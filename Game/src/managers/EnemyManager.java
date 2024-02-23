package managers;

import enemies.Enemy;
import helpz.LoadSave;
import scenes.Playing;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public EnemyManager(Playing playing){
        this.playing = playing;
        enemyImgs = new BufferedImage[8];
        addEnemy(32*3, 32*9);
        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = LoadSave.getSpriteAtlas();
        enemyImgs[0] = atlas.getSubimage(15*32, 10*32, 32, 32);
        enemyImgs[1] = atlas.getSubimage(16*32, 10*32, 32, 32);
        enemyImgs[2] = atlas.getSubimage(17*32, 10*32, 32, 32);
        enemyImgs[3] = atlas.getSubimage(18*32, 10*32, 32, 32);
//        enemyImgs[4] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[5] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[6] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[7] = atlas.getSubimage(15*32, 10*32, 32, 32);
    }

    public void update(){
        for(Enemy e : enemies){
            e.move(0.5f, 0);
        }
    }
    public void addEnemy(int x, int y){
        enemies.add(new Enemy(x, y, 0, 0));
    }
    public void draw(Graphics g){
        for (Enemy e : enemies){
            drawEnemy(e, g);
        }
    }

    private void drawEnemy(Enemy e, Graphics g) {
        g.drawImage(enemyImgs[0], (int)e.getX(), (int)e.getY(), null);
    }
}
