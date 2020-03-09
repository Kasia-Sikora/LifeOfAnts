//package com.codecool.quest.logic;
//
//import com.codecool.quest.GameMap;
//import com.codecool.quest.model.CellType;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AntTest {
//    GameMap gameMap = new GameMap(3, 3, CellType.FLOOR);
//
//    @Test
//    void moveUpdatesCells() {
//        Player player = new Player(gameMap.getCell(1, 1));
//        player.move(1, 0);
//
//        assertEquals(2, player.getX());
//        assertEquals(1, player.getY());
//        assertEquals(null, gameMap.getCell(1, 1).getAnt());
//        assertEquals(player, gameMap.getCell(2, 1).getAnt());
//    }
//
//    @Test
//    void cannotMoveIntoWall() {
//        gameMap.getCell(2, 1).setType(CellType.WALL);
//        Player player = new Player(gameMap.getCell(1, 1));
//        player.move(1, 0);
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//    }
//
//    @Test
//    void cannotMoveOutOfMap() {
//        Player player = new Player(gameMap.getCell(2, 1));
//        player.move(1, 0);
//
//        assertEquals(2, player.getX());
//        assertEquals(1, player.getY());
//    }
//
//    @Test
//    void cannotMoveIntoAnotherActor() {
//        Player player = new Player(gameMap.getCell(1, 1));
//        Skeleton skeleton = new Skeleton(gameMap.getCell(2, 1));
//        player.move(1, 0);
//
//        assertEquals(1, player.getX());
//        assertEquals(1, player.getY());
//        assertEquals(2, skeleton.getX());
//        assertEquals(1, skeleton.getY());
//        assertEquals(skeleton, gameMap.getCell(2, 1).getAnt());
//    }
//}