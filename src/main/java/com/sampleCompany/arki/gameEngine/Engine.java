package com.sampleCompany.arki.gameEngine;

import com.sampleCompany.arki.gameEngine.display.Display;
import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.init.Init;
import com.sampleCompany.arki.gameEngine.init.Initializer;
import com.sampleCompany.arki.gameEngine.init.Registry;
import com.sampleCompany.arki.gameEngine.input.keyboard.KeyManager;
import com.sampleCompany.arki.gameEngine.input.mouse.MouseManager;
import com.sampleCompany.arki.gameEngine.scenes.SceneManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;
import com.sampleCompany.game.sampleGame.Reference;
import org.reflections.Reflections;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;

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
        releaseDate = "11/20/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public final class Engine implements Runnable
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
    public static EntityManager entityManager;

    public static KeyManager keyManager;
    public static MouseManager mouseManager;

    // Class
    public Engine() {}

    /**
     * Registers initializers that are part of the game engine.
     */
    private void registerEngineInitializers() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException
    {
        // Get the package of the game (not game engine) by hierarchy instead of name.
        String[] splitPackages = this.getClass().getPackageName().split("\\.");
        StringBuilder rootPackage = new StringBuilder();
        for (int i = 0; i < 2; i++)
        {
            rootPackage.append(splitPackages[i]).append(".");
        }
        rootPackage.deleteCharAt(17);
        // Scans specified package (defined above) for Scenes.
        Reflections reflections = new Reflections(rootPackage.toString());
        Set<Class<?>> annotated = reflections.getTypesAnnotatedWith(Init.class);

        for (Class<?> c : annotated)
        {
            Registry.addInitializer((Initializer) c.getDeclaredConstructor().newInstance());
        }
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

        if (sceneManager != null)
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
        g.clearRect(0, 0, display.getFrame().getWidth(), display.getFrame().getHeight());
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
        try
        {
            registerEngineInitializers();
        }
        catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e)
        {
            e.printStackTrace();
        }

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
