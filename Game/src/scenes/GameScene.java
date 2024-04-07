package scenes;

import main.GameWindow;

public abstract class GameScene {
    private GameWindow game;
    public GameScene(GameWindow game) {
        this.game = game;
    }
    public GameWindow getGame() {
        return game;
    }
    public abstract void mouseClicked3();
}
