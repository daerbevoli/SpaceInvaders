package be.uantwerpen.fti.ei.J2D;

import be.uantwerpen.fti.ei.Abstract.AbstractFactory;
import be.uantwerpen.fti.ei.Abstract.Game;

import javax.swing.*;

/**
 * This class sets the frame and repaints it.
 */
public class GraphicsContext implements Constants {
    private final JFrame frame;
    private final AbstractFactory fact;
    private final Game game;
    private final GamePanel gp;

    /**
     * Initialization of the frame and its properties.
     */
    public GraphicsContext() {
        frame = new JFrame();
        fact = new J2DFactory(this);
        game = new Game(fact);
        gp = new GamePanel(game);
        frame.add(gp);
        frame.pack();
        frame.setTitle("Space Invaders");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * Repaints the frame.
     */
    public void render(){
        frame.repaint();
    }

}
