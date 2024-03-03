package ui;

import helpz.LoadSave;
import main.GameWindow;
import objects.Tile;
import scenes.Editing;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static main.GameStates.*;

public class ToolBar extends Bar{
    private Editing editing;
    private GameWindow game;
    private MyButton bMenu, bSave;
    private MyButton bPathStart, bPathEnd;
    private BufferedImage pathStart, pathEnd, selectedPath;
    private Tile selectedTile;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    public ToolBar(int x, int y, int width, int height, Editing editing, GameWindow game) {
        super(x, y, width, height);
        this.editing = editing;
        this.game = game;
        initButtons();
        initPathImgs();
    }

    private void initPathImgs() {
        pathStart = LoadSave.getSpriteAtlas().getSubimage(18*32, 0, 32, 32);
        pathEnd = LoadSave.getSpriteAtlas().getSubimage(17*32, 0, 32, 32);
    }

    private void initButtons() {
        bSave = new MyButton("Save", 1028, 5, 120, 50);
        bMenu = new MyButton("Main Menu", 1156, 5, 120, 50);
        int yStart = 100;
        int yOffset = 40;
        int xStart = 1032;
        int xOffset = 40;
        int index = 0, cnt = 0;
        for(Tile tile: game.getTileManager().tiles){
            if(cnt == 4){
                cnt = 0;
                yStart += yOffset;
            }
            tileButtons.add(new MyButton(tile.getTileType(), xStart + xOffset*cnt, yStart, 32, 32, index));
            ++index;
            ++cnt;
        }
        bPathStart = new MyButton("PathStart", 1032 + xOffset*5, 100, 32, 32);
        bPathEnd = new MyButton("PathEnd", 1032 + xOffset*5, 100 + yOffset, 32, 32);
    }

    public void draw(Graphics g){
        g.setColor(new Color(181,215,253));
        g.fillRect(x, y, width, height);
        drawButtons(g);
    }
    private void drawButtons(Graphics g){
        bMenu.draw(g);
        bSave.draw(g);

        drawTileButtons(g);

        drawPathButton(g, bPathStart, pathStart);
        drawPathButton(g, bPathEnd, pathEnd);

        drawSelectedTile(g);
    }

    private void drawPathButton(Graphics g, MyButton b, BufferedImage img) {
        g.drawImage(img, b.getX(), b.getY(), b.getW(), b.getH(), null);
    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null){
            g.drawImage(selectedTile.getSprite(), 1032, 650, 64, 64, null);
            g.setColor(Color.BLACK);
            g.drawRect(1032, 650, 64, 64);
        }

        if(selectedPath != null){
            g.drawImage(selectedPath, 1032, 650, 64, 64, null);
            g.setColor(Color.BLACK);
            g.drawRect(1032, 650, 64, 64);
        }
    }

    private void drawTileButtons(Graphics g){
        for(MyButton b : tileButtons){
            g.drawImage(getButtonImg(b.getId()), b.getX(), b.getY(), b.getW(), b.getH(), null);
            drawButtonFeedback(g, b);
        }
    }
    private BufferedImage getButtonImg(int id) {
        return game.getTileManager().getSprite(id);
    }

    public void mouseClicked(int x, int y) {
        if(bMenu.getBounds().contains(x, y)){
            bMenu.resetBooleans();
            SetGameState(MENU);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.resetBooleans();
            editing.saveLevel();
        }
        else if(bPathStart.getBounds().contains(x, y)){
            if(selectedPath == pathStart){
                selectedPath = null;
                editing.setSelectedTile(null);
                return;
            }
            selectedPath = pathStart;
            editing.setSelectedTile(new Tile(pathStart, -1, 0));
        }
        else if(bPathEnd.getBounds().contains(x, y)){
            if(selectedPath == pathEnd){
                selectedPath = null;
                editing.setSelectedTile(null);
                return;
            }
            selectedPath = pathEnd;
            editing.setSelectedTile(new Tile(pathEnd, -2, 0));
        }
        else{
            for(MyButton b : tileButtons){
                if(b.getBounds().contains(x, y)){
                    if(selectedTile == game.getTileManager().getTile(b.getId())){
                        selectedTile = null;
                        editing.setSelectedTile(null);
                        return;
                    }
                    selectedTile = game.getTileManager().getTile(b.getId());
                    editing.setSelectedTile(selectedTile);
                    return;
                }
            }
            selectedTile = null;
            editing.setSelectedTile(null);
        }
    }
    public void mouseClicked3(){
    }
    public void mouseMoved(int x, int y) {
        bMenu.setMouseOver(false);
        bSave.setMouseOver(false);
        bPathStart.setMouseOver(false);
        bPathEnd.setMouseOver(false);
        for(MyButton b : tileButtons){
            b.setMouseOver(false);
        }
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.setMouseOver(true);
        }
        else if(bPathStart.getBounds().contains(x, y)){
            bPathStart.setMouseOver(true);
        }
        else if(bPathEnd.getBounds().contains(x, y)){
            bPathEnd.setMouseOver(true);
        }
        else{
            for(MyButton b : tileButtons){
                if(b.getBounds().contains(x, y)){
                    b.setMouseOver(true);
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        bMenu.setMousePressed(false);
        bSave.setMousePressed(false);
        bPathStart.setMousePressed(false);
        bPathEnd.setMousePressed(false);
        for(MyButton b : tileButtons){
            b.setMousePressed(false);
        }
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.setMousePressed(true);
        }
        else if(bPathStart.getBounds().contains(x, y)){
            bPathStart.setMousePressed(true);
        }
        else if(bPathEnd.getBounds().contains(x, y)){
            bPathEnd.setMousePressed(true);
        }
        else{
            for(MyButton b : tileButtons){
                if(b.getBounds().contains(x, y)){
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }
    public void mouseReleased(int x, int y) {
        bMenu.resetBooleans();
        bSave.resetBooleans();
        bPathStart.resetBooleans();
        bPathEnd.resetBooleans();
        for(MyButton b : tileButtons){
            b.resetBooleans();
        }
    }
    public BufferedImage getStartPathImg(){
        return pathStart;
    }
    public BufferedImage getEndPathImg(){
        return pathEnd;
    }
}
