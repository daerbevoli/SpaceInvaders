package be.uantwerpen.fti.ei.Abstract;

import javax.swing.*;
import java.awt.*;

/**
 * The Entity class.<br>
 * This is the superclass of all the entities.<br>
 * Defines the x- and y-position, width, height, image and visibility of the entity.<br>
 * The visibility flag is to choose whether to draw the image.<br>
 */

public abstract class Entity {

    // The class variables are protected because only a superclass can use them.
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean visible;
    protected Image image;

    /**
     * The Entity constructor.<br>
     * It sets the visible flag.<br>
     * @param x The x-position of the entity.
     * @param y The y-position of the entity.
     */
    public Entity(int x, int y) {

        this.x = x;
        this.y = y;
        visible = true;
    }

    /**
     * Used to load the image stored at the imageName path into the image variable.
     * @param imageName The path where the image is located.
     */
    protected void loadImage(String imageName) {
        image = new ImageIcon(imageName).getImage();
    }

    /**
     * Sets the width and height.
     */
    protected void getImageDimensions() {

        width = image.getWidth(null);
        height = image.getHeight(null);
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
}
