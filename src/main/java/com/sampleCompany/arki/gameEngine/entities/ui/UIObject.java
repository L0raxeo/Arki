package com.sampleCompany.arki.gameEngine.entities.ui;

import com.sampleCompany.arki.gameEngine.entities.Entity;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * User interface objects are used in menus
 * in the form of buttons, text, progress bars,
 * text fields... etc.
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
public abstract class UIObject extends Entity
{

    public UIObject(String name, String unlocalizedName, float x, float y, int width, int height)
    {
        super(name, unlocalizedName, x, y, width, height);
    }

}
