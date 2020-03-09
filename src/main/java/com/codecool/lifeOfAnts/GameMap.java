package com.codecool.lifeOfAnts;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.model.CellType;
import com.codecool.lifeOfAnts.model.insects.*;
import javafx.application.Platform;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameMap {
    private int width;
    private int height;

    public Cell[][] getCells() {
        return cells;
    }

    private Cell[][] cells;

    private Queen queen;
    private Ant ant;
    private Drone drone;
    private Soldier soldier;
    private Worker worker;
    public List<Ant> colony;
    private Wasp wasp;
    public int timeToRespawnWasp = 20;
    boolean isWasp = false;

    GameMap(int width, int height, CellType defaultCellType) {
        this.width = width;
        this.height = height;
        cells = new Cell[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(this, x, y, defaultCellType);
            }
        }
        colony = new ArrayList<>();
    }

    void addAnts(Ant ant){
        colony.add(ant);
    }

    public Cell getCell(int x, int y) {
        try {
            return cells[x][y];
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }
        return null;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    public Queen getQueen() {
        return queen;
    }


    boolean moveAnts() {
        Platform.runLater(() -> {
            queen.checkIfMating();
            for (Ant ant : colony) {
                if(wasp == null) {
                    if (ant instanceof Drone) {
                        ((Drone) ant).moveToQueen(queen);
                    }
                    if (ant instanceof Worker) {
                        ((Worker) ant).move();
                    }
                    if (ant instanceof Soldier) {
                        ((Soldier) ant).move();
                    }
                } else{
                    if (ant instanceof Soldier) {
                        ((Soldier) ant).moveToWasp(wasp);
                    }
                }
            }
        });
        return true;
    }


    int getWidth() {
        return width;
    }

    int getHeight() {
        return height;
    }

    void setQueen(Queen queen) {
        this.queen = queen;
    }

    public void setWasp(Wasp wasp) {
        this.wasp = wasp;
    }

    void moveWasp() {
        Platform.runLater(() -> {
            if (timeToRespawnWasp > 0 && wasp != null) {
                wasp.moveToQueen();
            }else if (timeToRespawnWasp > 0){
                timeToRespawnWasp--;
            }else {
                if (wasp == null && timeToRespawnWasp == 0) {
                    Random random = new Random();
                    setWasp(new Wasp(getCell(random.nextInt(cells.length - 1), random.nextInt(cells.length - 1))));
                } else {
                    wasp.moveToQueen();
                }
            }
            System.out.println(timeToRespawnWasp);
        });
    }

    public Wasp getWasp() {
        return wasp;
    }
}
