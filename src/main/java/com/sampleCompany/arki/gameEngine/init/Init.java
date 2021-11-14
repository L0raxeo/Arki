package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * Annotation required for each initializer
 * for it to be detectable by the registry.
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
public @interface Init {}
