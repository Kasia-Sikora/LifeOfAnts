package com.codecool.lifeOfAnts;

import com.codecool.lifeOfAnts.model.Cell;
import com.codecool.lifeOfAnts.model.CellType;
import com.codecool.lifeOfAnts.model.insects.*;


import java.io.InputStream;
import java.util.Scanner;

class MapLoader {
    static GameMap loadMap(String mapFile) {
        InputStream is = MapLoader.class.getResourceAsStream(mapFile);
        Scanner scanner = new Scanner(is);
        int width = scanner.nextInt();
        int height = scanner.nextInt();

        scanner.nextLine(); // empty line

        GameMap map = new GameMap(width, height, CellType.EMPTY);
        for (int y = 0; y < height; y++) {
            String line = scanner.nextLine();
            for (int x = 0; x < width; x++) {
                if (x < line.length()) {
                    Cell cell = map.getCell(x, y);
                    switch (line.charAt(x)) {
                        case ' ':
                            cell.setType(CellType.EMPTY);
                            break;
                        case '#':
                            cell.setType(CellType.WALL);
                            break;
                        case 'W':
                            cell.setType(CellType.EMPTY);
                            map.addAnts(new Worker(cell));
                            break;
                        case 'S':
                            cell.setType(CellType.EMPTY);
                            map.addAnts(new Soldier(cell));
                            break;
                        case 'D':
                            cell.setType(CellType.EMPTY);
                            map.addAnts(new Drone(cell));
                            break;
                        case 'Q':
                            cell.setType(CellType.EMPTY);
                            map.setQueen(new Queen(cell));
                            break;
                        default:
                            throw new RuntimeException("Unrecognized character: '" + line.charAt(x) + "'");
                    }
                }
            }
        }
        return map;
    }
}
