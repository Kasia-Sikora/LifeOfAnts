package com.codecool.lifeOfAnts.model;

import com.codecool.lifeOfAnts.GameMap;
import com.codecool.lifeOfAnts.model.insects.*;

import java.util.Random;

public class Cell implements Drawable {

    private CellType type;
    private Ant ant;
    private GameMap gameMap;
    private int x, y;
    Random random = new Random();
    private Wasp wasp;

    public Cell(GameMap gameMap, int x, int y, CellType type) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public Cell(GameMap gameMap, int x, int y) {
        this.gameMap = gameMap;
        this.x = x;
        this.y = y;
    }

    public CellType getType() {
        return type;
    }

    public void setType(CellType type) {
        this.type = type;
    }

    public void setAnt(Ant ant) {
        this.ant = ant;
    }

    public Ant getAnt() {
        return ant;
    }

    public Cell getNeighbor(int x, int y) {
        return gameMap.getCell(x , y);
    }

    public Cell getNeighbor(Directions direction) {
        return gameMap.getCell(x + direction.getX(), y + direction.getY());
    }

    @Override
    public String getTileName() {
        return type.getTileName();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Ant getQueen(){
        return gameMap.getQueen();
    }

    public double checkDistanceToQueen (Ant ant){
        int antToQueenX = gameMap.getQueen().getX() - ant.getX();
        int antToQueenY = gameMap.getQueen().getY() - ant.getY();

        return  Math.sqrt(antToQueenX * antToQueenX + antToQueenY * antToQueenY);
    }

    public double checkDistanceToWasp (Ant ant){
        int antToWaspX = gameMap.getWasp().getX() - ant.getX();
        int antToWaspY = gameMap.getWasp().getY() - ant.getY();

        return  Math.sqrt(antToWaspX * antToWaspX + antToWaspY * antToWaspY);
    }

    public void addNewRandomAnt() {

        int ant = random.nextInt(3);
        Ant newAnt = null;

        switch (ant) {
            case 0:
                newAnt = new Soldier(gameMap.getCell(random.nextInt(80), random.nextInt(80)));
                break;
            case 1:
                newAnt = new Worker(gameMap.getCell(random.nextInt(80), random.nextInt(80)));
                break;
            case 2:
                newAnt = new Drone(gameMap.getCell(random.nextInt(80), random.nextInt(80)));
                break;
        }
        gameMap.colony.add(newAnt);
        System.out.println("New " + newAnt.getTileName() + " was born!");
    }

    public void moveToRandom(Ant drone) {
        Cell[][] cells = gameMap.getCells();
        Cell newCell = cells[random.nextInt(cells.length - 1)][random.nextInt(cells.length - 1)];
        newCell.setAnt(drone);
        drone.setCell(newCell);
    }

    public void moveToEdge(Ant drone) {
        Cell[][] cells = gameMap.getCells();
        int x = 1;
        int y = cells.length -2;
        int[] coordinates = new int[]{x, y};
        System.out.println(coordinates[0] + " " + coordinates[1]);
        Cell newCell = cells[coordinates[random.nextInt(2)]][coordinates[random.nextInt(2)]];
        newCell.setAnt(drone);
        drone.setCell(newCell);
    }

    public void setWasp(Wasp wasp) {
        this.wasp = wasp;
        gameMap.setWasp(wasp);
        if(wasp == null){
            gameMap.timeToRespawnWasp = 20;
        }
    }

    public Wasp getWasp() {
        return wasp;
    }
}
