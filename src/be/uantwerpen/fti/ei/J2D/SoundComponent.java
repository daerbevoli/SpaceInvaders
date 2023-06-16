package be.uantwerpen.fti.ei.J2D;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * This class is a class of its own. It creates the Sound Component object. <br>
 * It implements the Constants interface to use the constants.
 */

public class SoundComponent implements Constants{

    private Clip missileSound;
    private Clip beamSound;
    private Clip damageSound;
    private Clip bonusSound;
    private Clip lvlUpSound;
    private Clip winSound;
    private Clip loseSound;

    /**
     * Creates the Sound Component object.
     * @throws UnsupportedAudioFileException This exception is thrown to indicate an operation has failed because a file did not contain valid data.
     * @throws LineUnavailableException This exception is thrown to indicate that a line cannot be opened because it is unavailable.
     * @throws IOException This exception is thrown to indicate some sort of error had occurred using InputStreams. (Very common).
     */
    public SoundComponent() throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        initSounds();
    }

    /**
     * This function gets the stream from the files in the Constants interface and makes it into a clip.
     * @throws UnsupportedAudioFileException This exception is thrown to indicate an operation has failed because a file did not contain valid data.
     * @throws LineUnavailableException This exception is thrown to indicate that a line cannot be opened because it is unavailable.
     * @throws IOException This exception is thrown to indicate some sort of error had occurred using InputStreams. (Very common).
     */
    private void initSounds() throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        AudioInputStream missileAudiostream = AudioSystem.getAudioInputStream(Constants.missileSoundFile);
        missileSound = AudioSystem.getClip();
        missileSound.open(missileAudiostream);

        AudioInputStream beamAudiostream = AudioSystem.getAudioInputStream(Constants.beamSoundFile);
        beamSound = AudioSystem.getClip();
        beamSound.open(beamAudiostream);

        AudioInputStream damageAudiostream = AudioSystem.getAudioInputStream(Constants.damageSoundFile);
        damageSound = AudioSystem.getClip();
        damageSound.open(damageAudiostream);

        AudioInputStream bonusAudiostream = AudioSystem.getAudioInputStream(Constants.bonusSoundFile);
        bonusSound = AudioSystem.getClip();
        bonusSound.open(bonusAudiostream);

        AudioInputStream lvlUpAudiostream = AudioSystem.getAudioInputStream(Constants.lvlUpSoundFile);
        lvlUpSound = AudioSystem.getClip();
        lvlUpSound.open(lvlUpAudiostream);

        AudioInputStream winAudiostream = AudioSystem.getAudioInputStream(Constants.winSoundFile);
        winSound = AudioSystem.getClip();
        winSound.open(winAudiostream);

        AudioInputStream loseAudiostream = AudioSystem.getAudioInputStream(Constants.loseSoundFile);
        loseSound = AudioSystem.getClip();
        loseSound.open(loseAudiostream);

    }

    /**
     * Returns the missile sound.
     * @return The missile sound.
     */
    public Clip getMissileSound(){
        return missileSound;
    }

    /**
     * Returns the beam sound.
     * @return The beam sound.
     */
    public Clip getBeamSound(){
        return beamSound;
    }

    /**
     * Returns the damage sound.
     * @return The damage sound.
     */
    public Clip getDamageSound(){
        return damageSound;
    }

    /**
     * Returns the bonus sound.
     * @return The bonus sound.
     */
    public Clip getBonusSound(){
        return bonusSound;
    }

    /**
     * Returns the level up sound.
     * @return The level up sound.
     */
    public Clip getLvlUpSound(){
        return lvlUpSound;
    }

    /**
     * Returns the win sound.
     * @return The win sound.
     */
    public Clip getWinSound(){
        return winSound;
    }

    /**
     * Returns the loss sound.
     * @return The loss sound.
     */
    public Clip getLoseSound(){
        return loseSound;
    }


}
