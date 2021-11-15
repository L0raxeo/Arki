package com.sampleCompany.arki.gameEngine.entities;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.util.ArrayList;

/**
 * Container for a large quantity of entities to
 * be registered all at once into the entity manager.
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
public abstract class EntityMap
{

    /**
     * All entities in the entity map.
     */
    public final ArrayList<Entity> entities = new ArrayList<>();

    // class
    public EntityMap()
    {
        mapEntities();
    }

    /**
     * Stores instantiations of entities.
     */
    public abstract void mapEntities();

    /**
     * Used to instantiate and register entities at once.
     */
    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    /**
     * Get all entities within map.
     */
    public ArrayList<Entity> getEntities()
    {
        return entities;
    }

}
