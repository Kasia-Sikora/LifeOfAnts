package com.codecool.lifeOfAnts.model.insects;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.model.Directions;

import java.util.Random;

public class Drone extends Ant {

    Random random = new Random();

    int timeMating;

    int mate;
    public Drone(Cell cell) {
        super(cell);
        timeMating = 0;
        mate = 0;
    }

    @Override
    public String getTileName() {
        return "Drone";
    }


    public void moveToQueen(Queen queen) {

        int workerToQueenDx;
        int workerToQueenDy;

        workerToQueenDx = queen.getX() - this.getX();
        workerToQueenDy = queen.getY() - this.getY();

        if (timeMating == 0 && mate == 0) {
//        if (!isCloseToQueen(queen, workerToQueenDx, workerToQueenDy)) {

            if (Math.abs(workerToQueenDx) > Math.abs((workerToQueenDy))) {
                if (workerToQueenDx > 0) {
                    move(Directions.RIGHT);
                } else {
                    move(Directions.LEFT);
                }
            } else {
                if (workerToQueenDy > 0) {
                    move(Directions.DOWN);
                } else {
                    move(Directions.UP);
                }
            }
        } else {
            if (timeMating != 0) {
                timeMating--;
            } else {
                mate = 0;
                moveToRandomPosition();
            }
        }
    }

    void moveOutToEdge() {
        System.out.println("D’OH");
        this.cell.setAnt(null);
        cell.moveToEdge(this);
    }


    void sexOn() {
        timeMating = 10;
        System.out.println("“HALLELUJAH”");
    }

    private void moveToRandomPosition() {
        this.cell.setAnt(null);
        cell.moveToRandom(this);
        timeMating = 0;
    }

    int getTimeMating() {
        return timeMating;
    }
}

