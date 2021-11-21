package com.sampleCompany.arki.gameEngine.gfx;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.entities.objects.Object;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;
import com.sampleCompany.game.sampleGame.Reference;

@VersionInfo(
        version = "1.0",
        releaseDate = "11/20/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Camera
{

    /**
     * Entity that camera is fixed to/following
     */
    public Object focusedEntity = null;

    /**
     * 2D position of camera in the scene/world of the game.
     */
    public float xWorld = 0, yWorld = 0;

    // Empty class
    public Camera() {}

    /**
     * Updates camera position which dictates offsets.
     */
    public void tick()
    {
        if (focusedEntity != null)
        {
            xWorld = focusedEntity.getWorldX() - ((Reference.displayWidth / 2f) - (focusedEntity.getWidth() / 2f));
            yWorld = focusedEntity.getWorldY() - ((Reference.displayHeight / 2f) - (focusedEntity.getHeight() / 2f));
        }
    }

    /**
     * Checks whether specified entity is visible within screen.
     */
    public boolean isEntityVisible(Entity e)
    {
        return !(e.getDisplayX() + e.getWidth() < 0) &&
                !(e.getDisplayX() > Reference.displayWidth) &&
                !(e.getDisplayY() + e.getHeight() < 0) &&
                !(e.getDisplayY() > Reference.displayHeight);
    }

    /**
     * Fixes camera on specified object.
     */
    public void focus(Object e)
    {
        focusedEntity = e;
    }

    /**
     * @return 'x' coordinate of camera's perspective/position of other objects.
     */
    public float xOffset()
    {
        return -xWorld;
    }

    /**
     * @return 'y' coordinate of camera's perspective/position of other objects.
     */
    public float yOffset()
    {
        return -yWorld;
    }

    public void setPosition(float x, float y)
    {
        xWorld = x;
        yWorld = y;
    }

    /**
     * Set camera position to specified 'x'
     */
    public void setX(float x)
    {
        this.xWorld = x;
    }

    /**
     * Set camera position to specified 'y'
     */
    public void setY(float y)
    {
        this.yWorld = y;
    }

}
