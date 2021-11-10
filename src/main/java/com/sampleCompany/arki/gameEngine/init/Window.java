package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.Engine;
import com.sampleCompany.arki.gameEngine.display.Display;
import com.sampleCompany.arki.gameEngine.input.KeyManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Manages all keys, and can be used to
 * retrieve information about the current
 * state of a specific key.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/10/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Window implements Initializer
{

    private Display display;

    @Override
    public void preInit()
    {
        Engine.keyManager = new KeyManager();
    }

    @Override
    public void init()
    {
        display = new Display("Test Window", 1080, 720);
    }

    @Override
    public void postInit()
    {
        display.getFrame().addKeyListener(Engine.keyManager);
    }

}
