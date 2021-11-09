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

    private static final ArrayList<Initializer> allInitializers = new ArrayList<>();

    public static void addInitializer(Initializer obj)
    {
        allInitializers.add(obj);
    }

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
