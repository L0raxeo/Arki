package com.sampleCompany.arki.gameEngine.sfx;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.util.ArrayList;

/**
 * Manages the playing of all audio clips,
 * and all audio clips being played.
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
public class AudioManager
{

    /**
     * Contains all the audio clips
     * whose clips are currently playing.
     */
    private static final ArrayList<AudioClip> curPlayingSounds = new ArrayList<>();

    /**
     * @param audioClip being played.
     */
    public static void play(AudioClip audioClip)
    {
        audioClip.getClip().start();
        indexAudioClip(audioClip);
    }

    /**
     * @param audioClip being played.
     * @param amt (amount) of times the clip is looped.
     */
    public static void loop(AudioClip audioClip, int amt)
    {
        audioClip.getClip().loop(amt);
        indexAudioClip(audioClip);
    }

    /**
     * Stops the audio clip specified. This does
     * not mean it is paused, simply that the
     * entire action of playing the audio clip
     * is terminated.
     *
     * @param audioClip being stopped.
     */
    public static void stop(AudioClip audioClip)
    {
        audioClip.getClip().stop();
        audioClip.getClip().close();
        removeAudioClip(audioClip);
    }

    /**
     * Stops the audio clip specified. This does
     * not mean it is paused, simply that the
     * entire action of playing the audio clip
     * is terminated.
     *
     * @param name of audio clilp being stopped.
     */
    public static void stop(String name)
    {
        for (AudioClip audioClip : curPlayingSounds)
        {
            if (audioClip.name.equals(name))
            {
                audioClip.getClip().stop();
                audioClip.getClip().close();
                removeAudioClip(audioClip);
                break;
            }
        }
    }

    /**
     * Checks if the audio clip specified in the
     * parameters is being played.
     */
    public static boolean isAudioClipPlaying(AudioClip audioClip)
    {
        return curPlayingSounds.contains(audioClip);
    }

    /**
     * Checks if the audio clip specified by its
     * name attribute is being played.
     */
    public static boolean isAudioClipPlaying(String name)
    {
        for (AudioClip audioClip : curPlayingSounds)
        {
            if (audioClip.name.equals(name))
                return true;
        }

        return false;
    }

    /**
     * Formally declares the specified audio
     * clip is being played.
     *
     * @param audioClip being indexed.
     */
    private static void indexAudioClip(AudioClip audioClip)
    {
        curPlayingSounds.add(audioClip);
    }

    /**
     * Formally declares the specified audio
     * clip is no longer playing.
     *
     * @param audioClip being removed.
     */
    private static void removeAudioClip(AudioClip audioClip)
    {
        curPlayingSounds.remove(audioClip);
    }

}
