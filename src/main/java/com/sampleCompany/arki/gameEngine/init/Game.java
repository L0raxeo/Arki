package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.Engine;
import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.scenes.SceneManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Initializer for game processes.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/12/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Game implements Initializer
{

    @Override
    public void preInit() throws Exception
    {
        Engine.sceneManager = new SceneManager();
        Engine.entityManager = new EntityManager();
    }

}
