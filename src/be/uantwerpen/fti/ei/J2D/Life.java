package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractLife;

import java.awt.*;

/**
 * This class is a superclass of AbstractLife. It defines the Life object. <br>
 * It implements the Constants interface to use the constants.
 */

public class Life extends AbstractLife {

    /**
     * Creates the Life object.
     * @param x x-position.
     * @param y y-position.
     */
    public Life(int x, int y) {
        super(x, y);
        initLife();
    }

    /**
     * This function loads in the image and gets its dimension.
     */
    private void initLife(){
        loadImage("src/images/life.png");
        getImageDimensions();
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
