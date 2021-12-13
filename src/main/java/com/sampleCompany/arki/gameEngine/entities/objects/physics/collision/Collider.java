package com.sampleCompany.arki.gameEngine.entities.objects.physics.collision;

import com.sampleCompany.arki.gameEngine.entities.objects.GameObject;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Game object represented in the form of a collider.
 * The collider inherits the associated game object
 * as well as additional collision info such as
 * the collision type.
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
public class Collider
{

    public enum CollisionType
    {
        TOP,
        BOTTOM,
        LEFT,
        RIGHT
    }

    public CollisionType type;
    public GameObject gameObject;

    public Collider(GameObject gameObject, CollisionType type)
    {
        this.gameObject = gameObject;
        this.type = type;
    }

    public CollisionType getType()
    {
        return type;
    }

    public GameObject getGameObject()
    {
        return gameObject;
    }

}
