package managers;

import helpz.LoadSave;
import objects.Tile;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class TileManager {
    public Tile GRASS, DIRT, ROAD, SAND;
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
