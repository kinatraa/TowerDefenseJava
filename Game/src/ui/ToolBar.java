package ui;

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
    private Tile selectedTile;
    private ArrayList<MyButton> tileButtons = new ArrayList<>();
    public ToolBar(int x, int y, int width, int height, Editing editing, GameWindow game) {
        super(x, y, width, height);
        this.editing = editing;
        this.game = game;
        initButtons();
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
            tileButtons.add(new MyButton(tile.getName(), xStart + xOffset*cnt, yStart, 32, 32, index));
            ++index;
            ++cnt;
        }
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

        drawSelectedTile(g);
    }

    private void drawSelectedTile(Graphics g) {
        if(selectedTile != null){
            g.drawImage(selectedTile.getSprite(), 1032, 650, 64, 64, null);
            g.setColor(Color.BLACK);
            g.drawRect(1032, 650, 64, 64);
        }
    }

    private void drawTileButtons(Graphics g){
        for(MyButton b : tileButtons){
            g.drawImage(getButtonImg(b.getId()), b.getX(), b.getY(), b.getW(), b.getH(), null);
            if(b.isMouseOver()) g.setColor(Color.WHITE);
            else g.setColor(Color.BLACK);
            if(b.isMousePressed()){
                g.drawRect(b.getX()+1, b.getY()+1, b.getW()-2, b.getH()-2);
                g.drawRect(b.getX()+2, b.getY()+2, b.getW()-4, b.getH()-4);
            }
            if(!b.isMouseOver()){
                b.setMousePressed(false);
            }
            g.drawRect(b.getX(), b.getY(), b.getW(), b.getH());
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
        for(MyButton b : tileButtons){
            b.setMouseOver(false);
        }
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMouseOver(true);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.setMouseOver(true);
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
        for(MyButton b : tileButtons){
            b.setMousePressed(false);
        }
        if(bMenu.getBounds().contains(x, y)){
            bMenu.setMousePressed(true);
        }
        else if(bSave.getBounds().contains(x, y)){
            bSave.setMousePressed(true);
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
        for(MyButton b : tileButtons){
            b.resetBooleans();
        }
    }
}
