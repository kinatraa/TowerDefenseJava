package managers;

import main.Game;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.File;

import static helpz.Constants.Towers.*;

public class SoundManager {
    private Clip menuMusic, playingMusic, cannonFire, rocketFire, coin, selection, explode;
    private boolean menuActive = false, playingActive = false;
    private float gainMusic;
    private float gainEffect;
    private final float defaultVolume = 46.0206f;
    private final float maxVolume = 6.0206f;
    private final Game game;
    private FloatControl gainControl;

    public SoundManager(Game game) {
        this.game = game;
    }

    public void update() {
        gainEffect = maxVolume - (100 - game.getSettings().getGainEffect()) * defaultVolume / 100;
        gainMusic = maxVolume - (100 - game.getSettings().getGainMusic()) * defaultVolume / 100;
        if (menuMusic != null) {
            if (menuMusic.isOpen()) {
                gainControl = (FloatControl) menuMusic.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainMusic);
            }
        }
        if (playingMusic != null) {
            if (playingMusic.isOpen()) {
                gainControl = (FloatControl) playingMusic.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainMusic);
            }
        }
    }

    public void playMenuMusic() {
        if (menuActive) return;
        menuActive = true;
        try {
            File soundPath = new File("src/sounds/menumusic.wav");
            if (soundPath.exists()) {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                menuMusic = AudioSystem.getClip();
                menuMusic.open(inputStream);
                menuMusic.loop(Clip.LOOP_CONTINUOUSLY);
                menuMusic.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void playPlayingMusic() {
        if (playingActive) return;
        playingActive = true;
        try {
            File soundPath = new File("src/sounds/playingmusic.wav");
            if (soundPath.exists()) {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                playingMusic = AudioSystem.getClip();
                playingMusic.open(inputStream);
                playingMusic.loop(Clip.LOOP_CONTINUOUSLY);
                playingMusic.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void gainCoins(float gainEffect) {
        try {
            File soundPath = new File("src/sounds/coins.wav");
            if (soundPath.exists()) {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                coin = AudioSystem.getClip();
                coin.open(inputStream);
                gainControl = (FloatControl) coin.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainEffect);
                coin.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void shootingSound(int type, float gainEffect) {
        switch (type) {
            case GREEN_CANON, RED_CANON:
                try {
                    File soundPath = new File("src/sounds/cannon.wav");
                    if (soundPath.exists()) {
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                        cannonFire = AudioSystem.getClip();
                        cannonFire.open(inputStream);
                        gainControl = (FloatControl) cannonFire.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(gainEffect);
                        cannonFire.start();
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
            case BIG_ONE, DOUBLE_RR:
                try {
                    File soundPath = new File("src/sounds/rocket.wav");
                    if (soundPath.exists()) {
                        AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                        rocketFire = AudioSystem.getClip();
                        rocketFire.open(inputStream);
                        gainControl = (FloatControl) rocketFire.getControl(FloatControl.Type.MASTER_GAIN);
                        gainControl.setValue(gainEffect);
                        rocketFire.start();
                    }
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                break;
        }
    }

    public void explodeSounds(float gainEffect) {
        try {
            File soundPath = new File("src/sounds/explosion.wav");
            if (soundPath.exists()) {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                explode = AudioSystem.getClip();
                explode.open(inputStream);
                gainControl = (FloatControl) explode.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainEffect);
                explode.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void selectionSound(float gainEffect) {
        try {
            File soundPath = new File("src/sounds/selection.wav");
            if (soundPath.exists()) {
                AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundPath);
                selection = AudioSystem.getClip();
                selection.open(inputStream);
                gainControl = (FloatControl) selection.getControl(FloatControl.Type.MASTER_GAIN);
                gainControl.setValue(gainEffect);
                selection.start();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void closeMenuMusic() {
        if (menuMusic != null && menuMusic.isRunning()) {
            menuMusic.close();
            menuActive = false;
        }

    }

    public void closePlayingMusic() {
        if (playingMusic != null && playingMusic.isRunning()) {
            playingMusic.close();
            playingActive = false;
        }
    }

    public float getGainEffect() {
        return gainEffect;
    }

    public float getGainMusic() {
        return gainMusic;
    }
}
