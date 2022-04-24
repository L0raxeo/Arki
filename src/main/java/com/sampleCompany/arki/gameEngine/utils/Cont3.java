package com.sampleCompany.arki.gameEngine.utils;

/**
 * Stores 3 values of any type.
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
public record Cont3<A, B, C>(A a, B b, C c)
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

    /**
     * @return second value (value c).
     */
    public C getC()
    {
        return c;
    }

}
