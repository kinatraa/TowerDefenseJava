package Scenes;

import main.GameWindow;

import java.awt.*;

public class Playing extends GameScene implements SceneMethods{
    public Playing(GameWindow game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.RED);
        g.fillRect(0, 0, 1280, 768);
    }
}
