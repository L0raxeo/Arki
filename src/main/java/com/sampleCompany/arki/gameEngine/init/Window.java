package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.Engine;
import com.sampleCompany.arki.gameEngine.display.Display;
import com.sampleCompany.arki.gameEngine.input.KeyManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;
import com.sampleCompany.sampleGame.Reference;

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
    @Override
    public void preInit()
    {
        Engine.keyManager = new KeyManager();
    }

    @Override
    public void init()
    {
        Engine.display = new Display(Reference.NAME, Reference.displayWidth, Reference.displayHeight);
    }

    @Override
    public void postInit()
    {
        Engine.display.getFrame().addKeyListener(Engine.keyManager);
    }

}
