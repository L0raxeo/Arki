package com.sampleCompany.arki.gameEngine.scenes;

import com.sampleCompany.arki.gameEngine.Engine;
import com.sampleCompany.arki.gameEngine.init.Init;
import com.sampleCompany.arki.gameEngine.init.Initializer;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;
import org.reflections.Reflections;

import java.awt.*;
import java.util.ArrayList;
import java.util.Set;

/**
 * Manages all registered scenes, and
 * handles their use.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/20/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
@Init
public final class SceneManager implements Initializer
{

    /**
     * All known registered defined scenes
     */
    private static final ArrayList<Scene> allScenes = new ArrayList<>();

    /**
     * Current scene active
     */
    private static Scene currentScene = null;

    private static Scene defaultScene = null;

    /**
     * Queued scene to be set to active
     * Overwrites queued scene (cannot queue multiple scenes)
     */
    private static Scene queuedScene = null;

    /**
     * Scans entire project for scenes and
     * automatically registers them.
     */
    @Override
    public void init() throws Exception
    {
        // Get the package of the game (not game engine) by hierarchy instead of name.
        String[] splitPackages = this.getClass().getPackageName().split("\\.");
        StringBuilder rootPackage = new StringBuilder();
        for (int i = 0; i < 2; i++)
        {
            rootPackage.append(splitPackages[i]).append(".");
        }

        // Scans specified package (defined above) for Scenes.
        Reflections ref = new Reflections(rootPackage + "game");
        Set<Class<? extends Scene>> classes = ref.getSubTypesOf(Scene.class);

        for (Class<? extends Scene> scene : classes)
        {
            Scene s = scene.getDeclaredConstructor().newInstance();

            addScene(s);

            if (scene.isAnnotationPresent(DefaultScene.class))
            {
                defaultScene = s;
                forceScene(s);
            }
        }
    }

    /**
     * Updates current scene
     */
    public void tick()
    {
        if (queuedScene != null)
        {
            currentScene.sleep();
            forceScene(queuedScene);
            queuedScene = null;
        }

        if (currentScene != null)
            currentScene.tick();

        Engine.entityManager.tick();
    }

    /**
     * Renders current scene
     */
    public void render(Graphics g)
    {
        if (currentScene != null)
            currentScene.render(g);

        Engine.entityManager.render(g);
    }

    // Array list scene getters and setters

    /**
     * Adds scene to all scenes array list.
     * Invokes awake() method, since the
     * scene is being created.
     */
    public static void addScene(Scene scene)
    {
        allScenes.add(scene);
        scene.awake();
    }

    /**
     * Forces specified scene to be set to active
     */
    public static void forceScene(Scene scene)
    {
        currentScene = scene;
        currentScene.start();
    }

    /**
     * @return scene associated with specified ID
     */
    public static Scene getScene(String id)
    {
        for (Scene s : allScenes)
        {
            if (s.getClass().getAnnotation(SceneInfo.class).sceneID().equals(id))
                return s;
        }

        return null;
    }

    // All methods of switching scenes

    /**
     * Sets scene object specified
     */
    public static void setScene(Scene scene)
    {
        if (queuedScene != scene)
            queuedScene = scene;
    }

    /**
     * Sets scene of specified ID
     */
    public static void setScene(String id)
    {
        if (queuedScene.equals(getScene(id)))
            return;

        for (Scene s : allScenes)
        {
            if (s.getClass().getAnnotation(SceneInfo.class).sceneID().equals(id))
                queuedScene = getScene(id);
        }
    }

    // Getters

    /**
     * @return active scene
     */
    public static Scene getCurrentScene()
    {
        return currentScene;
    }

    public static Scene getDefaultScene()
    {
        return defaultScene;
    }

    // Force changes

    /**
     * Deletes scene from {@link SceneManager}
     */
    public static void deleteScene(Scene scene)
    {
        allScenes.remove(scene);
    }

}
