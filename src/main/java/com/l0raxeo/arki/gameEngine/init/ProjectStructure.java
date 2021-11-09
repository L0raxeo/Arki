package com.l0raxeo.arki.gameEngine.init;

import com.l0raxeo.arki.gameEngine.utils.FileLoader;
import com.l0raxeo.arki.gameEngine.utils.VersionInfo;
import com.l0raxeo.sampleGame.Reference;

import java.io.IOException;

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
        releaseDate = "11/9/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class ProjectStructure implements Initializer
{

    /**
     * Creates root asset directory, along with child directories.
     */
    @Override
    public void preInit()
    {
        FileLoader.createDir("assets/resources");
    }

    /**
     * Creates specific resources and assets, within the directories
     * created in the preInit() method.
     */
    @Override
    public void init() throws IOException
    {
        FileLoader.writeFile("assets/resources/game_info.txt", Reference.NAME, Reference.GAMEID, Reference.VERSION);
    }

}
