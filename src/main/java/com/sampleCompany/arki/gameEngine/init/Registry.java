package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.util.ArrayList;

/**
 * Handles all initializers and its processes.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/9/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Registry
{

    /**
     * Stores all initializers that need to be executed during
     * initialization sequence.
     */
    private static final ArrayList<Initializer> allInitializers = new ArrayList<>();

    /**
     * Adds the specified initializer to the list of
     * initialization processes that need to be executed.
     *
     * Must be used for all initialization sequences in
     * both the game engine, and the game being created.
     */
    public static void addInitializer(Initializer obj)
    {
        allInitializers.add(obj);
    }

    // Loops through all initializers and invokes each appropriate process.

    public static void preInit() throws Exception
    {
        for (Initializer i : allInitializers)
        {
            i.preInit();
        }
    }

    public static void init() throws Exception
    {
        for (Initializer i : allInitializers)
        {
            i.init();
        }
    }

    public static void postInit() throws Exception
    {
        for (Initializer i : allInitializers)
        {
            i.postInit();
        }
    }

}
