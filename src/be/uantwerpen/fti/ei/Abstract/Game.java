package be.uantwerpen.fti.ei.Abstract;

import be.uantwerpen.fti.ei.J2D.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * The Game class. This class contains the run and the update methods. It initializes the entities and updates it. Then runs the game.
 * <br>
 * It implements the Runnable interface and the run method. The method is then executed by a thread.
 * <br>
 * It also implements the Constants interface to use the constants.
 *
 * @author Sameer Baruwal
 */

public class Game implements Runnable, Constants {

    // Factory
    public AbstractFactory factory;

    // Entities
    private Spaceship spaceship;
    private List<Alien> aliens;
    private List<Barrier> barriers;
    private List<Life> lives;

    // Bonus
    private Bonus bonus;

    // Alien fires beams
    private Alien beamAlien;
    private int rand;

    // Images
    private Image backgroundImg;

    // Game states
    private boolean inGame;
    private boolean isPaused;
    private boolean startLevel;
    private boolean Won;

    // Level
    private Level lvl;

    // Score
    private int score;

    // Thread
    private Thread t;

    /**
     * Creates the game with the abstract factory pattern.
     * @param f, The Abstract Factory.
     */
    public Game(AbstractFactory f) {
        this.factory = f;
        initGame();
    }

    /**
     * Initialized the entities, the flags and starts the thread.
     */
    private void initGame(){
        backgroundImg = level1BGImg;

        spaceship = (Spaceship) factory.createSpaceship(screenWidth / 2, screenHeight - 50);
        initAliens(initAlienCol, initAlienRow, enemyLvl1Img, 2);
        barriers = new ArrayList<>();
        for (int i = 1; i < BarrierAmount+1; i++){
            barriers.add(new Barrier(i*(screenWidth / (BarrierAmount+1)), screenHeight - 100, BarrierLives));
        }
        lives = new ArrayList<>();
        lives.add(new Life(10, 10));
        lives.add(new Life(50, 10));
        lives.add(new Life(90, 10));

        rand = new Random().nextInt(aliens.size());
        beamAlien = aliens.get(rand);

        bonus = (Bonus) factory.createBonus(-50, 20);

        inGame = true;
        isPaused = false;
        startLevel = false;
        Won = false;

        lvl = new Level(1);

        t = new Thread(this);
        t.start();

    }

    /**
     * Initialized the aliens arraylist with Alien objects.
     * @param col, The columns of the list, used for the x-position.
     * @param row, The rows of the list, used for the y-position.
     * @param img, The image of the Alien object.
     * @param fireSpeed, The speed at which the beam is fired.
     */
    private void initAliens(int col, int row, Image img, int fireSpeed){
        aliens = new ArrayList<>();

        for (int i = 1; i < col+1; i++) {
            for (int j = 1; j < row+1; j++) {
                aliens.add((Alien) factory.createAlien(i*55, j*45)); // creates list of Alien objects with x = i & y = j
            }
        }
        for (Alien alien : aliens) {
            alien.setImage(img);
            alien.setFireSpeed(fireSpeed);
        }
    }

