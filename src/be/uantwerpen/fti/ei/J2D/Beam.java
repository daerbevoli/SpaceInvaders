package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractBeam;

import javax.swing.*;
import java.awt.*;

/**
 * This class is a superclass of AbstractBeam. It defines the Beam object. <br>
 * It implements the Constants interface to use the constants. <br>
 * This class is not a superclass of the Entity class, hence why a lot of the methods are redefined.
 */

public class Beam extends AbstractBeam {

    /**
     * Creates the Beam object.
     * @param x x-position.
     * @param y y-position.
     * @param fireSpeed The speed at which the beam moves.
     */
    public Beam(int x, int y, int fireSpeed) {
        super(x, y, fireSpeed);
        initFire();
    }

    /**
     * This function loads in the image and gets its dimension manually.
     */
    private void initFire() {

        image = new ImageIcon("src/images/enemyFire.png").getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
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
