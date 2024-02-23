package managers;

import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static helpz.ImgFix.getRotImg;

public class TileManager {
    public Tile GRASS, DIRT, ROAD, SAND, DIRT_GRASS,
            DIRT_SAND, ROAD_GRASS, ROAD_SAND,
            DIRT_CORNER1, DIRT_CORNER2, ROAD_CORNER1, ROAD_CORNER2,
            BUSH1, BUSH2, ROCK1, ROCK2;
    public BufferedImage atlas;
    public ArrayList<Tile> tiles = new ArrayList<>();
    public TileManager() {
        loadAtlas();
        createTiles();
    }

    private BufferedImage getSprite(int x, int y) {
        if (atlas != null && x < 23 && y < 13) {
            return atlas.getSubimage(x*32, y*32, 32, 32);
        } else {
            return null;
        }
    }
    private void createTiles() {
        int id = 0;
        tiles.add(GRASS = new Tile(getSprite(19, 6), id++, "Grass"));
        tiles.add(DIRT = new Tile(getSprite(20, 6), id++, "Dirt"));
        tiles.add(ROAD = new Tile(getSprite(21, 6), id++, "Road"));
        tiles.add(SAND = new Tile(getSprite(22, 6), id++, "Sand"));
        tiles.add(DIRT_GRASS = new  Tile(getSprite(0, 4), id++, "Dirt_Grass1"));
        tiles.add(new Tile(getRotImg(DIRT_GRASS.getSprite(), 90), id++, "Dirt_Grass2"));
        tiles.add(new Tile(getRotImg(DIRT_GRASS.getSprite(), 180), id++, "Dirt_Grass3"));
        tiles.add(new Tile(getRotImg(DIRT_GRASS.getSprite(), 270), id++, "Dirt_Grass4"));
        tiles.add(DIRT_CORNER1 = new  Tile(getSprite(3, 3), id++, "DIRT_CORNER11"));
        tiles.add(new  Tile(getRotImg(DIRT_CORNER1.getSprite(), 90), id++, "DIRT_CORNER12"));
        tiles.add(new  Tile(getRotImg(DIRT_CORNER1.getSprite(), 180), id++, "DIRT_CORNER13"));
        tiles.add(new  Tile(getRotImg(DIRT_CORNER1.getSprite(), 270), id++, "DIRT_CORNER14"));
        tiles.add(DIRT_CORNER2 = new  Tile(getSprite(0, 3), id++, "DIRT_CORNER21"));
        tiles.add(new  Tile(getRotImg(DIRT_CORNER2.getSprite(), 90), id++, "DIRT_CORNER22"));
        tiles.add(new  Tile(getRotImg(DIRT_CORNER2.getSprite(), 180), id++, "DIRT_CORNER23"));
        tiles.add(new  Tile(getRotImg(DIRT_CORNER2.getSprite(), 270), id++, "DIRT_CORNER24"));
        tiles.add(DIRT_SAND = new  Tile(getSprite(5, 7), id++, "Dirt_Sand1"));
        tiles.add(new  Tile(getRotImg(DIRT_SAND.getSprite(), 90), id++, "Dirt_Sand2"));
        tiles.add(new  Tile(getRotImg(DIRT_SAND.getSprite(), 180), id++, "Dirt_Sand3"));
        tiles.add(new  Tile(getRotImg(DIRT_SAND.getSprite(), 270), id++, "Dirt_Sand4"));
        tiles.add(ROAD_GRASS = new  Tile(getSprite(10, 4), id++, "Road_Grass1"));
        tiles.add(new  Tile(getRotImg(ROAD_GRASS.getSprite(), 90), id++, "Road_Grass2"));
        tiles.add(new  Tile(getRotImg(ROAD_GRASS.getSprite(), 180), id++, "Road_Grass3"));
        tiles.add(new  Tile(getRotImg(ROAD_GRASS.getSprite(), 270), id++, "Road_Grass4"));
        tiles.add(ROAD_SAND = new  Tile(getSprite(10, 7), id++, "Road_Sand1"));
        tiles.add(new  Tile(getRotImg(ROAD_SAND.getSprite(), 90), id++, "Road_Sand2"));
        tiles.add(new  Tile(getRotImg(ROAD_SAND.getSprite(), 180), id++, "Road_Sand3"));
        tiles.add(new  Tile(getRotImg(ROAD_SAND.getSprite(), 270), id++, "Road_Sand4"));
        tiles.add(ROAD_CORNER1 = new  Tile(getSprite(13, 6), id++, "ROAD_CORNER11"));
        tiles.add(new  Tile(getRotImg(ROAD_CORNER1.getSprite(), 90), id++, "ROAD_CORNER12"));
        tiles.add(new  Tile(getRotImg(ROAD_CORNER1.getSprite(), 180), id++, "ROAD_CORNER13"));
        tiles.add(new  Tile(getRotImg(ROAD_CORNER1.getSprite(), 270), id++, "ROAD_CORNER14"));
        tiles.add(ROAD_CORNER2 = new  Tile(getSprite(10, 6), id++, "ROAD_CORNER21"));
        tiles.add(new  Tile(getRotImg(ROAD_CORNER2.getSprite(), 90), id++, "ROAD_CORNER22"));
        tiles.add(new  Tile(getRotImg(ROAD_CORNER2.getSprite(), 180), id++, "ROAD_CORNER23"));
        tiles.add(new  Tile(getRotImg(ROAD_CORNER2.getSprite(), 270), id++, "ROAD_CORNER24"));
        tiles.add(BUSH1 = new Tile(getSprite(15, 5), id++, "Bush1"));
        tiles.add(BUSH2 = new Tile(getSprite(17, 5), id++, "Bush2"));
        tiles.add(ROCK1 = new Tile(getSprite(21, 5), id++, "Rock1"));
        tiles.add(ROCK2 = new Tile(getSprite(20, 5), id++, "Rock2"));
    }

    private void loadAtlas() {
        atlas = LoadSave.getSpriteAtlas();
    }
    public Tile getTile(int id){
        return tiles.get(id);
    }
    public BufferedImage getSprite(int id) {
        BufferedImage sprite = tiles.get(id).getSprite();
        return sprite;
    }
}
