package com.sampleCompany.arki.gameEngine.sfx;

import java.util.ArrayList;

public class SoundManager
{

    public static final ArrayList<AudioClip> playingAudioClips = new ArrayList<>();

    public static void play(AudioClip audioClip, float volume)
    {
        float gain = (audioClip.getRange() * volume) + audioClip.getGainControl().getMinimum();
        audioClip.getGainControl().setValue(gain);
        audioClip.getClip().start();
        playingAudioClips.add(audioClip);
    }

    /**
     * @param audioClip being played.
     * @param volume of clip.
     * @param iterationsAmt of times clip is looped.
     */
    public static void play(AudioClip audioClip, float volume, int iterationsAmt)
    {
        float gain = (audioClip.getRange() * volume) + audioClip.getGainControl().getMinimum();
        audioClip.getGainControl().setValue(gain);
        audioClip.getClip().loop(iterationsAmt);
        audioClip.getClip().start();
        playingAudioClips.add(audioClip);
    }

    public static void stop(AudioClip audioClip)
    {
        audioClip.getClip().stop();
        playingAudioClips.remove(audioClip);
    }

    public static void stop(String name)
    {
        for (AudioClip audioClip : playingAudioClips)
        {
            if (audioClip.name.equals(name))
            {
                audioClip.getClip().stop();
                playingAudioClips.remove(audioClip);
                break;
            }
        }
    }

}