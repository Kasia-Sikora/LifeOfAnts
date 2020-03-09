package com.codecool.lifeOfAnts.model.insects;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.model.Directions;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Worker extends Ant {

    private Directions lastDirection = Directions.LEFT;


    public Worker(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "Worker";
    }

    public void move() {

        Random random = new Random();

        List<Directions> listOfDirections = Arrays.asList(Directions.UP, Directions.RIGHT, Directions.DOWN, Directions.LEFT);

        lastDirection = listOfDirections.get(random.nextInt(listOfDirections.size()));

        switch (lastDirection) {
            case LEFT:
                move(Directions.UP);
                lastDirection = Directions.UP;
                break;
            case UP:
                move(Directions.RIGHT);
                lastDirection = Directions.RIGHT;
                break;
            case RIGHT:
                move(Directions.DOWN);
                lastDirection = Directions.DOWN;
                break;
            case DOWN:
                move(Directions.LEFT);
                lastDirection = Directions.LEFT;
                break;

        }
    }

}