package scenes;

import helpz.LoadSave;
import main.GameWindow;
import objects.Tile;
import ui.MyButton;
import ui.ToolBar;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Editing extends GameScene implements SceneMethods {
    private GameWindow game;
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelect;
    private ToolBar toolBar;
    public Editing(GameWindow game){
        super(game);
        loadDefaultLevel();
        toolBar = new ToolBar(1024, 0, 256, 768, this, game);
        this.game = game;
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
//        for(int y = 0; y < lvl.length; y++){
//            for(int x = 0; x < lvl[y].length; x++){
//                int id = lvl[y][x];
//                System.out.print(id + " ");
//            }
//            System.out.println();
//        }
//        System.out.println();
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
    }
    private void drawLevel(Graphics g){
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x*32, y*32, null);
            }
        }
    }
    private BufferedImage getSprite(int spiteID){
        return getGame().getTileManager().getSprite(spiteID);
    }
    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null && drawSelect){
            g.drawImage(selectedTile.getSprite(), mouseX, mouseY, 32, 32, null);
        }
    }
    public void setSelectedTile(Tile tile){
        this.selectedTile = tile;
        drawSelect = true;
    }
    private void changeTile(int x, int y) {
        if(selectedTile != null){
            int tileX = x / 32;
            int tileY = y / 32;
            if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                return;
            lastTileX = tileX;
            lastTileY = tileY;
            lastTileId = selectedTile.getId();
            lvl[tileY][tileX] = selectedTile.getId();
        }
    }
    public void saveLevel(){
        LoadSave.SaveLevel("new_level", lvl);
        game.getPlaying().setLevel(lvl);
    }
    @Override
    public void mouseClicked(int x, int y) {
        if(x > 1024){
            toolBar.mouseClicked(x, y);
        }
        else{
            changeTile(mouseX, mouseY);
        }
    }

    @Override
    public void mouseMoved(int x, int y) {
        if(x > 1024){
            toolBar.mouseMoved(x, y);
            drawSelect = false;
        }
        else{
            drawSelect = true;
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(x > 1024){
            toolBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        toolBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
        if(x > 1024){

        }
        else{
            changeTile(x, y);
        }
    }
}
