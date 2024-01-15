package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MyMouseListener implements MouseListener, MouseMotionListener {
    private GameWindow game;

    public MyMouseListener(GameWindow game) {
        this.game = game;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gameState){
                case MENU:
                    game.getMenu().mouseClicked(e.getX(), e.getY());
                    break;
                case PLAYING:
                    game.getPlaying().mouseClicked(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSettings().mouseClicked(e.getX(), e.getY());
                    break;
                case MENU2:
                    game.getMenu2().mouseClicked(e.getX(), e.getY());
                default:
                    break;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gameState){
                case MENU:
                    game.getMenu().mousePressed(e.getX(), e.getY());
                    break;
                case PLAYING:
                    game.getPlaying().mousePressed(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSettings().mousePressed(e.getX(), e.getY());
                    break;
                case MENU2:
                    game.getMenu2().mousePressed(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1){
            switch (GameStates.gameState){
                case MENU:
                    game.getMenu().mousePressed(e.getX(), e.getY());
                    break;
                case PLAYING:
                    game.getPlaying().mousePressed(e.getX(), e.getY());
                    break;
                case SETTINGS:
                    game.getSettings().mousePressed(e.getX(), e.getY());
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        switch (GameStates.gameState) {
            case MENU:
                game.getMenu().mouseMoved(e.getX(), e.getY());
                break;
            case PLAYING:
                game.getPlaying().mouseMoved(e.getX(), e.getY());
                break;
            case SETTINGS:
                game.getSettings().mouseMoved(e.getX(), e.getY());
                break;
            case MENU2:
                game.getMenu2().mouseMoved(e.getX(), e.getY());
                break;
            default:
                break;

        }
    }
}
