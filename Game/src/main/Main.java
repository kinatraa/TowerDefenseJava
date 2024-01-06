package main;
import javax.swing.*;
    
public class Main extends JFrame {
    public static void main(String[] args) {
        GameWindow game = new GameWindow();
        game.initInputs();
        game.run();
    }
}