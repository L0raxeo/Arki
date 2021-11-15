package com.sampleCompany.arki.gameEngine.entities.ui;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Contains onClick() method used in all
 * UIObjects that can be interacted with.
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
public interface ClickListener
{

    /**
     * Invoked when clicked on with mouse.
     */
    void onClick();

}
