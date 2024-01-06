package Scenes;

import main.GameWindow;

import java.awt.*;

public class Settings extends GameScene implements SceneMethods{
    public Settings(GameWindow game) {
        super(game);
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 1280, 768);
    }
}
