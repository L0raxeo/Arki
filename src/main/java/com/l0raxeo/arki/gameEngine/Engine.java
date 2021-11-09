package com.l0raxeo.arki.gameEngine;

import com.l0raxeo.arki.gameEngine.init.Initializer;
import com.l0raxeo.arki.gameEngine.init.ProjectStructure;
import com.l0raxeo.arki.gameEngine.utils.VersionInfo;
import com.l0raxeo.sampleGame.Reference;

import java.io.IOException;
import java.util.ServiceLoader;

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
        releaseDate = "11/9/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Engine implements Runnable
{

    private Thread thread;
    private boolean running = false;

    // Class
    public Engine() {}

    // All init sequences of this class invoke the initializers.

    private void preInit() throws Exception
    {
        for (Initializer i : Application.getAllInitializers())
        {
            i.preInit();
        }
    }

    private void init() throws Exception
    {
        for (Initializer i : Application.getAllInitializers())
        {
            i.init();
        }
    }

    private void postInit() throws Exception
    {
        for (Initializer i : Application.getAllInitializers())
        {
            i.postInit();
        }
    }

    private void tick()
    {

    }

    private void render()
    {

    }

    /**
     * Loop method for thread.
     */
    @Override
    public void run()
    {
        try
        {
            preInit();
            init();
            postInit();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        int fps = Reference.targetFPS;
        double timePerTick = 1000000000f / fps;
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;

        while (running)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / timePerTick;
            timer += now - lastTime;
            lastTime = now;

            if (delta >= 1)
            {
                tick();
                render();
                delta--;
            }

            if (timer >= 1000000000)
            {
                timer = 0;
            }
        }

        stop();
    }

    /**
     * Starts application.
     */
    public synchronized void start()
    {
        if (running) return;
        running = true;
        thread = new Thread(this);
        thread.start();
    }

    /**
     * Stops application.
     */
    public synchronized void stop()
    {
        if (!running) return;
        running = false;

        try
        {
            thread.join();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }

}
