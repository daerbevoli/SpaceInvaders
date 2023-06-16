package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractAlien;
import be.uantwerpen.fti.ei.Abstract.Entity;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a superclass of AbstractAlien. It defines the Alien object. <br>
 * It implements the Constants interface to use the constants.
 */

public class Alien extends AbstractAlien implements Constants {

    private boolean movingLeft = false;
    private List<Beam> beams;
    private int fireSpeed;

    private SoundComponent sc;
    private Clip beamSound;


    /**
     * Creates the Alien object.
     * @param x x-position.
     * @param y y-position.
     */
    public Alien(int x, int y) {
        super(x, y);
        initAlien();
        initSound();
    }

    /**
     * This function loads in the image, gets its dimension and initialized the beams list.
     */
    public void initAlien() {
        loadImage("src/images/enemyLvl1.png");
        getImageDimensions();
        beams = new ArrayList<>(1);

    }

    /**
     * This function initializes the sound component and sets the beam sound.
     */
    private void initSound(){
        try {
            sc = new SoundComponent();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        beamSound = sc.getBeamSound();
    }

    /**
     * Returns the beams list.
     * @return The beams list.
     */
    public List<Beam> getBeams(){
        return beams;
    }

    /**
     * Adds beam object to the list if the list is empty. <br>
     * Plays the beam sound.
     */
    public void fireBeam(){
        beamSound.start();
        if (beams.isEmpty()) {
            beamSound.stop();
            beamSound.setFramePosition(0);
            beams.add(new Beam(x, y, fireSpeed));
        }
    }

    /**
     * Returns the fireSpeed.
     * @return The fireSpeed.
     */
    public int getFireSpeed(){
        return fireSpeed;
    }

    /**
     * Sets the fireSpeed.
     * @param fireSpeed The fireSpeed.
     */
    public void setFireSpeed(int fireSpeed){
        this.fireSpeed = fireSpeed;
    }

    /**
     * Moves the x-position to the right.
     */
    public void moveRight() {
        x += 1;
    }

    /**
     * Moves the x-position to the left.
     */
    public void moveLeft(){
        x -= 1;
    }

    /**
     * Moves the y-position down.
     */
    public void moveDown(){
        y += 3;
    }

    /**
     * Returns the isMovingLeft flag.
     * @return The isMovingLeft flag.
     */
    public boolean isMovingLeft(){
        return movingLeft;
    }

    /**
     * Sets the isMovingLeft flag.
     * @param movingLeft The isMovingLeft flag.
     */
    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }

    /**
     * 1 or 0 if the x position crosses the defined limits.
     * @return 1 or 0
     */
    public boolean hitWall() {
        return (x < 7 || x > screenWidth - 38);
    }

    public boolean checkCollision(Entity entity){
        return this.getBounds().intersects(entity.getBounds());
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
