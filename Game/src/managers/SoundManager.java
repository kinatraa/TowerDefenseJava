package managers;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

import static helpz.Constants.Towers.*;

public class SoundManager {
    private Clip menuMusic, playingMusic, cannonFire, rocketFire, coin, selection, explode;
    private boolean menuActive = false, playingActive = false;
    private float gain = -20f;
    public void playMenuMusic(){
        if(menuActive) return;
        menuActive = true;
        try {
            File soundPath = new File("src/sounds/menumusic.wav");
            if(soundPath.exists()){
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                menuMusic = AudioSystem.getClip();
                menuMusic.open(inputStream);
                FloatControl gainControl = (FloatControl) menuMusic.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15);
                menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
//                Thread.sleep(10000);
                menuMusic.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void playPlayingMusic(){
        if(playingActive) return;
        playingActive = true;
        try {
            File soundPath = new File("src/sounds/playingmusic.wav");
            if(soundPath.exists()){
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                playingMusic = AudioSystem.getClip();
                playingMusic.open(inputStream);
                FloatControl gainControl = (FloatControl) playingMusic.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-15);
                playingMusic.loop(Clip.LOOP_CONTINUOUSLY);
//                Thread.sleep(10000);
                playingMusic.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void gainCoins(){
        try {
            File soundPath = new File("src/sounds/coins.wav");
            if(soundPath.exists()){
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                coin = AudioSystem.getClip();
                coin.open(inputStream);
                coin.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void shootingSound(int type){
        switch (type){
            case GREEN_CANON, RED_CANON:
                try {
                    File soundPath = new File("src/sounds/cannon.wav");
                    if(soundPath.exists()){
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                        cannonFire = AudioSystem.getClip();
                        cannonFire.open(inputStream);
                        cannonFire.start();
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
            case BIG_ONE, DOUBLE_RR:
                try {
                    File soundPath = new File("src/sounds/rocket.wav");
                    if(soundPath.exists()){
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                        rocketFire = AudioSystem.getClip();
                        rocketFire.open(inputStream);
                        FloatControl gainControl = (FloatControl) rocketFire.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(-5);
                        rocketFire.start();
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
        }
    }

    public void explodeSounds(){
        try {
            File soundPath = new File("src/sounds/explosion.wav");
            if(soundPath.exists()){
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                explode = AudioSystem.getClip();
                explode.open(inputStream);
                FloatControl gainControl = (FloatControl) explode.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gain);
                explode.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void selectionSound(){
        try {
            File soundPath = new File("src/sounds/selection.wav");
            if(soundPath.exists()){
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                selection = AudioSystem.getClip();
                selection.open(inputStream);
                FloatControl gainControl = (FloatControl) selection.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(-20);
                selection.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void closeMenuMusic(){
        if(menuMusic != null && menuMusic.isRunning()){
            menuMusic.close();
            menuActive = false;
        }

    }

    public void closePlayingMusic(){
        if(playingMusic != null && playingMusic.isRunning()){
            playingMusic.close();
            playingActive = false;
        }
    }
}
