package com.sampleCompany.arki.gameEngine.scenes;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks the initial scene that the
 * game will activate initially by
 * default.
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
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DefaultScene {}