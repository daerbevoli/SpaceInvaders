package be.uantwerpen.fti.ei.Abstract;

import be.uantwerpen.fti.ei.J2D.GraphicsContext;

/**
 * The AbstractFactory class.
 * This class is a factory creator. <br>
 * It consists of abstract methods to create the entities and an abstract render method.
 */

public abstract class AbstractFactory {
    public abstract AbstractSpaceship createSpaceship(int x, int y);
    public abstract AbstractAlien createAlien(int x, int y);
    public abstract AbstractMissile createMissile(int x, int y);
    public abstract AbstractBeam createBeam(int x, int y, int fireSpeed);
    public abstract AbstractLife createLife(int x, int y);
    public abstract AbstractBarrier createBarrier(int x, int y, int lives);
    public abstract AbstractBonus createBonus(int x, int y);
    public abstract GraphicsContext getGraphics();
    public abstract void render();
}
