package com.l0raxeo.arki.gameEngine;

import com.l0raxeo.arki.gameEngine.utils.VersionInfo;

/**
 * The main engine of Arki, where
 * the thread resides. Handles all
 * core operations within the game,
 * including ticking, rendering, etc...
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo (
        version = "1.0",
        releaseDate = "11/7/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Engine implements Runnable
{

    @Override
    public void run()
    {

    }

    public synchronized void start()
    {

    }

    public synchronized void stop()
    {

    }

}
