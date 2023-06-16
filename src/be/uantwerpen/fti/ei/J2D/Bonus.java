package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractBonus;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;

/**
 * This class is a superclass of AbstractBonus. It defines the Bonus object. <br>
 * It implements the Constants interface to use the constants.
 */

public class Bonus extends AbstractBonus implements Constants {

    private boolean isHit;

    private SoundComponent sc;
    private Clip bsSound;

    /**
     * Creates the bonus object.
     * @param x x-position
     * @param y y-position
     */
    public Bonus(int x, int y) {
        super(x, y);
        initBonus();
        initSound();
        isHit = false;
    }

    /**
     * This function loads in the image and gets its dimension.
     */
    private void initBonus(){
        loadImage("src/images/bonus.png");
        getImageDimensions();
    }

    /**
     * This function initializes the sound component and sets the bonus sound.
     */
    private void initSound(){
        try {
            sc = new SoundComponent();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        bsSound = sc.getBonusSound();
    }

    /**
     * Returns the isHit flag.
     * @return The isHit flag.
     */
    public boolean isHit(){
        return isHit;
    }

    /**
     * Sets the isHit flag.
     * @param hit The isHit flag.
     */
    public void setHit(boolean hit){
        this.isHit = hit;
    }

    /**
     * This function plays the bonus sound when the bonus is hit.
     */
    public void BonusHit(){
        if (isHit) {
            bsSound.start();
            if (!visible) {
                bsSound.stop();
            }
            bsSound.setFramePosition(0);
        }

    }

    /**
     * Moves the bonus. <br>
     * If it goes beyond the screenWidth, the visible flag is cleared.
     */
    public void move(){
        this.x += 1;
        if (this.x > screenWidth){
            this.setVisible(false);
        }
    }

    /**
     * Draws image.
     * @param g, Allows drawing.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        if (visible){
            g2d.drawImage(image, x, y, null);
        }
    }


}
