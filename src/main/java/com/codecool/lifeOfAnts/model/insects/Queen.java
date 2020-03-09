package com.codecool.lifeOfAnts.model.insects;

import com.codecool.lifeOfAnts.model.Cell;

import java.util.Random;

public class Queen extends Ant {

    Random random;
    int mate = 0;
    int timeToMate = 0;


    public Queen(Cell cell) {
        super(cell);
        random = new Random();
        setMating();

    }

    private void setMating() {
        this.timeToMate = random.nextInt(5) + 5;
    }

    @Override
    public String getTileName() {
        return "Queen";
    }

    public void checkIfMating() {

        checkNeighbours();

        if(mate == 0 && timeToMate > 0) {
            timeToMate--;
        }
        else if (mate == 0 && timeToMate == 0){
            mate = 1;
        }
    }

    private void checkNeighbours() {

        for (int i = -4; i <= 4; i++){
            for (int j = -4; j <= 4; j++){
                Ant ant = cell.getNeighbor(this.getX() + i, this.getY()+ j).getAnt();
                if (ant instanceof Drone){
                    mate(((Drone)ant));
                }
            }
        }
    }

    private void mate(Drone ant) {
        if(mate == 1){
            if(ant.getTimeMating() == 0) {
                ant.sexOn();
                setMating();
                mate = 0;
                cell.addNewRandomAnt();
            }
        }else{
            if(ant.getTimeMating() == 0)
                ant.moveOutToEdge();
        }
    }
}

