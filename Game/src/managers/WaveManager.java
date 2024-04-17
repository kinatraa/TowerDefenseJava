package managers;

import events.Wave;
import scenes.Playing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

public class WaveManager {
    private Playing playing;
    private Vector<Wave> waves = new Vector<>();
    private int enemySpawnTickLimit = 60 * 4;
    private int enemySpawnTick = enemySpawnTickLimit;
    private int enemyIndex, waveIndex;
    private int waveTickLimit = 60 * 5;
    private int waveTick = 0;
    private boolean waveTimerStart, waveTickTimerOver;
    public WaveManager(Playing playing){
        this.playing = playing;
        createWaves();
    }

    public void update(){
        if(enemySpawnTick < enemySpawnTickLimit){
            enemySpawnTick++;
        }
        if(waveTimerStart){
            waveTick++;
            if(waveTick >= waveTickLimit){
                waveTickTimerOver = true;
            }
        }
    }

    public int getNextEnemy(){
        enemySpawnTick = 0;
        if(enemyIndex < waves.get(waveIndex).getEnemyList().size()) return waves.get(waveIndex).getEnemyList().get(enemyIndex++);
        else return -1;
    }

    private void createWaves() {
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(4, 5, 4, 5, 4))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(1, 1, 1, 1, 1, 0, 0, 0, 0, 0))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 0, 0, 0, 0, 0))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 3, 3, 3, 3, 3))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(2, 2, 2, 2, 2, 2, 2, 2, 2, 2))));
        waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(3, 3, 3, 3, 3, 3, 3, 3, 3, 3))));
    }

    public Vector<Wave> getWaves() {
        return waves;
    }

    public boolean isTimeForNewEnemy() {
        return enemySpawnTick >= enemySpawnTickLimit;
    }

    public boolean isThereMoreEnemiesInWave(){
        return enemyIndex < waves.get(waveIndex).getEnemyList().size();
    }

    public boolean isThereMoreWaves() {
        return (waveIndex + 1) < waves.size();
    }

    public void startWaveTimer() {
        waveTimerStart = true;
    }

    public boolean isWaveTimerOver() {
        return waveTickTimerOver;
    }

    public void increaseWaveIndex(){
        waveIndex++;
        waveTick = 0;
        waveTickTimerOver = false;
        waveTimerStart = false;
    }

    public void resetEnemyIndex() {
        enemyIndex = 0;
    }

    public int getWaveIndex() {
        return waveIndex;
    }

    public float getTimeLeft(){
        float ticksLeft = waveTickLimit - waveTick;
        return ticksLeft / 60.0f;
    }

    public boolean isWaveTimerStarted(){
        return waveTimerStart;
    }

    public void reset(){
        waves.clear();
        createWaves();
        enemyIndex = 0;
        waveIndex = 0;
        waveTimerStart = false;
        waveTickTimerOver = false;
        waveTick = 0;
        enemySpawnTick = enemySpawnTickLimit;
    }
}
