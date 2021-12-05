package com.sampleCompany.arki.gameEngine.sfx;

import com.sampleCompany.arki.gameEngine.utils.FileLoader;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * An audio clip object contains all the
 * attributes of an audio file that are
 * used to define how it's played. The
 * object itself also automatically handles
 * and creates the clip and audio input
 * stream.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "2.1",
        releaseDate = "12/5/2021",
        since = "2.1",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class AudioClip
{

    public AudioInputStream audioInputStream;
    public Clip clip;

    // Attributes

    public final String name;
    public final String path;
    public final float volume;

    // class

    /**
     * @param name the reference name of the audio clip
     * @param path the path of the raw wave file (not a resource)
     * @param volume of the wave file at which it will be played at
     */
    public AudioClip(String name, String path, float volume)
    {
        this.name = name;
        this.path = path;
        this.volume = volume;

        createClip();
    }

    /**
     * Create the clip by initializing the audio input stream,
     * and then using it to initialize and open the clip;
     */
    private void createClip()
    {
        try
        {
            audioInputStream = AudioSystem.getAudioInputStream(FileLoader.loadFile(path));

            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
        {
            e.printStackTrace();
        }
    }

    // Getters

    public Clip getClip()
    {
        return clip;
    }

    public AudioInputStream getAudioInputStream()
    {
        return audioInputStream;
    }

}
