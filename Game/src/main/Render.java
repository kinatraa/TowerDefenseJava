package main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Render {
    private GameWindow game;

    public Render(GameWindow game){
        this.game = game;

    }
    public void render(Graphics g){
        switch(GameStates.gameState) {
            case MENU:
                game.getMenu().render(g);
                break;
            case PLAYING:
                game.getPlaying().render(g);
                break;
            case SETTINGS:
                game.getSettings().render(g);
                break;
            case MENU2:
                game.getMenu2().render(g);
                break;
            case EDIT:
                game.getEditor().render(g);
                break;
        }
    }
}
