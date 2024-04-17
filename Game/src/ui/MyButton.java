package ui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private int x, y, width, height, id, tileType;
    private String text;
    private Rectangle bounds;
    private boolean mouseOver, mousePressed;
    public MyButton(String text, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = -1;
        initBounds();
    }

    public MyButton(String text, int x, int y, int width, int height, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.id = id;
        initBounds();
    }

    public MyButton(int tileType, int x, int y, int width, int height, int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.tileType = tileType;
        this.id = id;
        initBounds();
    }
    private void initBounds(){
        this.bounds = new Rectangle(x, y, width, height);
    }
    public void draw(Graphics g){
        drawBody(g);
        drawBorder(g);
        drawText(g);
    }

//    public void setImage(ImageIO image){
//        setImage(image);
//    }
    private void drawBorder(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRoundRect(x, y, width, height, 10, 10);
        if (mousePressed) {
            g.drawRoundRect(x + 1, y + 1, width - 2, height - 2, 10, 10);
            g.drawRoundRect(x + 2, y + 2, width - 4, height - 4, 10, 10);
        }
    }

    private void drawBody(Graphics g) {
        if(mouseOver) g.setColor(Color.GRAY);
        else g.setColor(Color.WHITE);
        g.fillRoundRect(x, y, width, height, 20, 20);
    }

    private void drawText(Graphics g) {
        g.setFont(new Font("Impact", Font.PLAIN, 18));
        int w = g.getFontMetrics().stringWidth(text);
        int h = g.getFontMetrics().getHeight();
        g.drawString(text, x - w/2 + width/2, y + h/2 + height/2);
    }

    public void resetBooleans() {
        this.mouseOver = false;
        this.mousePressed = false;
    }

    public void setMousePressed(boolean mousePressed){
        this.mousePressed = mousePressed;
    }
    public boolean isMousePressed(){
        return mousePressed;
    }
    public void setMouseOver(boolean mouseOver){
        this.mouseOver = mouseOver;
    }
    public boolean isMouseOver(){
        return mouseOver;
    }

    public Rectangle getBounds() {
        return bounds;
    }
    public int getId(){
        return id;
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getW(){
        return width;
    }
    public int getH(){
        return height;
    }
}
