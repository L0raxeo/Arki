package com.sampleCompany.arki.gameEngine.gfx;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.image.BufferedImage;

/**
 * Animation object stores individual frames to be accessed
 * and rendered to the screen in an animation sequence.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/20/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Animation
{

    /**
     * Speed of animation.
     * Speed = buffer between frames:
     * - higher speed decreases animation speed
     * - lower speed increases animation speed
     *
     * CHANGE SO THAT HIGHER SPEED = HIGHER SPEED
     */
    private final int speed;

    /**
     * Current frame as index in frames array.
     */
    private int index;

    /**
     * Iterating frame timer
     */
    private long lastTime, timer;

    /**
     * All iterable animation frames.
     */
    private final BufferedImage[] frames;

    // Class
    public Animation(int speed, BufferedImage[] frames)
    {
        this.speed = speed;
        this.frames = frames;
        index = 0;
        timer = 0;
        lastTime = System.currentTimeMillis();
    }

    /**
     * Iterates through animation frame appropriate to speed.
     */
    public void tick()
    {
        timer += System.currentTimeMillis() - lastTime;
        lastTime = System.currentTimeMillis();

        if (timer > speed)
        {
            index++;
            timer = 0;

            if (index >= frames.length)
                index = 0;
        }
    }

    /**
     * Render this buffered image.
     *
     * @return current frame animation is displaying
     */
    public BufferedImage getCurrentFrame()
    {
        return frames[index];
    }

}
