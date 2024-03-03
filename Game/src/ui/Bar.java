package ui;

import scenes.Playing;

import java.awt.*;

public class Bar {
    protected int x, y, width, height;
    public Bar(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    protected void drawButtonFeedback(Graphics g, MyButton b){
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
