package be.uantwerpen.fti.ei.Abstract;

import java.awt.*;

/**
 * This class is an abstract superclass of Entity.<br>
 * It implements only one method: the abstract draw method
 */

public abstract class AbstractBarrier extends Entity {

    protected int lives;

    /**
     * We cannot define an instance of this object since it is an abstract class.
     * @param x The x-position.
     * @param y The y-position.
     */
    public AbstractBarrier(int x, int y, int lives) {
        super(x, y);
        this.lives = lives;
    }

    /**
     * Overridden in the superclass.
     * @param g, Allows drawing.
     */
    public abstract void draw(Graphics g);
}
