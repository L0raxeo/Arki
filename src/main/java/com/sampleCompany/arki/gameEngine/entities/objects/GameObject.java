package com.sampleCompany.arki.gameEngine.entities.objects;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Entity subtype that is not used in GUIs, UIs, menus...
 * etc. Objects are entities that are placed within the
 * world of the scene, to be interacted with by other
 * entities, other than the direct user.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "2.1",
        releaseDate = "11/21/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public abstract class GameObject extends Entity
{

    // class
    public GameObject(String name, String unlocalizedName, float x, float y, int width, int height)
    {
        super(name, unlocalizedName, x, y, width, height);
    }

    /**
     * Moves object according to speed parameter on x-axis.
     */
    public void moveX(int xSpeed)
    {
        setX(super.getX() + xSpeed);
    }

    /**
     * Moves object according to speed parameter on y-axis.
     */
    public void moveY(int ySpeed)
    {
        setY(super.getY() + ySpeed);
    }

    /**
     * Moves object on both x and y axis according to paramters.
     */
    public void move(int xSpeed, int ySpeed)
    {
        setX(super.getX() + xSpeed);
        setY(super.getY() + ySpeed);
    }

}
