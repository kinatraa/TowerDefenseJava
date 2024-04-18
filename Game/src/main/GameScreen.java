package main;

import input.KeyBoardListener;
import input.MyMouseListener;

import javax.swing.*;
import java.awt.*;

public class GameScreen extends JPanel {
    private Game game;
    private Dimension sizeScreen;
    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;

    public GameScreen(Game game) {
        this.game = game;
        setPanelSize();
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyBoardListener = new KeyBoardListener(game);
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyBoardListener);
        requestFocus();
    }

    private void setPanelSize() {
        sizeScreen = new Dimension(1280, 768);
        setMinimumSize(sizeScreen);
        setMaximumSize(sizeScreen);
        setPreferredSize(sizeScreen);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        game.getRender().render(g);
    }
}
