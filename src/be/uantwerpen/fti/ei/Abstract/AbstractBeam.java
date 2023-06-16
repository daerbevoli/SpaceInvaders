package be.uantwerpen.fti.ei.Abstract;

import be.uantwerpen.fti.ei.J2D.Constants;

import java.awt.*;

/**
 * This class is a subclass for a Beam object.<br>
 * It defines the abstract draw method.
 */

public abstract class AbstractBeam implements Constants {

    protected int x;
    protected int y;
    protected int fireSpeed;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    /**
     * We cannot define an instance of this object since it is an abstract class.
     * @param x The x-position.
     * @param y The y-position.
     * @param fireSpeed The speed at which the beam moves.
     */
    public AbstractBeam(int x, int y, int fireSpeed) {
        this.x = x;
        this.y = y;
        this.fireSpeed = fireSpeed;
        visible = true;
    }

    /**
     * Returns the image.
     * @return Image.
     */
    public Image getImage() {
        return image;
    }

    /**
     * Sets the image.
     * @param image The new image.
     */
    public void setImage(Image image){
        this.image = image;
    }

    /**
     * Return the x-position.
     * @return The x-position.
     */
    public int getX() {
        return x;
    }

    /**
     * Return the y-position.
     * @return The y-position.
     */
    public int getY() {
        return y;
    }

    /**
     * Sets the x-position.
     * @param x The new x-position.
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * Sets the y-position.
     * @param y The new y-position.
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * Returns the width.
     * @return The width.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Sets the width.
     * @param width The width.
     */
    public void setWidth(int width) {
        this.width = width;
    }

    /**
     * Returns the height.
     * @return The height.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Sets the height.
     * @param height The height.
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * Returns the visible flag.
     * @return The visible flag.
     */
    public boolean isVisible() {
        return visible;
    }

    /**
     * Sets the visible flag.
     * @param visible The visible flag.
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * Return the bounds of the image.
     * @return The bounds of the image.
     */
    public Rectangle getBounds(){
        return new Rectangle(x, y, width, height);
    }

    /**
     * Moves the y-position at the speed of fireSpeed.<br>
     * If y exceeds the screenHeight, the visible flag is cleared.
     */
    public void move(){
        y += fireSpeed;

        if (y > screenHeight){
            visible = false;
        }

    }

    /**
     * Overridden in the superclass.
     * @param g, Allows drawing.
     */
    public abstract void draw(Graphics g);
}
