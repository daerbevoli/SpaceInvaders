package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.*;

/**
 * The J2DFactory is a superclass of the AbstractFactory class. <br>
 * Defines the visualization package. <br>
 * Overrides the methods of the AbstractFactory class to return visualization specific entities. <br>
 * The methods of the AbstractFactory subclass are overridden to return the visualization specific entity.
 */

public class J2DFactory extends AbstractFactory {
    private final GraphicsContext grcon;

    /**
     * Creates the J2D factory
     * @param gtx The graphics context to use as visualization.
     */
    public J2DFactory(GraphicsContext gtx){
        this.grcon = gtx;
    }

    @Override
    public AbstractSpaceship createSpaceship(int x, int y) {
        return new Spaceship(x, y);
    }

    @Override
    public AbstractAlien createAlien(int x, int y) {
        return new Alien(x, y);
    }

    @Override
    public AbstractMissile createMissile(int x, int y) {
        return new Missile(x, y);
    }

    @Override
    public AbstractBeam createBeam(int x, int y, int fireSpeed) {
        return new Beam(x, y, fireSpeed);
    }

    @Override
    public AbstractLife createLife(int x, int y) {
        return new Life(x, y);
    }

    @Override
    public AbstractBarrier createBarrier(int x, int y, int lives) {
        return new Barrier(x, y, lives);
    }

    @Override
    public AbstractBonus createBonus(int x, int y) {
        return new Bonus(x, y);
    }

    @Override
    public GraphicsContext getGraphics() {
        return grcon;
    }

    @Override
    public void render() {
        grcon.render();
    }
}
