package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractBarrier;

import java.awt.*;

/**
 * This class is a superclass of AbstractBarrier. It defines the Barrier object. <br>
 * It implements the Constants interface to use the constants.
 */

public class Barrier extends AbstractBarrier implements Constants{

    /**
     * Creates the Barrier object.
     * @param x x-position.
     * @param y y-position.
     * @param lives The amount of lives before the barrier is destroyed.
     */
    public Barrier(int x, int y, int lives) {
        super(x, y, lives);
        initBarrier();

    }

    /**
     * This function loads in the image and gets its dimension.
     */
    public void initBarrier(){
        loadImage("src/images/barrier.png");
        getImageDimensions();
    }

    /**
     * Returns the lives.
     * @return The lives.
     */
    public int getLives() {
        return lives;
    }

    /**
     * Sets the lives.
     * @param lives The lives.
     */
    public void setLives(int lives){
        this.lives = lives;
    }

    /**
     * Draws image.
     * @param g, Allows drawing.
     */
    @Override
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.drawImage(image, x, y, null);
        g2d.setColor(Color.WHITE);
        Font font = new Font("Helvetica", Font.BOLD, 10);
        g2d.setFont(font);
        g2d.drawString(Integer.toString(lives), x+20, y+10);
    }
}
