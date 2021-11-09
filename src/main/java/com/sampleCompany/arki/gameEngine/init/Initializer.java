package com.sampleCompany.arki.gameEngine.init;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * This interface is used to implement
 * the initialization sequence into the
 * associated class.
 *
 * If a class needs to utilize the init
 * sequence (pre init, init, post init),
 * then it can implement this class and
 * the respective methods will be invoked
 * appropriately.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/8/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public interface Initializer
{

    /**
     * Initialization that does not depend on
     * any other processes.
     */
    default void preInit() throws Exception {}

    /**
     * Initialization that depends on processes
     * from preInit().
     */
    default void init() throws Exception {}

    /**
     * General initialization - initialization
     * of code variables, processes with
     * dependencies, and other surface ideas.
     */
    default void postInit() throws Exception {}

}
