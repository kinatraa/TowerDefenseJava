package main;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class GameScreen extends JPanel {
    private GameWindow game;
    private Dimension sizeScreen;
    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;
    public GameScreen(GameWindow game){
        this.game = game;
        setPanelSize();
    }
    public void initInputs() {
        myMouseListener = new MyMouseListener(game);
        keyBoardListener = new KeyBoardListener();
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

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        game.getRender().render(g);
    }
}
