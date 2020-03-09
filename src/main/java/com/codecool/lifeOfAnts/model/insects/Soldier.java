package com.codecool.lifeOfAnts.model.insects;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.model.Directions;

public class Soldier extends Ant {

    Queen queen;
    Directions lastDirection = Directions.LEFT;


    public Soldier(Cell cell) {
        super(cell);
    }

    @Override
    public String getTileName() {
        return "Soldier";
    }


    public void move() {

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

    public void moveToWasp(Wasp wasp) {
        int soldierToWaspDx;
        int soldierToWaspDy;

        soldierToWaspDx = wasp.getX() - this.getX();
        soldierToWaspDy = wasp.getY() - this.getY();

        if (cell.checkDistanceToWasp(this) > 1) {
            if (Math.abs(soldierToWaspDx) > Math.abs((soldierToWaspDy))) {
                if (soldierToWaspDx > 0) {
                    move(Directions.RIGHT);
                } else {
                    move(Directions.LEFT);
                }
            } else {
                if (soldierToWaspDy > 0) {
                    move(Directions.DOWN);
                } else {
                    move(Directions.UP);
                }
            }
        } else {
            System.out.println("HAJIME");
            wasp.cell.setWasp(null);
        }
    }
}


