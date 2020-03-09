package com.codecool.lifeOfAnts.model.insects;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.model.Directions;
import com.codecool.lifeOfAnts.model.CellType;
import com.codecool.lifeOfAnts.model.Drawable;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Ant implements Drawable {

    protected Cell cell;

    private double distanceToQueen;

    Ant(Cell cell) {
        this.cell = cell;
        this.cell.setAnt(this);
    }

    void move(Directions direction) {

        try {
            Cell nextCell = cell.getNeighbor(direction);
            Ant ant = nextCell.getAnt();
            if ((nextCell.getType().equals(CellType.EMPTY) && ant == null)) {
                cell.setAnt(null);
                nextCell.setAnt(this);
                cell = nextCell;
            } else {
                Random random = new Random();
                List<Directions> listOfDirections = Arrays.asList(Directions.UP, Directions.RIGHT, Directions.DOWN, Directions.LEFT);
                Directions nextDirection = listOfDirections.get(random.nextInt(listOfDirections.size()));
//                int time = 4;
//                do {
                    move(nextDirection);
//                    time--;
//                } while (time != 0);
            }
            distanceToQueen = cell.checkDistanceToQueen(this);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
    }

    public void setCell(Cell newCell) {
        this.cell = newCell;
    }

    public abstract String getTileName();

    public Cell getCell() {
        return cell;
    }


    public int getX() {
        return cell.getX();
    }

    public int getY() {
        return cell.getY();
    }
}
