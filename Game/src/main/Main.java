package main;

import javax.swing.*;

public class Main extends JFrame {
    private GameScreen gameScreen;

    public static void main(String[] args) {
        GameWindow game = new GameWindow();
        game.gameScreen.initInputs();
        game.run();
    }
}