package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.Engine;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Origin of the program. Creates new instance
 * of the game engine, which creates a new
 * instance of the game.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/8/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Launcher
{

    public static void main(String[] args)
    {
        Engine engine = new Engine();
        engine.start();
    }

}
