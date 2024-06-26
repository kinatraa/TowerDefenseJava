package ui;

import helpz.Constants;
import managers.SoundManager;
import objects.Tile;
import objects.Tower;
import scenes.Playing;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

import static helpz.Constants.Towers.*;
import static main.GameStates.*;

public class ActionBar extends Bar {
    private MyButton bMenu2;
    private Playing playing;
    private SoundManager soundManager;
    private MyButton[] towerButtons;
    private MyButton sellTower, upgradeTower;
    private Tower selectedTower;
    private Tower displayedTower;
    private DecimalFormat formatter;
    private int gold = 500;
    private boolean showTowerCost;
    private int towerCostType;
    private int lives = 5;

    public ActionBar(int x, int y, int width, int height, Playing playing) {
        super(x, y, width, height);
        this.playing = playing;
        soundManager = playing.getGame().getSoundManager();
        formatter = new DecimalFormat("0.0");
        initButtons();
    }

    private void initButtons() {
        bMenu2 = new MyButton("Menu2", 1211, 5, 64, 64);
        towerButtons = new MyButton[4];
        int yStart = 100;
        int yOffset = 80;
        int xStart = 1032;
        int xOffset = 40;
        int index = 0, cnt = 0;
        for (int i = 0; i < towerButtons.length; i++) {
            towerButtons[i] = new MyButton("", xStart, yStart + yOffset * i, 64, 64, i);
        }

        sellTower = new MyButton("Sell", 1042, 590, 60, 30);
        upgradeTower = new MyButton("Upgrade", 1132, 590, 90, 30);
    }

    public void removeOneLife() {
        lives--;
        if (lives <= 0) {
            SetGameState(GAME_OVER);
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.decode("#48ba00"));
        g.fillRect(x, y, width, height);
        drawButtons(g);
        drawDisplayedTower(g);
        drawWaveInfo(g);
        drawGoldAmount(g);
        if (showTowerCost) drawTowerCost(g);
        drawLives(g);
    }

    private void drawLives(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString("Lives: " + lives, 50, 50);
    }

    private void drawTowerCost(Graphics g) {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(1032, 420, 200, 80);
        g.setColor(Color.BLACK);
        g.drawRect(1032, 420, 200, 80);
        g.setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString(Constants.Towers.GetName(towerCostType), 1050, 450);
        g.drawString("Cost: " + Constants.Towers.GetCost(towerCostType) + "$", 1050, 480);

        if (Constants.Towers.GetCost(towerCostType) > gold) {
            g.setColor(Color.RED);
            g.drawString("Not enough gold!", 1032, 80);
        }
    }

    public void payForTower(int towerType) {
        this.gold -= Constants.Towers.GetCost(towerType);
    }

    private void drawGoldAmount(Graphics g) {
        g.setFont(new Font("Impact", Font.PLAIN, 20));
        g.drawString("Gold: " + gold + "$", 1032, 50);
    }

