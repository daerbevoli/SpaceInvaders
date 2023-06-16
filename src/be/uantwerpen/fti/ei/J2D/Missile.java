package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractMissile;
import java.awt.*;

/**
 * This class is a superclass of AbstractMissile. It defines the Missile object. <br>
 * It implements the Constants interface to use the constants.
 */


public class Missile extends AbstractMissile {
    private final int missileSpeed = 2;

    /**
     * Creates the Missile object.
     * @param x x-position.
     * @param y y-position.
     */
    public Missile(int x, int y) {
        super(x, y);
        initMissile();
    }

    /**
     * This function loads in the image and gets its dimension.
     */
    private void initMissile() {

        loadImage("src/images/bullet.png");
        getImageDimensions();
    }

    /**
     * Moves the missile. <br>
     * If it goes beyond the 0, the visible flag is cleared.
     */
    public void move() {

        y -= missileSpeed;

        if (y < 0) {
            setVisible(false);
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
