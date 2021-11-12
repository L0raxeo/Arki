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
        releaseDate = "11/11/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class MBtn
{

    private final int btnCode;
    private MBtnState state;
    private MBtnState queuedState;

    // Class
    public MBtn(int btn, MBtnState state)
    {
        this.btnCode = btn;
        this.queuedState = state;
    }

    public void setState()
    {
        this.state = this.queuedState;
    }

    public void queueState(MBtnState state)
    {
        this.queuedState = state;
    }

    // Getters

    public int getBtnCode()
    {
        return btnCode;
    }

    public MBtnState getState()
    {
        return state;
    }

    public MBtnState getQueuedState()
    {
        return queuedState;
    }

}
