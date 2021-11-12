package com.sampleCompany.arki.gameEngine.input.keyboard;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * All states that a key can inherit.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/11/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public enum KeyState
{
    HELD,
    PRESSED,
    RELEASED,
    IDLE
}
