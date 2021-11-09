package com.l0raxeo.arki.gameEngine;

import com.l0raxeo.arki.gameEngine.init.Initializer;

import java.util.ArrayList;

public class Application
{

    public static int curFPS;

    private static final ArrayList<Initializer> allInitializers = new ArrayList<>();

    public void registerInitializer(Initializer initializer)
    {
        allInitializers.add(initializer);
    }

    public static ArrayList<Initializer> getAllInitializers()
    {
        return allInitializers;
    }

}
