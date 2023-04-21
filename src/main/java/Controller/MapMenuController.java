package Controller;

import Model.Tile;

import java.util.HashMap;
import java.util.regex.Matcher;

import static View.InputOutput.output;

public class MapMenuController {
    public static final int SIZEX = 8;
    public static final int SIZEY = 3;
    public static void initializeMap(int mapSize) {
        Tile[][] tiles = new Tile[mapSize][mapSize];
        for (int i = 0; i < mapSize ; i++) {
            for (int j = 0 ; j < mapSize ; j++) {
                tiles[i][j] = new Tile();
                tiles[i][j].setTexture("Earth");
                tiles[i][j].setX(i);
                tiles[i][j].setY(j);
            }
        }
        GameMenuController.game.getMap().setTiles(tiles);
        GameMenuController.game.getMap().setMapSize(mapSize);
    }
    public static void setTexture(Matcher matcher) {
        String type = matcher.group("type");
        int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
        GameMenuController.game.getMap().getTiles()[x][y].setTexture(type);
    }
    public static void setTextureRectangle(Matcher matcher) {
        String type = matcher.group("type");
        int x1 = Integer.parseInt(matcher.group("X1")), x2 = Integer.parseInt(matcher.group("X2")) ,
                y1 = Integer.parseInt(matcher.group("Y1")), y2 = Integer.parseInt(matcher.group("Y2"));
        for (int i = x1 ; i <= x2 ; i++ ){
            for (int j = y1 ; j <= y2 ; j++) {
                GameMenuController.game.getMap().getTiles()[i][j].setTexture(type);
            }
        }
    }
    public static void clear(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
        GameMenuController.game.getMap().getTiles()[x][y].setTexture("Earth");
        GameMenuController.game.getMap().getTiles()[x][y].getPeople().clear();
        GameMenuController.game.getMap().getTiles()[x][y].setBuilding(null);
    }
    public static void DrawMap(int topX, int topY) {
        HashMap<String, String> COLORS = changeColor();
        System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        drawSeparators(SIZEX * 6);
        for (int y = 0 ; y < SIZEY ; y++) {
            System.out.print("|");
            for (int x = 0 ; x < SIZEX ; x++) {
                int mapX = topX + x;
                int mapY = topY + y;
                drawAnEmptyLine(COLORS.get(GameMenuController.game.getMap().getTiles()[mapX][mapY].getTexture()),
                        COLORS.get("Reset"));
            }
            System.out.print("\n|");
            for (int x = 0 ; x < SIZEX ; x++) {
                int mapX = topX + x;
                int mapY = topY + y;
                Tile tile = GameMenuController.game.getMap().getTiles()[mapX][mapY];
                String c;
                if (tile.getBuilding() != null) c = "##B##";
                else if (tile.getPeople().size() > 0) c = "##S##";
                else c = "#####";
                c = COLORS.get(GameMenuController.game.getMap().getTiles()[mapX][mapY].getTexture()) + c +
                        COLORS.get("Reset") + "|";
                System.out.print(c);
            }
            System.out.print("\n|");
            for (int x = 0 ; x < SIZEX ; x++) {
                int mapX = topX + x;
                int mapY = topY + y;
                drawAnEmptyLine(COLORS.get(GameMenuController.game.getMap().getTiles()[mapX][mapY].getTexture()),
                        COLORS.get("Reset"));
            }
            System.out.println();
            drawSeparators(SIZEX * 6);
        }

    }
    public static HashMap<String, String> changeColor() {
        HashMap<String, String> map = new HashMap<>();
//        Black
        map.put("Stone", "\u001B[40m");
        map.put("Iron", "\u001B[40m");
//        Red
        map.put("Blood", "\u001B[41m");
//        Green
        map.put("Earth", "\u001B[42m");
        map.put("Grass", "\u001B[42m");
//        Blue
        map.put("Lake", "\u001B[44m");
//        Yellow
        map.put("Sand", "\u001B[43m");
//        Reset
        map.put("Reset", "\u001B[0m");
        return map;
    }
    private static void drawAnEmptyLine(String color, String reset) {
        System.out.print(color + "#####" + reset + "|");
    }
    private static void drawSeparators(int times) {
        for (int i = 0 ; i < times ; i++) System.out.print("-");
        System.out.println();
    }
    public static void showDetails(Matcher matcher) {
        int x = Integer.parseInt(matcher.group("X")), y = Integer.parseInt(matcher.group("Y"));
        Tile tile = GameMenuController.game.getMap().getTiles()[x][y];
        output("x: " + tile.getX() + " , y: " + tile.getY());
        output("texture: " + tile.getTexture());
        if (tile.getBuilding() != null) output("Building: " + tile.getBuilding().getName());
        else if (tile.getPeople().size() > 0) output("People: " + tile.getPeople().get(0).getName());
    }
}