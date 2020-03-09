package com.codecool.lifeOfAnts;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.view.Tiles;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class Main extends Application {
    private boolean isAntsMove = false;

    private final int screenWidth = 102;
    private final int screenHeight = 102;
    private List<String> mapList = new ArrayList<>() {{
        add("/map.txt");
    }};
    private GameMap map = MapLoader.loadMap(mapList.remove(0));
    private Canvas canvas = new Canvas(
            screenWidth * Tiles.TILE_WIDTH,
            screenHeight * Tiles.TILE_WIDTH);
    private GraphicsContext context = canvas.getGraphicsContext2D();


    // todo google Runnable, Thread
    private Task<Void> moveAnts = new Task<>() {
        @Override
        protected Void call() throws Exception {
            do {
                isAntsMove = false;
                Thread.sleep(500);
                if (map.moveAnts()) {
                    isAntsMove = true;
                    map.moveWasp();
                    Thread.sleep(5);
                }
            } while (!isCancelled());
            return null;
        }
    };

    private Task<Void> refresh = new Task<>() {
        @Override
        protected Void call() throws Exception {
            while (true) {
                Thread.sleep(4);
                if (isAntsMove) {
                    refresh();
                }
                if (isCancelled()) {
                    return null;
                }
            }
        }
    };


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage){

        BorderPane borderPane = new BorderPane();

        borderPane.setCenter(canvas);


        Scene scene = new Scene(borderPane);
        primaryStage.setScene(scene);
        refresh();

        primaryStage.setTitle("Ant Colony");
        primaryStage.show();

        // todo move to new method
        Thread threadMonstersMove = new Thread(moveAnts);
        Thread threadRefreshMap = new Thread(refresh);

        threadMonstersMove.setDaemon(true);
        threadRefreshMap.setDaemon(true);

        threadMonstersMove.start();
        threadRefreshMap.start();
    }

    private void refresh() {

        Platform.runLater(this::printNewBoard);
    }


    private void printNewBoard() {
        context.setFill(Color.BLACK);
        context.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Cell cell = map.getCell(x, y);

                if (cell.getAnt() != null) {
                    Tiles.drawTile(context, cell.getAnt(), x, y);
                } else if (cell.getWasp() != null) {
                    Tiles.drawTile(context, cell.getWasp(), x, y);
                } else {
                    Tiles.drawTile(context, cell, x, y);
                }
            }
        }
    }
}
