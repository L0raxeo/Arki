package com.sampleCompany.arki.gameEngine.scenes;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Carries basic scene information.
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
public @interface SceneInfo
{

    // Getting attributes is as follows
    // sampleScene.getClass().getAnnotation(SceneInfo.class).attribute()

    /**
     * A user friendly name for scene
     */
    String name() default "";

    /**
     * The unique scene identifier for this scene
     */
    String sceneID();

    /**
     * POV of camera: 'side' or 'top'
     */
    boolean sideView();

    /**
     * Gravitational constant (only applicable if scene has side view).
     */
    double gravitationalConstant();

}