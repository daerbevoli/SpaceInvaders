package be.uantwerpen.fti.ei.Abstract;

import java.awt.*;

/**
 * This class is an abstract superclass of Entity.<br>
 * It implements only one method: the abstract draw method
 */

public abstract class AbstractMissile extends Entity {

    /**
     * We cannot define an instance of this object since it is an abstract class.
     * @param x The x-position.
     * @param y The y-position.
     */
    public AbstractMissile(int x, int y) {
        super(x, y);
    }

    /**
     * Overridden in the superclass.
     * @param g, Allows drawing.
     */
    public abstract void draw(Graphics g);
}

