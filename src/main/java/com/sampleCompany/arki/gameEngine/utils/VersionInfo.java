package com.sampleCompany.arki.gameEngine.utils;

import java.lang.annotation.Documented;

/**
 * Version info is a documentation
 * interface that is used to help
 * label code written in the game
 * engine (Arki).
 *
 * @author Lorcan Andrew Cheng
 */
@Documented
public @interface VersionInfo
{

    /**
     * Current version when this revision was written.
     *
     * @return current version.
     */
    String version();

    /**
     * Date this revision was written
     *
     * @return date of revision
     */
    String releaseDate();

    /**
     * First version this class or function
     * was introduced.
     *
     * @return first introduction of functionality
     */
    String since();

    /**
     * All contributors to this revision.
     *
     * @return all revision contributions.
     */
    String[] contributors();

}
