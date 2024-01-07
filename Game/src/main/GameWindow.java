package main;

import Scenes.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class GameWindow extends JFrame implements Runnable{
    private GameScreen gameScreen;
    private BufferedImage img;
    private final double FPS_SET = 60.0;
    private final double UPS_SET = 60.0;
    private Thread gameThread;
    private MyMouseListener myMouseListener;
    private KeyBoardListener keyBoardListener;

    //Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;

    public GameWindow(){
        initClasses();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        add(gameScreen);
        pack();
        setVisible(true);
    }

    private void initClasses() {
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
    }

    public void initInputs() {
        myMouseListener = new MyMouseListener();
        keyBoardListener = new KeyBoardListener();
        addMouseListener(myMouseListener);
        addMouseMotionListener(myMouseListener);
        addKeyListener(keyBoardListener);

        requestFocus();
    }

    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();
    }

    public void run(){
        double timePerFrame = 1e9 / FPS_SET;
        double timePerUpdate = 1e9 / UPS_SET;
        long lastFrame = System.nanoTime();
        long lastUpdate = System.nanoTime();
        long lastTimeCheck = System.currentTimeMillis();
        int frames = 0;
        int updates = 0;
        while(true) {
            // Render
            if(System.nanoTime() - lastFrame >= timePerFrame) {
                repaint();
                lastFrame = System.nanoTime();
                frames++;
            }
            // Update
            if(System.nanoTime() - lastUpdate >= timePerUpdate) {
                lastUpdate = System.nanoTime();
                updates++;
            }
            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
                System.out.println("FPS: " + frames + " | UPS: " + updates);
                frames = 0;
                updates = 0;
                lastTimeCheck = System.currentTimeMillis();
            }
        }
    }

    //Getters and setters
    public Render getRender() {
        return render;
    }
    public Menu getMenu() {
        return menu;
    }
    public Playing getPlaying() {
        return playing;
    }
    public Settings getSettings() {
        return settings;
    }
}
