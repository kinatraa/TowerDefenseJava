package managers;

import enemies.Enemy;
import helpz.LoadSave;
import scenes.Playing;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class EnemyManager {
    private Playing playing;
    private BufferedImage[] enemyImgs;
    private ArrayList<Enemy> enemies = new ArrayList<>();
    public EnemyManager(Playing playing){
        this.playing = playing;
        enemyImgs = new BufferedImage[8];
        addEnemy(64*3, 64*9);
        loadEnemyImgs();
    }

    private void loadEnemyImgs() {
        BufferedImage atlas = getSpriteAtlas();
        enemyImgs[0] = atlas.getSubimage(15*64, 10*64, 64, 64);
        enemyImgs[1] = atlas.getSubimage(16*64, 10*64, 64, 64);
        enemyImgs[2] = atlas.getSubimage(17*64, 10*64, 64, 64);
        enemyImgs[3] = atlas.getSubimage(18*64, 10*64, 64, 64);
//        enemyImgs[4] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[5] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[6] = atlas.getSubimage(15*32, 10*32, 32, 32);
//        enemyImgs[7] = atlas.getSubimage(15*32, 10*32, 32, 32);
    }
    private static BufferedImage getSpriteAtlas() {
        BufferedImage img = null;
        InputStream is = EnemyManager.class.getClassLoader().getResourceAsStream("atlas64px.png");
        try {
            if(is != null) img = ImageIO.read(is);
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return img;
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