    private void drawWaveInfo(Graphics g) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Impact", Font.PLAIN, 20));
        drawWaveTimerInfo(g);
        drawEnemiesLeftInfo(g);
        drawWavesLeftInfo(g);
    }

    private void drawWavesLeftInfo(Graphics g) {
        int current = playing.getWaveManager().getWaveIndex();
        int size = playing.getWaveManager().getWaves().size();
        g.drawString("Wave " + (current + 1) + " / " + size, 1050, 710);
    }

    private void drawEnemiesLeftInfo(Graphics g) {
        int remaining = playing.getEnemyManager().getAmountOfAliveEnemies();
        g.drawString("Enemies Left: " + remaining, 1050, 740);
    }

    private void drawWaveTimerInfo(Graphics g) {
        if (playing.getWaveManager().isWaveTimerStarted()) {
            float timeLeft = playing.getWaveManager().getTimeLeft();
            String formatedText = formatter.format(timeLeft);
            g.drawString("Time left: " + formatedText, 1050, 680);
        }
    }

    private void drawDisplayedTower(Graphics g) {
        if (displayedTower != null) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(1032, 520, 220, 130);
            g.setColor(Color.BLACK);
            g.drawRect(1032, 520, 220, 130);
            g.drawRect(1042, 530, 50, 50);
            g.drawImage(playing.getTowerManager().getTowerImgs()[displayedTower.getTowerType()], 1042, 530, 50, 50, null);
            g.setFont(new Font("Impact", Font.PLAIN, 15));
            g.drawString("" + Constants.Towers.GetName(displayedTower.getTowerType()), 1118, 545);
            g.drawString("ID: " + displayedTower.getId(), 1118, 570);
            g.drawString("Tier: " + displayedTower.getTier(), 1168, 570);

            drawDisplayedTowerBorder(g);
            drawDisplayedTowerRange(g);

            sellTower.draw(g);
            if (displayedTower.getTier() < 3) {
                upgradeTower.draw(g);
            }

            if (sellTower.isMouseOver()) {
                g.setColor(Color.RED);
                g.drawString("Sell for: " + getSellAmount(displayedTower) + "$", 1052, 640);
            } else if (upgradeTower.isMouseOver() && displayedTower.getTier() < 3) {
                g.setColor(Color.BLUE);
                g.drawString("Upgrade for: " + getUpgradeAmount(displayedTower) + "$", 1052, 640);
            }
        }
    }

    private int getUpgradeAmount(Tower displayedTower) {
        return 10;
    }

    private int getSellAmount(Tower displayedTower) {
        return (int) ((Constants.Towers.GetCost(displayedTower.getId()) + displayedTower.getTier() * 10) * 0.8);
    }

    private void drawDisplayedTowerRange(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawOval(displayedTower.getX() + 16 - (int) displayedTower.getRange() * 2 / 2, displayedTower.getY() + 16 - (int) displayedTower.getRange() * 2 / 2, (int) displayedTower.getRange() * 2, (int) displayedTower.getRange() * 2);
    }

    private void drawDisplayedTowerBorder(Graphics g) {
        g.setColor(Color.CYAN);
        g.drawRect(displayedTower.getX(), displayedTower.getY(), 32, 32);
    }

    public void displayTower(Tower t) {
        displayedTower = t;
    }

    private void drawButtons(Graphics g) {
        ImageIcon imageIcon = new ImageIcon("src/imgs/gear.png");
        Image image = imageIcon.getImage();
        g.drawImage(image, 1211, 5, 64, 64, null);
        for (MyButton b : towerButtons) {
            g.setColor(Color.LIGHT_GRAY);
            g.fillRect(b.getX(), b.getY(), b.getW(), b.getH());
            g.drawImage(playing.getTowerManager().getTowerImgs()[b.getId()], b.getX(), b.getY(), b.getW(), b.getH(), null);
            drawButtonFeedback(g, b);
        }
    }

    public void mouseClicked(int x, int y) {
        if (bMenu2.getBounds().contains(x, y)) {
            bMenu2.resetBooleans();
            soundManager.selectionSound(soundManager.getGainEffect());
            SetGameState(MENU2);
        } else {
            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) {
                    soundManager.selectionSound(soundManager.getGainEffect());
                    sellTowerClicked();
                    return;
                }
                if (displayedTower.getTier() < 3) {
                    if (upgradeTower.getBounds().contains(x, y) && gold >= 10) {
                        soundManager.selectionSound(soundManager.getGainEffect());
                        upgradeTowerClicked();
                        return;
                    }
                }
            }
            for (MyButton b : towerButtons) {
                b.resetBooleans();
                if (b.getBounds().contains(x, y)) {
                    if (!isGoldEnough(b.getId())) return;
                    soundManager.selectionSound(soundManager.getGainEffect());
                    selectedTower = new Tower(0, 0, -1, b.getId());
                    playing.setSelectedTower(selectedTower);
                    return;
                }
            }
        }
    }

    private void upgradeTowerClicked() {
        gold -= getUpgradeAmount(displayedTower);
        playing.upgradeTower(displayedTower);
    }

    private void sellTowerClicked() {
        playing.removeTower(displayedTower);
        gold += (int) (Constants.Towers.GetCost(displayedTower.getId()) * 0.8);
        displayedTower = null;
    }

    private boolean isGoldEnough(int id) {
        return gold >= Constants.Towers.GetCost(id);
    }

    public void mouseMoved(int x, int y) {
        bMenu2.setMouseOver(false);
        showTowerCost = false;
        sellTower.setMouseOver(false);
        upgradeTower.setMouseOver(false);
        for (MyButton b : towerButtons) {
            b.setMouseOver(false);
        }
        if (bMenu2.getBounds().contains(x, y)) {
            bMenu2.setMouseOver(true);
        } else {
            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) sellTower.setMouseOver(true);
                else if (upgradeTower.getBounds().contains(x, y)) upgradeTower.setMouseOver(true);
            }
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMouseOver(true);
                    showTowerCost = true;
                    towerCostType = b.getId();
                    return;
                }
            }
        }
    }

    public void mousePressed(int x, int y) {
        bMenu2.setMousePressed(false);
        sellTower.setMousePressed(false);
        upgradeTower.setMousePressed(false);
        for (MyButton b : towerButtons) {
            b.setMousePressed(false);
        }
        if (bMenu2.getBounds().contains(x, y)) {
            bMenu2.setMousePressed(true);
        } else {
            if (displayedTower != null) {
                if (sellTower.getBounds().contains(x, y)) sellTower.setMousePressed(true);
                else if (upgradeTower.getBounds().contains(x, y)) upgradeTower.setMousePressed(true);
            }
            for (MyButton b : towerButtons) {
                if (b.getBounds().contains(x, y)) {
                    b.setMousePressed(true);
                    return;
                }
            }
        }
    }

    public void mouseReleased(int x, int y) {
        bMenu2.resetBooleans();
        showTowerCost = false;
        sellTower.resetBooleans();
        upgradeTower.resetBooleans();
        for (MyButton b : towerButtons) {
            b.resetBooleans();
        }
    }

    public void addGold(int reward) {
        this.gold += reward;
    }

    public void setShowTowerCost(boolean showTowerCost) {
        this.showTowerCost = showTowerCost;
    }

    public int getLives() {
        return lives;
    }

    public void resetEverything() {
        lives = 5;
        towerCostType = 0;
        showTowerCost = false;
        gold = 500;
        selectedTower = null;
        displayedTower = null;
    }
}
