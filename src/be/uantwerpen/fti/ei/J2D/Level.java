package be.uantwerpen.fti.ei.J2D;


import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * This class is a class of its own. It defines the Level object. <br>
 * This class is used to play sounds at the transitioning of levels and at the end of the game.
 */
public class Level {

    private int level;

    private SoundComponent sc;
    private Clip lvlUpSound;
    private Clip winSound;
    private Clip loseSound;

    private boolean gameWon;
    private boolean dead;
    private boolean startLevel;


    /**
     * Creates the Level object and sets the flags.
     * @param level The level.
     */
    public Level(int level){
        this.level = level;
        initSounds();
        startLevel = true;
        gameWon = false;
        dead = false;
    }

    /**
     * This function loads the sound component and sets the sounds of the respective clips.
     */
    private void initSounds(){
        try {
            sc = new SoundComponent();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        lvlUpSound = sc.getLvlUpSound();
        winSound = sc.getWinSound();
        loseSound = sc.getLoseSound();
    }

    /**
     * Returns the level.
     * @return The level.
     */
    public int getLevel() {
        return level;
    }

    /**
     * Sets the level.
     * @param level The level.
     */
    public void setLevel(int level) {
        this.level = level;
    }

    /**
     * Returns the StartLevel flag.
     * @return The StartLevel flag.
     */
    public boolean isStartLevel() {
        return startLevel;
    }

    /**
     * Sets the StartLevel flag.
     * @param startLevel The Startlevel flag.
     */
    public void setStartLevel(boolean startLevel) {
        this.startLevel = startLevel;
    }

    /**
     * Returns the GameWon flag.
     * @return The GameWon flag.
     */
    public boolean isGameWon() {
        return gameWon;
    }

    /**
     * Sets the GameWon flag.
     * @param gameWon The GameWon flag.
     */
    public void setGameWon(boolean gameWon) {
        this.gameWon = gameWon;
    }

    /**
     * Returns the dead flag.
     * @return The dead flag.
     */
    public boolean isDead() {
        return dead;
    }

    /**
     * Sets the dead flag.
     * @param dead The dead flag.
     */
    public void setDead(boolean dead) {
        this.dead = dead;
    }


    /**
     * This functions plays a sound when going up a level.
     */
    public void updateLvl(){
        lvlUpSound.start();
        if (startLevel) {
            lvlUpSound.stop();
        }
        lvlUpSound.setFramePosition(0);
    }

    /**
     * This function plays a win or lose sounds depending on the outcome.
     */
    public void updateWin(){
        if (gameWon){
            winSound.start();
            loseSound.stop();
            loseSound.setFramePosition(0);
        }
        if (dead) {
            loseSound.start();
            winSound.stop();
            winSound.setFramePosition(0);
        }
    }
}
