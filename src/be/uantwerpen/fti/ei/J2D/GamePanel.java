package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * This class paints the images and listens for key inputs.<br>
 * This class is a superclass of the JPanel class. It implements the KeyListener interface to use the key functions.
 */

public class GamePanel extends JPanel implements Constants, KeyListener {
    private final Game game;

    /**
     * Creates the game panel.
     * @param game The game.
     */
    public GamePanel(Game game){
        this.game = game;
        initPanel();
    }

    /**
     * the setFocusable method is set to true to listen for key inputs. <br>
     * The preferred size set. <br>
     * A key listener added to the class.
     */
    private void initPanel(){
        setFocusable(true); //panel is focused for keyboard presses
        setPreferredSize(new Dimension(screenWidth, screenHeight));
        addKeyListener(this);
    }

    /**
     * This method is called everytime the game renders. It calls the doDrawing method that does the drawing of the game.<br>
     * Toolkit.getDefaultToolkit().sync() is used to sync the drawing and avoid delays.
     * @param g, Graphics object that allows the drawing to be possible.
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
        Toolkit.getDefaultToolkit().sync();
    }

    /**
     * Selects what state of the game to draw with the use of flags.
     * @param g, Allows drawing.
     */
    private void doDrawing(Graphics g){
        if (game.isInGame()){
            if (game.isStartLevel()){
                if (game.isPaused()){
                    drawPaused(g);
                } else {
                    drawObjects(g);
                }
            } else {
                drawLevel(g);
            }
        } else {
            if (game.isWon()){
                drawGameWon(g);
            } else {
                drawGameLost(g);
            }
        }
    }

    /**
     * Draws the Pause screen.
     * @param g, Allows drawing.
     */
    public void drawPaused(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(pauseImg, 0, 0, this);
    }

    /**
     * Draws the game won screen.
     * @param g, Allows drawing.
     */
    public void drawGameWon(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(WinImg, 0, 0, this);
        Font font = new Font("Helvetica", Font.BOLD, 20);
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString("Score: " + game.getScore(), screenWidth / 2 - 50, screenHeight / 2 + 50);

    }

    /**
     * Draws the game lost screen.
     * @param g, Allows drawing.
     */
    public void drawGameLost(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(LoseImg, 0, 0, this);

        Font font = new Font("Helvetica", Font.BOLD, 20);
        g2d.setColor(Color.WHITE);
        g2d.setFont(font);
        g2d.drawString("Score: " + game.getScore(), screenWidth / 2 - 50, screenHeight / 2 + 50);
    }

    /**
     * Draws the respective level screen.
     * @param g, Allows drawing.
     */
    public void drawLevel(Graphics g){ // draws over the background
        Graphics2D g2d = (Graphics2D) g;

        if (game.getLvl().getLevel() == 1){
            g2d.drawImage(titleImg, 0, 0, this);
        }
        if (game.getLvl().getLevel() == 2){
            g2d.drawImage(level2Img, 0, 0, this);
        }
        if (game.getLvl().getLevel() == 3){
            g2d.drawImage(level3Img, 0, 0, this);
        }
    }

    /**
     * Draws the entities, background, the score and the level.
     * @param g, Allows drawing.
     */
    public void drawObjects(Graphics g){
        Graphics2D g2d = (Graphics2D) g;

        g2d.drawImage(game.getBackgroundImg(), 0, 0, null);
        game.getSpaceship().draw(g);
        for (int i = 0; i < game.getAliens().size(); i++){
            game.getAliens().get(i).draw(g); // to avoid concurrentModificationException
        }
        for (Life life : game.getLives()){
            life.draw(g);
        }
        for (Barrier barrier : game.getBarriers()){
            barrier.draw(g);
        }
        for (Missile missile : game.getSpaceship().getMissiles()){
            missile.draw(g);
        }
        for (Beam beam : game.getBeamAlien().getBeams()){
            beam.draw(g);
        }
        game.getBonus().draw(g);

        g2d.setColor(Color.WHITE);
        g2d.drawString("score: " + game.getScore(), screenWidth - 60, 15);
        g2d.drawString("Level: " + game.getLvl().getLevel(), screenWidth / 2, 15);
    }

    /**
     * Calls the spaceship keyPressed method. <br>
     * If P key is pressed, the isPaused flag is toggled. <br>
     * If S key is pressed, the StartLevel flag is set. <br>
     * If Q key is pressed, the game is exited. <br>
     * @param e The event of the pressed key, used to get the keycode.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        game.getSpaceship().keyPressed(e);
        int key = e.getKeyCode();
        if (key== KeyEvent.VK_P) {
            game.setPaused(!game.isPaused());
        }
        if (key == KeyEvent.VK_S){
            game.setStartLevel(true);
        }
        if (key == KeyEvent.VK_Q){
            if (!game.isStartLevel() || game.isPaused() || !game.isInGame()) {
                System.exit(0);
            }
        }
    }

    /**
     * Calls the spaceship keyReleased method
     * @param e, The event of the pressed key, used to get the keycode.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        game.getSpaceship().keyReleased(e);
    }

    /**
     * N/A
     * @param e, The event of the pressed key, used to get the keycode.
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }
}
