package be.uantwerpen.fti.ei.J2D;

import javax.swing.*;
import java.awt.*;
import java.io.File;

/**
 * Constants interface where all the constants are stored.
 */
public interface Constants {

    int screenWidth = 900;
    int screenHeight = 506;

    int BarrierLives = 12;
    int BarrierAmount = 5;

    int initAlienCol = 5;
    int initAlienRow = 3;

    Image level1BGImg = new ImageIcon("src/images/spaceLvl1.jpg").getImage();
    Image level2BGImg = new ImageIcon("src/images/spaceLvl2.png").getImage();
    Image level3BGImg = new ImageIcon("src/images/spaceLvl3.png").getImage();

    Image enemyLvl1Img = new ImageIcon("src/images/enemyLvl1.png").getImage();
    Image enemyLvl2Img = new ImageIcon("src/images/enemyLvl2.png").getImage();
    Image enemyLvl3Img = new ImageIcon("src/images/enemyLvl3.png").getImage();

    Image titleImg = new ImageIcon("src/images/TitleScreen.png").getImage();
    Image level2Img = new ImageIcon("src/images/level2Img.png").getImage();
    Image level3Img = new ImageIcon("src/images/level3Img.png").getImage();

    Image WinImg = new ImageIcon("src/images/WinScreen.png").getImage();
    Image LoseImg = new ImageIcon("src/images/LoseScreen.png").getImage();
    Image pauseImg = new ImageIcon("src/images/PauseScreen.png").getImage();

    File missileSoundFile = new File("src/sounds/bulletSound.wav");
    File beamSoundFile = new File("src/sounds/alienBeam.wav");
    File damageSoundFile = new File("src/sounds/damageSound.wav");
    File bonusSoundFile = new File("src/sounds/bonusSound.wav");
    File lvlUpSoundFile = new File("src/sounds/levelUpSound.wav");
    File winSoundFile = new File("src/sounds/winSound.wav");
    File loseSoundFile = new File("src/sounds/loseSound.wav");




}
