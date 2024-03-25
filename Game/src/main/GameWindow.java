package main;

import helpz.LoadSave;
import managers.TileManager;
import scenes.*;

import javax.swing.*;

public class GameWindow extends JFrame implements Runnable{
    GameScreen gameScreen;
    private final double FPS_SET = 60.0;
    private final double UPS_SET = 60.0;
    private Thread gameThread;
    //Classes
    private Render render;
    private Menu menu;
    private Playing playing;
    private Settings settings;
    private Menu2 menu2;
    private Editing editing;
    private TileManager tileManager;

    public GameWindow(){
        initClasses();
        createDefaultLevel();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 768);
        setLocationRelativeTo(null);
        setResizable(false);
        add(gameScreen);
        start();
        updateGame();
        pack();
        setVisible(true);
    }
    private void createDefaultLevel() {
        int[] arr = new int[800];
        for(int i = 0; i < arr.length; i++){
            arr[i] = 0;
        }
        LoadSave.CreateLevel("new_level", arr);
    }
    private void initClasses() {
        tileManager = new TileManager();
        render = new Render(this);
        gameScreen = new GameScreen(this);
        menu = new Menu(this);
        playing = new Playing(this);
        settings = new Settings(this);
        menu2 = new Menu2(this, playing);
        editing = new Editing(this);
    }

    private void start(){
        gameThread = new Thread(this){};
        gameThread.start();
    }

    private void updateGame(){
        switch(GameStates.gameState){
            case MENU:
                break;
            case PLAYING:
                playing.update();
                break;
            case SETTINGS:
                break;
            case MENU2:
                break;
            case EDIT:
                break;
        }
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
                updateGame();
                lastUpdate = System.nanoTime();
                updates++;
            }
            if(System.currentTimeMillis() - lastTimeCheck >= 1000){
//                System.out.println("FPS: " + frames + " | UPS: " + updates);
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
    public Menu2 getMenu2(){return menu2;}
    public Editing getEditor(){return editing;}
    public TileManager getTileManager(){return tileManager;}
}
