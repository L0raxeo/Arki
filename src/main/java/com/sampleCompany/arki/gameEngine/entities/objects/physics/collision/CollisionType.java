package com.sampleCompany.arki.gameEngine.entities.objects.physics.collision;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Side of game object that is colliding.
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
public enum CollisionType
{
    TOP,
    BOTTOM,
    LEFT,
    RIGHT
}
