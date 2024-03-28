package managers;

import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Vector;

import static helpz.ImgFix.getRotImg;
import static helpz.Constants.Tiles.*;

public class TileManager {
    public Tile GRASS, DIRT, ROAD, SAND, DIRT_GRASS,
            DIRT_SAND, ROAD_GRASS, ROAD_SAND,
            DIRT_CORNER1, DIRT_CORNER2, ROAD_CORNER1, ROAD_CORNER2,
            BUSH1, BUSH2, ROCK1, ROCK2;
    public BufferedImage atlas;
    public Vector<Tile> tiles = new Vector<>();

    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private BufferedImage getSprite(int x, int y) {
        if (atlas != null && x < 23 && y < 13) {
            return atlas.getSubimage(x * 32, y * 32, 32, 32);
        } else {
            return null;
        }
    }

    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(19, 6), id++, GRASS_TILE));
        tiles.add(DIRT = new Tile(getSprite(20, 6), id++, DIRT_TILE));
        tiles.add(ROAD = new Tile(getSprite(21, 6), id++, ROAD_TILE));
        tiles.add(SAND = new Tile(getSprite(22, 6), id++, SAND_TILE));

        tiles.add(DIRT_GRASS = new Tile(getSprite(0, 1), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_GRASS.getSprite(), 90), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_GRASS.getSprite(), 180), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_GRASS.getSprite(), 270), id++, DIRT_e_TILE));

        tiles.add(DIRT_CORNER1 = new Tile(getSprite(3, 0), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_CORNER1.getSprite(), 90), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_CORNER1.getSprite(), 180), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_CORNER1.getSprite(), 270), id++, DIRT_e_TILE));

        tiles.add(DIRT_CORNER2 = new Tile(getSprite(0, 0), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_CORNER2.getSprite(), 90), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_CORNER2.getSprite(), 180), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_CORNER2.getSprite(), 270), id++, DIRT_e_TILE));

        tiles.add(DIRT_SAND = new Tile(getSprite(5, 7), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_SAND.getSprite(), 90), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_SAND.getSprite(), 180), id++, DIRT_e_TILE));
        tiles.add(new Tile(getRotImg(DIRT_SAND.getSprite(), 270), id++, DIRT_e_TILE));

        tiles.add(ROAD_GRASS = new Tile(getSprite(10, 4), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_GRASS.getSprite(), 90), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_GRASS.getSprite(), 180), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_GRASS.getSprite(), 270), id++, ROAD_e_TILE));

        tiles.add(ROAD_SAND = new Tile(getSprite(10, 7), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_SAND.getSprite(), 90), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_SAND.getSprite(), 180), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_SAND.getSprite(), 270), id++, ROAD_e_TILE));

        tiles.add(ROAD_CORNER1 = new Tile(getSprite(13, 6), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_CORNER1.getSprite(), 90), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_CORNER1.getSprite(), 180), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_CORNER1.getSprite(), 270), id++, ROAD_e_TILE));

        tiles.add(ROAD_CORNER2 = new Tile(getSprite(10, 6), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_CORNER2.getSprite(), 90), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_CORNER2.getSprite(), 180), id++, ROAD_e_TILE));
        tiles.add(new Tile(getRotImg(ROAD_CORNER2.getSprite(), 270), id++, ROAD_e_TILE));

        tiles.add(BUSH1 = new Tile(getSprite(15, 5), id++, BUSH_TILE));
        tiles.add(BUSH2 = new Tile(getSprite(17, 5), id++, BUSH_TILE));
        tiles.add(ROCK1 = new Tile(getSprite(21, 5), id++, ROCK_TILE));
        tiles.add(ROCK2 = new Tile(getSprite(20, 5), id++, ROCK_TILE));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }

    public Tile getTile(int id) {
        return tiles.get(id);
    }

    public BufferedImage getSprite(int id) {
        BufferedImage sprite = tiles.get(id).getSprite();
        return sprite;
    }

    public int[][] getTypeArr() {
        int[][] idArr = LoadSave.GetLevelData("new_level");
        int[][] typeArr = new int[idArr.length][idArr[0].length];

        for (int j = 0; j < idArr.length; j++) {
            for (int i = 0; i < idArr[j].length; i++) {
                int id = idArr[j][i];
                typeArr[j][i] = tiles.get(id).getTileType();
            }
        }

        return typeArr;

    }
}
