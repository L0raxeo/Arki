package com.sampleCompany.arki.gameEngine.entities;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

/**
 * Manages all entities and all subtypes by
 * registering, communicating, with each entity,
 * and relaying information from the engine to
 * each entity.
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
public final class EntityManager
{

    /**
     * All entities in scene
     */
    private static final ArrayList<Entity> allEntities = new ArrayList<>();

    /**
     * Used to sort render order of 2D objects that overlay one and other.
     */
    private final Comparator<Entity> renderSorter = Comparator.comparingInt(a -> (int) a.getY() + a.getHeight());

    /**
     * Updates all registered entities by invoking tick() method.
     */
    public void tick()
    {
        ListIterator<Entity> it = allEntities.listIterator();

        while (it.hasNext())
        {
            try
            {
                Entity e = it.next();
                e.tick();

                if (!e.isActive())
                    it.remove();
            }
            catch (Exception e)
            {
                return;
            }
        }

        allEntities.sort(renderSorter);
    }

    /**
     * Renders all registered entities.
     */
    public void render(Graphics g)
    {
        for (Entity e : allEntities)
            e.render(g);
    }

    /**
     * Maps all entities defined in specified entity map.
     */
    public static void mapEntities(EntityMap entityMap)
    {
        for (Entity e : entityMap.getEntities())
        {
            addEntity(e);
        }
    }

    /**
     * Removed all entities mapped by specified entity map.
     */
    public static void removeEntitiesOfMap(EntityMap entityMap)
    {
        for (Entity e : entityMap.getEntities())
        {
            allEntities.remove(e);
        }
    }

    // Modify individual entity

    /**
     * Add individual entity to entity manager.
     */
    public static void addEntity(Entity e)
    {
        allEntities.add(e);
        e.start();
    }

    /**
     * Remove individual entity from entity manager.
     */
    public static void removeEntity(Entity e)
    {
        allEntities.remove(e);
    }

}
