package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.utils.FileLoader;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Loads the general project structure, including
 * organization directories such as resources,
 * stacktrace, etc...
 *
 * Is the initializer of the project structure.
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
@Init
public class ProjectStructure implements Initializer
{

    /**
     * Creates root asset directory, along with child directories.
     */
    @Override
    public void preInit()
    {
        FileLoader.createDir("libs/resources");
        FileLoader.createDir("bin/assets");
    }

}
