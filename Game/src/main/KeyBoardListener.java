package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import static main.GameStates.*;

public class KeyBoardListener implements KeyListener {
    private GameWindow game;
    public KeyBoardListener(GameWindow game){
        this.game = game;
    }
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
//        if(gameState == EDIT){
//            game.getEditor().keyPress(e);
//        }
        if(gameState == PLAYING){
            game.getPlaying().keyPressed(e);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
