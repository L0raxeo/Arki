package com.sampleCompany.arki.gameEngine.input.mouse;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

/**
 * The mouse button class holds the current
 * state of the mouse button, and any queued
 * states associated with that button.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/20/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class MBtn
{

    /**
     * Mouse button reference code.
     */
    private final int btnCode;
    /**
     * Current press state of button.
     */
    private MBtnState state;
    /**
     * Queued press state of button.
     */
    private MBtnState queuedState;

    // Class
    public MBtn(int btn, MBtnState state)
    {
        this.btnCode = btn;
        this.queuedState = state;
    }

    /**
     * Sets button press state to queued state.
     */
    public void setState()
    {
        this.state = this.queuedState;
    }

    /**
     * Queues specified button press state to be set.
     */
    public void queueState(MBtnState state)
    {
        this.queuedState = state;
    }

    // Getters

    /**
     * Gets code of this button.
     */
    public int getBtnCode()
    {
        return btnCode;
    }

    /**
     * Gets current state of mouse button.
     */
    public MBtnState getState()
    {
        return state;
    }

    /**
     * Gets queued state of mouse button.
     */
    public MBtnState getQueuedState()
    {
        return queuedState;
    }

}
