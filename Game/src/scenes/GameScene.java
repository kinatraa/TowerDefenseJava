package scenes;

import main.GameWindow;

public class GameScene {
    private GameWindow game;
    public GameScene(GameWindow game) {
        this.game = game;
    }
    public GameWindow getGame() {
        return game;
    }
}
