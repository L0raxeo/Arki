package com.sampleCompany.arki.gameEngine.entities;

import com.sampleCompany.arki.gameEngine.scenes.Scene;

import java.util.ArrayList;

public abstract class EntityMap
{

    /**
     * All entities in the entity map.
     */
    public final ArrayList<Entity> entities = new ArrayList<>();

    /**
     * Scene associated with entity map.
     */
    private final Scene scene;

    // class
    public EntityMap(Scene scene)
    {
        this.scene = scene;

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

    /**
     * Get scene associated with entity map.
     */
    public Scene getScene()
    {
        return scene;
    }

}
