package com.sampleCompany.arki.gameEngine.entities;

import com.sampleCompany.arki.gameEngine.entities.objects.GameObject;
import com.sampleCompany.arki.gameEngine.utils.Vec2;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.*;

/**
 * Parent version of all entity subtypes.
 * Contains general attributes that all
 * objects/entities inherit.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "2.1",
        releaseDate = "11/24/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public abstract class Entity
{

    // attributes

    protected String name;
    protected String unlocalizedName;
    protected float xWorld, yWorld;
    protected float xDisplay, yDisplay;
    protected int width, height;
    protected float xCenter, yCenter;
    protected Rectangle bounds;

    /**
     * Determines whether entity will be destroyed
     */
    protected boolean active = true;

    // class

    public Entity(String name, String unlocalizedName, float x, float y, int width, int height)
    {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.xWorld = x;
        this.yWorld = y;
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(getWorldX(), getWorldY(), width, height);

        setX(getX());

        awake();
    }

    public Entity(String name, String unlocalizedName, Vec2 position, Vec2 dimensions)
    {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.xWorld = position.a();
        this.yWorld = position.b();
        this.width = (int) dimensions.a();
        this.height = (int) dimensions.b();
        this.bounds = new Rectangle(getWorldX(), getWorldY(), width, height);

        setX(getX());

        awake();
    }

    public Entity(String name, String unlocalizedName, Vec2 position, int width, int height)
    {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.xWorld = position.a();
        this.yWorld = position.b();
        this.width = width;
        this.height = height;
        this.bounds = new Rectangle(getWorldX(), getWorldY(), width, height);

        setX(getX());

        awake();
    }

    public Entity(String name, String unlocalizedName, float x, float y, Vec2 dimensions)
    {
        this.name = name;
        this.unlocalizedName = unlocalizedName;
        this.xWorld = x;
        this.yWorld = y;
        this.width = (int) dimensions.a();
        this.height = (int) dimensions.b();
        this.bounds = new Rectangle(getWorldX(), getWorldY(), width, height);

        setX(getX());

        awake();
    }

    /**
     * Invoked on registry into entity manager.
     */
    protected void awake() {}

    /**
     * Invoked on class instantiation.
     */
    protected void start() {}

    /**
     * Updates entity
     */
    public abstract void tick();

    /**
     * Renders entity, along with any changes
     * made or modified in tick() method.
     *
     * @param g is the drawing tool.
     */
    public abstract void render(Graphics g);

    /**
     * Destroys object and queues it to remove from entity manager.
     */
    public void destroy()
    {
        active = false;

        // update physics
        if (this instanceof GameObject)
        {
            for (GameObject o : EntityManager.getAllGameObjects())
            {
                // Invoke onCollisionExit to object being destroyed
                o.predictCollision((GameObject) this);
            }
        }

        onDestroy();
    }

    /**
     * Is invoked when entity is destroyed.
     */
    public void onDestroy()
    {

    }

    // Getters

    public Rectangle getCurBounds()
    {
        return new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    // World Positions

    public int getX()
    {
        return (int) xWorld;
    }

    public int getY()
    {
        return (int) yWorld;
    }

    public int getCenterX()
    {
        return (int) xCenter;
    }

    public int getCenterY()
    {
        return (int) yCenter;
    }

    public int getWorldX()
    {
        return (int) xWorld;
    }

    public int getWorldY()
    {
        return (int) yWorld;
    }

    // Display position

    public int getDisplayX()
    {
        return (int) xDisplay;
    }

    public int getDisplayY()
    {
        return (int) yDisplay;
    }

    public void setDisplayX(float x)
    {
        this.xDisplay = x;
    }

    public void setDisplayY(float y)
    {
        this.yDisplay = y;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public boolean isActive()
    {
        return active;
    }

    // Setters

    public void setX(int x)
    {
        this.xWorld = x;
        this.xCenter = xWorld + (width / 2f);
    }

    public void setY(int y)
    {
        this.yWorld = y;
        this.yCenter = yWorld + (height / 2f);
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

}
