package com.codecool.lifeOfAnts.model.insects;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.model.Directions;
import com.codecool.lifeOfAnts.model.CellType;
import com.codecool.lifeOfAnts.model.Drawable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Wasp implements Drawable {

    Cell cell;

    public Wasp(Cell cell) {
        this.cell = cell;
        this.cell.setWasp(this);
    }


    public void moveToQueen() {

        int waspToQueenDx;
        int waspToQueenDy;

        waspToQueenDx = cell.getQueen().getX() - this.getX();
        waspToQueenDy = cell.getQueen().getY() - this.getY();


        if (Math.abs(waspToQueenDx) > Math.abs((waspToQueenDy))) {
            if (waspToQueenDx > 0) {
                move(Directions.RIGHT);
            } else {
                move(Directions.LEFT);
            }
        } else {
            if (waspToQueenDy > 0) {
                move(Directions.DOWN);
            } else {
                move(Directions.UP);
            }
        }
    }

    void move(Directions direction) {

        Cell nextCell = cell.getNeighbor(direction);
        Ant ant = nextCell.getAnt();
        if ((nextCell.getType().equals(CellType.EMPTY) && ant == null)) {
            cell.setWasp(null);
            nextCell.setWasp(this);
            cell = nextCell;
        } else {
            Random random = new Random();
            List<Directions> listOfDirections = Arrays.asList(Directions.UP, Directions.RIGHT, Directions.DOWN, Directions.LEFT);
            Directions nextDirection = listOfDirections.get(random.nextInt(listOfDirections.size()));
            move(nextDirection);
        }
    }

    @Override
    public String getTileName() {
        return "Wasp";
    }

    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
