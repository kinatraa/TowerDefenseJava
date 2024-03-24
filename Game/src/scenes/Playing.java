package scenes;

import enemies.Enemy;
import helpz.Constants;
import helpz.LoadSave;
import managers.EnemyManager;
import managers.ProjectileManager;
import managers.TowerManager;
import managers.WaveManager;
import objects.PathPoint;
import objects.Tower;
import ui.MyButton;
import main.GameWindow;
import ui.ActionBar;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;

import static helpz.Constants.Tiles.*;

public class Playing extends GameScene implements SceneMethods, ImageObserver {
    private int[][] lvl;
    private ActionBar actionBar;
    private int mouseX, mouseY;
    private EnemyManager enemyManager;
    private TowerManager towerManager;
    private ProjectileManager projManager;
    private WaveManager waveManager;
    private Tower selectedTower;
    private PathPoint start, end;
    private int goldTick = 0;
    public Playing(GameWindow game) {
        super(game);
        loadDefaultLevel();
        actionBar = new ActionBar(1024, 0, 256, 768, this);
        enemyManager = new EnemyManager(this, start, end);
        towerManager = new TowerManager(this);
        projManager = new ProjectileManager(this);
        waveManager = new WaveManager(this);
    }
    public void update(){
        waveManager.update();

        goldTick++;
        if(goldTick % (60 * 3) == 0) actionBar.addGold(1);
        if(isAllEnemiesDead()){
            if(isThereMoreWaves()){
                waveManager.startWaveTimer();
                if(isWaveTimerOver()){
                    waveManager.increaseWaveIndex();
                    enemyManager.getEnemies().clear();
                    waveManager.resetEnemyIndex();
                }
            }
        }
        if(isTimeForNewEnemy()){
            spawnEnemy();
        }

        enemyManager.update();
        towerManager.update();
        projManager.update();
    }

    private boolean isWaveTimerOver() {
        return waveManager.isWaveTimerOver();
    }

    private boolean isThereMoreWaves() {
        return waveManager.isThereMoreWaves();
    }

    private boolean isAllEnemiesDead() {
        if(waveManager.isThereMoreEnemiesInWave()){
            return false;
        }
        for(Enemy e : enemyManager.getEnemies()){
            if(e.isAlive()) return false;
        }
        return true;
    }

    private void spawnEnemy() {
        enemyManager.spawnEnemy(waveManager.getNextEnemy());
    }

    private boolean isTimeForNewEnemy() {
        if(waveManager.isTimeForNewEnemy()){
            if(waveManager.isThereMoreEnemiesInWave()){
                return true;
            }
        }
        return false;
    }
    public void setSelectedTower(Tower selectedTower){
        this.selectedTower = selectedTower;
    }
    public void setLevel(int[][] lvl){
        this.lvl = lvl;
    }
    private void loadDefaultLevel() {
        lvl = LoadSave.GetLevelData("new_level");
        ArrayList<PathPoint> points = LoadSave.GetLevelPathPoints("new_level");
        start = points.get(0);
        end = points.get(1);
    }
    @Override
    public void render(Graphics g) {
        drawLevel(g);
        actionBar.draw(g);
        enemyManager.draw(g);
        towerManager.draw(g);
        projManager.draw(g);
        drawSelectedTower(g);
        drawHighlight(g);
    }

    private void drawHighlight(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 32, 32);
    }

    private void drawSelectedTower(Graphics g) {
        if(selectedTower != null){
            g.drawImage(towerManager.getTowerImgs()[selectedTower.getTowerType()], mouseX, mouseY, 32, 32, null);
        }
    }

    private void drawLevel(Graphics g){
        for(int y = 0; y < lvl.length; y++){
            for(int x = 0; x < lvl[y].length; x++){
                int id = lvl[y][x];
                g.drawImage(getSprite(id), x*32, y*32, null);
            }
        }
    }
    private BufferedImage getSprite(int spiteID){
        return getGame().getTileManager().getSprite(spiteID);
    }
    public int getTileType(int x, int y){
        int xCord = x / 32;
        int yCord = y / 32;
        if(xCord < 0 || xCord > 31) return 5;
        if(yCord < 0 || yCord > 23) return 5;

        int id = lvl[y / 32][x / 32];
        return getGame().getTileManager().getTile(id).getTileType();
    }
    @Override
    public void mouseClicked(int x, int y) {
        if(x > 1024){
            actionBar.mouseClicked(x, y);
        }
        else{
            if(selectedTower != null){
                if(isTileGrass(mouseX, mouseY) && getTowerAt(mouseX, mouseY) == null){
                    towerManager.addTower(selectedTower, mouseX, mouseY);
                    removeGold(selectedTower.getTowerType());
                    selectedTower = null;
                }
            }
            else{
                Tower t = getTowerAt(mouseX, mouseY);
                actionBar.displayTower(t);
            }
        }
    }

    private void removeGold(int towerType) {
        actionBar.payForTower(towerType);
    }

    private Tower getTowerAt(int x, int y) {
        return towerManager.getTowerAt(x, y);
    }

    private boolean isTileGrass(int x, int y) {
        int id = lvl[y/32][x/32];
        int tileType = getGame().getTileManager().getTile(id).getTileType();
        return tileType == GRASS_TILE;
    }
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
            selectedTower = null;
        }
    }
    @Override
    public void mouseClicked3() {

    }
    @Override
    public void mouseMoved(int x, int y) {
        if(x > 1024){
            actionBar.mouseMoved(x, y);
        }
        else{
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressed(int x, int y) {
        if(x > 1024){
            actionBar.mousePressed(x, y);
        }
    }

    @Override
    public void mouseReleased(int x, int y) {
        actionBar.mouseReleased(x, y);
    }

    @Override
    public void mouseDragged(int x, int y) {
    }

    @Override
    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
        return false;
    }

    public TowerManager getTowerManager() {
        return towerManager;
    }

    public EnemyManager getEnemyManager() {
        return enemyManager;
    }

    public WaveManager getWaveManager() {
        return waveManager;
    }

    public void shootEnemy(Tower t, Enemy e){
        projManager.newProjectile(t, e);
        towerManager.trackingEnemy(t, e);
    }

    public void reward(int enemyType) {
        actionBar.addGold(Constants.Enemies.GetReward(enemyType));
    }
}