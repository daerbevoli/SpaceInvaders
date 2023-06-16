package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractSpaceship;

import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is a superclass of AbstractSpaceship. It defines the Spaceship object. <br>
 * It implements the Constants interface to use the constants.
 */

public class Spaceship extends AbstractSpaceship implements Constants {

    private int dx;
    private List<Missile> missiles;

    private SoundComponent sc;

    private Clip msSound;
    private Clip hitSound;



    /**
     * Creates the Spaceship object.
     * @param x x-position.
     * @param y y-position.
     */
    public Spaceship(int x, int y) {
        super(x, y);
        initSpaceShip();
        initSound();

    }
    /**
     * This function loads in the image, gets its dimension and initialized the missiles list.
     */
    private void initSpaceShip() {
        loadImage("src/images/ship.png");
        getImageDimensions();
        missiles = new ArrayList<>();


    }

    private void initSound(){
        try {
            sc = new SoundComponent();
        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
        msSound = sc.getMissileSound();
        hitSound = sc.getDamageSound();
    }

    /**
     * Moves the spaceship.
     * If it goes beyond the screen dimension it set the x and y position to the edge position.
     */
    public void move() {
        x += dx;

        if (x < 10){
            x = 10;
        }
        if (x > screenWidth - 36){ // need screen width constant added
            x = screenWidth - 36;
        }
    }

    /**
     * Returns the missiles list.
     * @return The missiles list.
     */
    public List<Missile> getMissiles() {
        return missiles;
    }

    /**
     * This method gets called from the game keyPressed function. <br>
     * If the missiles size is less than 3 it calls the fire function when space bar is pressed. <br>
     * If the left or right arrow key is pressed it sets dx to the speed in that direction.
     * @param e keyEvent, used to get keyCode.
     */
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE) {
            if (missiles.size() < 3) { // make 3 a constant ammunition
                fire();
                msSound.start();
            }
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 3;
        }
    }

    /**
     * This function is called from the keyPressed function. It adds a Missile object to the missiles list.
     */
    public void fire() {
        missiles.add(new Missile(x, y));
    }

    /**
     * This function resets dx so that when the arrow key is released, the spaceship does not move.
     * @param e keyEvent, used to get keyCode.
     */
    public void keyReleased(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_SPACE){
            msSound.stop();
            msSound.setFramePosition(0);
        }

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }

    /**
     * plays the hitSound when the ship is hit.
     */
    public void hit(){
        hitSound.start();
        if (x == screenWidth / 2 && y == screenHeight - 50){
            hitSound.stop();
        }
        hitSound.setFramePosition(0);

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
