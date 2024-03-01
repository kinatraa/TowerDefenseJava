package scenes;

import helpz.LoadSave;
import main.GameWindow;
import objects.PathPoint;
import objects.Tile;
import ui.ToolBar;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.nio.file.Path;
import java.util.ArrayList;

import static helpz.Constants.Tiles.*;

public class Editing extends GameScene implements SceneMethods {
    private GameWindow game;
    private int[][] lvl;
    private Tile selectedTile;
    private int mouseX, mouseY;
    private int lastTileX, lastTileY, lastTileId;
    private boolean drawSelect;
    private ToolBar toolBar;
    private PathPoint start, end;
    public Editing(GameWindow game){
        super(game);
        loadDefaultLevel();
        toolBar = new ToolBar(1024, 0, 256, 768, this, game);
        this.game = game;
    }

    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }

    @Override
    public void render(Graphics g) {
        drawLevel(g);
        toolBar.draw(g);
        drawSelectedTile(g);
        drawPathPoint(g);
//        System.out.println("check");
    }

    private void drawPathPoint(Graphics g) {
        if(start != null){
            g.drawImage(toolBar.getStartPathImg(), start.getxCord() * 32, start.getyCord() * 32, 32, 32, null);
        }
        if(end != null){
            g.drawImage(toolBar.getEndPathImg(), end.getxCord() * 32, end.getyCord() * 32, 32, 32, null);
        }
    }

    private void drawLevel(Graphics g){
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
//                if(id == -1) g.drawImage(selectedTile.getSprite(), x*32, y*32, null);
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
            if(selectedTile.getId() >= 0){
                if(lastTileX == tileX && lastTileY == tileY && lastTileId == selectedTile.getId())
                    return;
                lastTileX = tileX;
                lastTileY = tileY;
                lastTileId = selectedTile.getId();
                lvl[tileY][tileX] = selectedTile.getId();
            }
            else{
                int id = lvl[tileY][tileX];
                if(game.getTileManager().getTile(id).getTileType() == DIRT_TILE){
                    if(selectedTile.getId() == -1){
                        start = new PathPoint(tileX, tileY);
                    }
                    else{
                        end = new PathPoint(tileX, tileY);
                    }
                }
            }
        }
    }
    public void saveLevel(){
        LoadSave.SaveLevel("new_level", lvl, start, end);
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
    public void mouseClicked3() {
        toolBar.mouseClicked3();
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
