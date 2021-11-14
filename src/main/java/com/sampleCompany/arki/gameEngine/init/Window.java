package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.Engine;
import com.sampleCompany.arki.gameEngine.display.Display;
import com.sampleCompany.arki.gameEngine.input.keyboard.KeyManager;
import com.sampleCompany.arki.gameEngine.input.mouse.MouseManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;
import com.sampleCompany.game.sampleGame.Reference;

/**
 * Manages all keys, and can be used to
 * retrieve information about the current
 * state of a specific key.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/14/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
@Init
public class Window implements Initializer
{
    @Override
    public void preInit()
    {
        Engine.keyManager = new KeyManager();
        Engine.mouseManager = new MouseManager();
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
        Engine.display.getCanvas().addMouseListener(Engine.mouseManager);
        Engine.display.getCanvas().addMouseMotionListener(Engine.mouseManager);
    }

}
