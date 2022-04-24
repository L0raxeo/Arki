package com.sampleCompany.arki.gameEngine.utils;

/**
 * Stores 2 values of any type.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "2.1",
        releaseDate = "1/5/2021",
        since = "2.1",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public record Cont2<A, B>(A a, B b)
{

    /**
     * @return first value (value a).
     */
    public A getA()
    {
        return a;
    }

    /**
     * @return second value (value b).
     */
    public B getB()
    {
        return b;
    }

}
