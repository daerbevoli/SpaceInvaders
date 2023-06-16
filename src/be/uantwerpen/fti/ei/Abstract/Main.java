package be.uantwerpen.fti.ei.Abstract;

import be.uantwerpen.fti.ei.J2D.GraphicsContext;
import be.uantwerpen.fti.ei.J2D.J2DFactory;

public class Main {

    public static void main(String[] args) {
	    AbstractFactory f = new J2DFactory(new GraphicsContext());
        Game game = new Game(f);
        game.run();
    }
}