    /**
     * Renders the game while the inGame flag is set. Updates the game if the startLevel flag is also set and the isPaused flag is cleared.
     * @throws InterruptedException Thrown when the thread is interrupted.
     */
    @Override
    public void run() {
        while (inGame) {
            if (!isPaused && startLevel) {
                // if paused or level not yet started don't update
                update();
            }
            factory.render();
            try {
                Thread.sleep(8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Call to all the other individual update functions
     */
    private void update(){
        updateCollision();
        updateShip();
        updateMissiles();
        updateAliens();
        updateBeams();
        updateBarrier();
        updateBonus();
        updateLives();
        updateLevel();

    }

    /**
     * inGame flag is cleared and the game is finished when the lives list is empty.
     */
    private void updateLives(){
        if (lives.isEmpty()){
            inGame = false;
            lvl.setDead(true);
        }
    }

    /**
     * As long as the spaceship is visible (not dead), it can move.
     */
    private void updateShip() {
        if (spaceship.isVisible()) {
            spaceship.move();
        }
    }

    /**
     * Missiles is an object list of the Spaceship object. This function moves and removes the Missile object from the list.
     */
    private void updateMissiles() {
        List<Missile> ms = spaceship.getMissiles();

        Iterator<Missile> iter = ms.iterator();
        while (iter.hasNext()) {
            Missile missile = iter.next();
            if (missile.isVisible()) {
                missile.move();
            } else {
                iter.remove();
            }
        }
    }

    /**
     * Removes a Barrier object from the barriers list if barrier life is 0.
     */
    private void updateBarrier(){
        barriers.removeIf(barrier -> barrier.getLives() == 0);
    }

    /**
     * Used to change direction, move left, right and down.
     */
    private void updateAliens() {
        for (int i = 0; i < aliens.size(); i++) {

            // used to change direction after alien wall is bumped
            Alien left = aliens.get(0);
            if (left.x < 5) {
                aliens.get(i).setMovingLeft(false);
            }
            Alien right = aliens.get(aliens.size() - 1);
            if (right.x > screenWidth - 36) {
                aliens.get(i).setMovingLeft(true);
            }

            // used to move down
            if (left.hitWall() || right.hitWall()) {
                aliens.get(i).moveDown();
            }

            // used to move left and right
            Alien alien = aliens.get(i);
            if (alien.isVisible() && alien.isMovingLeft()) {
                alien.moveLeft();
            } else if (alien.isVisible() && !alien.isMovingLeft()) {
                alien.moveRight();
            } else {
                aliens.remove(alien);
            }
        }
    }

    /**
     * A beam is fired if the list is empty. The list thus only contains one item at a time. A random Alien object is selected to fire the beam.
     */
    private void updateBeams(){
        if (beamAlien.isVisible()){
            beamAlien.fireBeam();
        }
        List<Beam> beams = beamAlien.getBeams();
        for (int i = 0; i < beams.size(); i++){
            Beam beam = beams.get(i);
            if (beam.isVisible()){
                beam.move();
            } else {
                rand = new Random().nextInt(aliens.size());
                beamAlien = aliens.get(rand);
                beams.remove(beam);
            }
        }
    }

    /**
     * A bonus only appears when you have 1 life left. If the bonus is hit, a life is added.<br>
     * The bonus keeps moving if you have one life left until it is hit. <br>
     * There is no possibility to go back to three lives.
     */
    private void updateBonus(){
        if (lives.size() == 1) {
            if (bonus.isVisible()) {
                bonus.move();
                if (bonus.isHit()){
                    lives.add(new Life(50, 10));
                }
                bonus.setHit(false);
            } else {
                bonus.setX(-50);
                bonus.setY(10);
                bonus.setVisible(true);
            }
        }
        bonus.BonusHit();
    }

    /**
     * If all aliens are dead we move to the next level.
     * We stay using the same canvas.
     * We increase amount of aliens and their fireSpeed.
     * We remove a barrier. <br>
     * The difficulty increases with the level.
     */
    private void updateLevel() {
        lvl.updateWin();
        if (aliens.isEmpty() && lvl.getLevel() == 1) {
            levelUpdate(2, level2BGImg);
            if (!barriers.isEmpty()) {
                barriers.remove(0);
            }
            initAliens(initAlienCol+1, initAlienRow+1, enemyLvl2Img, 3);

        }
        if (aliens.isEmpty() && lvl.getLevel() == 2) {
            levelUpdate(3, level3BGImg);
            if (!barriers.isEmpty()) {
                barriers.remove(barriers.size() - 1);
            }
            initAliens(initAlienCol+2, initAlienRow+2, enemyLvl3Img, 5);

        }
        if (aliens.isEmpty() && lvl.getLevel() == 3) {
            Won = true;
            inGame = false;
            lvl.setGameWon(Won);
            lvl.updateWin();
        }
    }

    /**
     * Help function for the updateLevel function. It plays the sounds at the transitioning of levels.
     * @param level The level to set.
     * @param bgImage The image to set.
     */
    private void levelUpdate(int level, Image bgImage){
        startLevel = false;
        lvl.setStartLevel(startLevel);
        lvl.setLevel(level);
        lvl.updateLvl();
        spaceship.getMissiles().clear();
        spaceship.setX(screenWidth / 2);
        spaceship.setX(screenHeight - 50);
        setBackgroundImg(bgImage);
    }


    /**
     * Collision is possible between all moving entities. This causes for a lot of repetitive code.
     */
    private void updateCollision(){

        Rectangle shipRect = spaceship.getBounds();

        // collision of alien and spaceship
        for (Alien alien : aliens){
            Rectangle alienRect = alien.getBounds();
            if (shipRect.intersects(alienRect)){
                spaceship.setVisible(false);
                alien.setVisible(false);
                inGame = false;
                Won = false;
            }
            for (int i = 0; i < barriers.size(); i++){
                Rectangle barrierRect = barriers.get(i).getBounds();
                if (alienRect.intersects(barrierRect)){
                    barriers.remove(barriers.get(i));
                }
            }
        }

        List<Beam> beams = beamAlien.getBeams();

        // collision of beam and ship
        for (Beam beam : beams){
            Rectangle beamRect = beam.getBounds();
            if (shipRect.intersects(beamRect)){
                lives.remove(lives.size()-1);
                beam.setVisible(false);
                spaceship.hit();
                spaceship.setX(screenWidth / 2);
                spaceship.setY(screenHeight - 50);
            }

            // collision of beam and barrier
            for (Barrier barrier : barriers){
                Rectangle barrierRect = barrier.getBounds();
                if (barrierRect.intersects(beamRect)){
                    barrier.setLives(barrier.getLives()-1);
                    beam.setVisible(false);
                }
            }
        }

        List<Missile> missiles = spaceship.getMissiles();

        // collision of missile
        for (Missile missile : missiles){
            Rectangle missileRect = missile.getBounds();

            Rectangle bonusRect = bonus.getBounds();
            if (bonusRect.intersects(missileRect)) {
                bonus.setHit(true);
                missile.setVisible(false);
                bonus.setVisible(false);
            }

            // with barrier
            for (Barrier barrier : barriers){
                Rectangle barrierRect = barrier.getBounds();
                if (barrierRect.intersects(missileRect)){
                    barrier.setLives(barrier.getLives()-1);
                    missile.setVisible(false);
                }
            }

            // with alien
            for (Alien alien : aliens){
                Rectangle alienRect = alien.getBounds();
                if (alienRect.intersects(missileRect)){
                    missile.setVisible(false);
                    alien.setVisible(false);
                    score += 20;
                }
            }
        }
    }

    /**
     * Used to replace background image when we go up a level in the updateLevel method.
     * @param img, The new image.
     */
    private void setBackgroundImg(Image img){
            backgroundImg = img;
    }

    /**
     * Returns the spaceship.
     * @return The spaceship.
     */
    public Spaceship getSpaceship() {
        return spaceship;
    }

    /**
     * Sets the spaceship.
     * @param spaceship The spaceship.
     */
    public void setSpaceship(Spaceship spaceship) {
        this.spaceship = spaceship;
    }

    /**
     * Returns the aliens list.
     * @return The aliens list.
     */
    public List<Alien> getAliens() {
        return aliens;
    }

    /**
     * Sets the aliens list.
     * @param aliens The aliens list.
     */
    public void setAliens(List<Alien> aliens) {
        this.aliens = aliens;
    }

    /**
     * Returns the barriers list.
     * @return The barriers list.
     */
    public List<Barrier> getBarriers() {
        return barriers;
    }

    /**
     * Sets the barriers list.
     * @param barriers The barriers list.
     */
    public void setBarriers(List<Barrier> barriers) {
        this.barriers = barriers;
    }

    /**
     * Returns the lives list.
     * @return The lives list.
     */
    public List<Life> getLives() {
        return lives;
    }

    /**
     * Sets the lives list.
     * @param lives The lives list.
     */
    public void setLives(List<Life> lives) {
        this.lives = lives;
    }

    /**
     * Returns the bonus.
     * @return The bonus.
     */
    public Bonus getBonus() {
        return bonus;
    }

    /**
     * Sets the bonus.
     * @param bonus The bonus.
     */
    public void setBonus(Bonus bonus) {
        this.bonus = bonus;
    }

    /**
     * Returns the beamAlien.
     * @return The beamAlien.
     */
    public Alien getBeamAlien() {
        return beamAlien;
    }

    /**
     * Sets the beamAlien.
     * @param beamAlien The beamAlien.
     */
    public void setBeamAlien(Alien beamAlien) {
        this.beamAlien = beamAlien;
    }

    /**
     * Returns the background image.
     * @return The background image.
     */
    public Image getBackgroundImg() {
        return backgroundImg;
    }

    /**
     * Returns the inGame flag.
     * @return The inGame flag.
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * Sets the inGame flag.
     * @param inGame the inGame flag.
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }

    /**
     * Returns the isPaused flag.
     * @return The isPaused flag.
     */
    public boolean isPaused() {
        return isPaused;
    }

    /**
     * Sets the isPaused flag.
     * @param paused the isPaused flag.
     */
    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    /**
     * Returns the StartLevel flag.
     * @return the StartLevel flag.
     */
    public boolean isStartLevel() {
        return startLevel;
    }

    /**
     * Sets the StartLevel flag.
     * @param startLevel The StartLevel flag.
     */
    public void setStartLevel(boolean startLevel) {
        this.startLevel = startLevel;
    }

    /**
     * Returns the Won flag.
     * @return the Won flag.
     */
    public boolean isWon() {
        return Won;
    }

    /**
     * Sets the Won flag.
     * @param won The Won flag.
     */
    public void setWon(boolean won) {
        Won = won;
    }


    /**
     * Returns the level.
     * @return The level.
     */
    public Level getLvl() {
        return lvl;
    }

    /**
     * Sets the level.
     * @param lvl The level.
     */
    public void setLvl(Level lvl) {
        this.lvl = lvl;
    }

    /**
     * Returns the score.
     * @return The score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets the score.
     * @param score The score.
     */
    public void setScore(int score) {
        this.score = score;
    }
}
