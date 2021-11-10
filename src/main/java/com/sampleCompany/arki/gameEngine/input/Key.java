package com.sampleCompany.arki.gameEngine.input;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * The Key class holds the current
 * state of the key, and any queued
 * states associated with that key.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/10/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Key
{

    private final int keyCode;
    private KeyState state;
    private KeyState queuedState;

    // Class
    public Key(int key, KeyState state)
    {
        this.keyCode = key;
        this.queuedState = state;
    }

    public void setState()
    {
        this.state = this.queuedState;
    }

    public void queueState(KeyState state)
    {
        this.queuedState = state;
    }

    // Getters

    public int getKeyCode()
    {
        return keyCode;
    }

    public KeyState getState()
    {
        return state;
    }

    public KeyState getQueuedState()
    {
        return queuedState;
    }

}
