package main;

import java.awt.*;

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
            case GAME_OVER:
                game.getGameOver().render(g);
                break;
            case VICTORY:
                game.getVictory().render(g);
                break;
        }
    }
}
