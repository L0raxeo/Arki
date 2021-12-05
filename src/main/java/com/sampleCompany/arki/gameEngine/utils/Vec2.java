package com.sampleCompany.arki.gameEngine.utils;

/**
 * Stores two numbers (as floats).
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
public record Vec2(float a, float b)
{

    // Getters

    /**
     * @return first value (value a).
     */
    public float getA()
    {
        return a;
    }

    /**
     * @return second value (value b).
     */
    public float getB()
    {
        return b;
    }

}
