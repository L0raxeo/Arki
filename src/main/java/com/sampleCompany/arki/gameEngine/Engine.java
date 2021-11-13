package com.sampleCompany.arki.gameEngine;

import com.sampleCompany.arki.gameEngine.display.Display;
import com.sampleCompany.arki.gameEngine.init.Game;
import com.sampleCompany.arki.gameEngine.init.ProjectStructure;
import com.sampleCompany.arki.gameEngine.init.Registry;
import com.sampleCompany.arki.gameEngine.init.Window;
import com.sampleCompany.arki.gameEngine.input.keyboard.KeyManager;
import com.sampleCompany.arki.gameEngine.input.mouse.MouseManager;
import com.sampleCompany.arki.gameEngine.scenes.SceneManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;
import com.sampleCompany.sampleGame.Reference;

import java.awt.*;
import java.awt.image.BufferStrategy;

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
        releaseDate = "11/11/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Engine implements Runnable
{

    /**
     * Main/default application window
     */
    public static Display display;

    // Thread
    private Thread thread;
    private boolean running = false;

    // Game

    public static SceneManager sceneManager;

    public static KeyManager keyManager;
    public static MouseManager mouseManager;

    // Class
    public Engine() {}

    /**
     * Registers initializers that are part of the game engine.
     */
    private void registerEngineInitializers()
    {
        Registry.addInitializer(new ProjectStructure());
        Registry.addInitializer(new Window());
        Registry.addInitializer(new Game());
    }

    /**
     * Updates all associated classes/objects with
     * a tick method.
     *
     * When moving an object across the screen, two
     * processes take place: position update, change
     * rendering. First, the object's position is
     * updated, in the form of a variable. This position
     * is then rendered to the screen in real time.
     * The order is as such: 1) tick 2) render.
     */
    private void tick()
    {
        keyManager.tick();
        mouseManager.tick();

        sceneManager.tick();
    }

    /**
     * Draws all specified components to the screen,
     * who have the required information to do so.
     *
     * When moving an object across the screen, two
     * processes take place: position update, change
     * rendering. First, the object's position is
     * updated, in the form of a variable. This position
     * is then rendered to the screen in real time.
     * The order is as such: 1) tick 2) render.
     */
    private void render()
    {
        BufferStrategy bs = display.getCanvas().getBufferStrategy();

        if (bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        Graphics g = bs.getDrawGraphics();
        // clear screen
        g.clearRect(0, 0, Reference.displayWidth, Reference.displayHeight);
        // draw here

        sceneManager.render(g);

        // end drawing
        bs.show();
        g.dispose();
    }

    /**
     * Loop method for thread.
     *
     * Invokes init sequences.
     */
    @Override
    public void run()
    {
        registerEngineInitializers();

        try
        {
            Registry.preInit();
            Registry.init();
            Registry.postInit();
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
