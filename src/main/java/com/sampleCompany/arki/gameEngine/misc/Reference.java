package com.sampleCompany.arki.gameEngine.misc;

import com.sampleCompany.arki.gameEngine.init.Init;
import com.sampleCompany.arki.gameEngine.init.Initializer;
import com.sampleCompany.arki.gameEngine.utils.FileLoader;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.util.Objects;

/**
 * Game info is stored in here during runtime.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "3.0",
        releaseDate = "12/25/2021",
        since = "3.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)

@Init
public class Reference implements Initializer
{

    // Game Information

    public static String NAME = "Default Game";
    public static String GAMEID = "defaultGame";
    public static String VERSION = "1.0";
    public static int targetFPS = 60;

    // Display Information

    public static int displayWidth = 1080;
    public static int displayHeight = 720;
    public static boolean resizeable = false;

    @Override
    public void preInit() throws Exception
    {
        NAME = Objects.requireNonNull(FileLoader.readLine("libs/resources/game_info.txt", 1)).split("=")[1];
        GAMEID = Objects.requireNonNull(FileLoader.readLine("libs/resources/game_info.txt", 2)).split("=")[1];
        VERSION = Objects.requireNonNull(FileLoader.readLine("libs/resources/game_info.txt", 3)).split("=")[1];

        // display information
        displayWidth = Integer.parseInt(Objects.requireNonNull(FileLoader.readLine("libs/resources/game_info.txt", 4)).split("=")[1]);
        displayHeight = Integer.parseInt(Objects.requireNonNull(FileLoader.readLine("libs/resources/game_info.txt", 5)).split("=")[1]);
        targetFPS = Integer.parseInt(Objects.requireNonNull(FileLoader.readLine("libs/resources/game_info.txt", 6)).split("=")[1]);
        resizeable = Boolean.parseBoolean(Objects.requireNonNull(FileLoader.readLine("libs/resources/game_info.txt", 7)).split("=")[1]);
    }
}
