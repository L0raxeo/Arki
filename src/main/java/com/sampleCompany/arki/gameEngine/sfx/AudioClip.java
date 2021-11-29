package com.sampleCompany.arki.gameEngine.sfx;

import com.sampleCompany.arki.gameEngine.utils.FileLoader;

import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class AudioClip
{

    public String name;

    public File rawFile;
    public Clip clip;

    // Volume
    public FloatControl gainControl;
    public float range;

    public AudioFormat format;
    public byte[] samples;

    /**
     * Constructs a new audio clip, using the given resource name to load audio
     * data from a classpath resource.
     */
    public AudioClip(String name, String path)
    {
        this.name = name;
        FileLoader.loadFile(path);

        try
        {
            AudioInputStream audioInputStream =
                    AudioSystem.getAudioInputStream(new BufferedInputStream(Objects.requireNonNull(rawFile.toURI().toURL().openStream())));

            this.format = audioInputStream.getFormat();
            this.samples = audioInputStream.readAllBytes();

            // Clip
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);

            this.clip = clip;
            this.gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            this.range = gainControl.getMaximum() - gainControl.getMinimum();
        }
        catch (LineUnavailableException | IOException | UnsupportedAudioFileException e)
        {
            e.printStackTrace();
        }
    }

    // Getters

    /**
     * @return raw file.
     */
    public File getRawFile()
    {
        return rawFile;
    }

    /**
     * @return playable clip.
     */
    public Clip getClip()
    {
        return clip;
    }

    /**
     * @return audio clip float control.
     */
    public FloatControl getGainControl()
    {
        return gainControl;
    }

    /**
     * @return range of volume as a float.
     */
    public final float getRange()
    {
        return range;
    }

    /**
     * @return audio format of file.
     */
    public AudioFormat getFormat()
    {
        return format;
    }

    /**
     * @return samples.
     */
    public byte[] getSamples()
    {
        return samples;
    }

}