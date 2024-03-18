package scenes;

import main.GameWindow;

public abstract class GameScene {
    private GameWindow game;
    protected int animationIndex;
    protected int ANIMATION_SPEED = 25;
    protected int tick;
    public GameScene(GameWindow game) {
        this.game = game;
    }
    public GameWindow getGame() {
        return game;
    }
    protected void updateTick() {
        tick++;
        if (tick >= ANIMATION_SPEED) {
            tick = 0;
            animationIndex++;
            if (animationIndex >= 4)
                animationIndex = 0;
        }
    }
    public abstract void mouseClicked3();
}
