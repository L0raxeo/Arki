package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.display.Display;
import com.sampleCompany.arki.gameEngine.input.KeyManager;

public class Window implements Initializer
{

    private Display display;

    private KeyManager keyManager;

    @Override
    public void preInit()
    {
        keyManager = new KeyManager();
    }

    @Override
    public void init()
    {
        display = new Display("Test Window", 100, 100);
    }

    @Override
    public void postInit()
    {
        display.getFrame().addKeyListener(keyManager);
    }

}
