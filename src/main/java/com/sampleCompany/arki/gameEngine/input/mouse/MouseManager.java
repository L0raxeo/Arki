package com.sampleCompany.arki.gameEngine.input.mouse;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

/**
 * Manages all mouse buttons, and can be
 * used to retrieve information about the
 * current state of a specific mouse button.
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
public class MouseManager implements MouseListener
{

    /**
     * All registered mouse buttons in the program.
     */
    private static final ArrayList<MBtn> allMBtns = new ArrayList<>();

    /**
     * Updates all mouse buttons and their states.
     */
    public void tick()
    {
        for (MBtn mBtn : allMBtns)
        {
            mBtn.setState();

            if (mBtn.getState() == MBtnState.RELEASED)
                mBtn.queueState(MBtnState.IDLE);
            else if (mBtn.getState() == MBtnState.PRESSED)
                mBtn.queueState(MBtnState.HELD);
        }
    }

    public static boolean isHeld(int e)
    {
        for (MBtn mBtn : allMBtns)
        {
            if (mBtn.getBtnCode() == e && mBtn.getState() == MBtnState.HELD || mBtn.getState() == MBtnState.PRESSED)
                return true;
        }

        return false;
    }

    public static boolean onPress(int e)
    {
        for (MBtn mBtn : allMBtns)
        {
            if (mBtn.getBtnCode() == e && mBtn.getState() == MBtnState.PRESSED)
                return true;
        }

        return false;
    }

    public static boolean onRelease(int e)
    {
        for (MBtn mBtn : allMBtns)
        {
            if (mBtn.getBtnCode() == e && mBtn.getState() == MBtnState.RELEASED)
                return true;
        }

        return false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e)
    {
        for (MBtn mBtn : allMBtns)
        {
            if (mBtn.getState() == MBtnState.HELD)
                return;

            if (mBtn.getBtnCode() == e.hashCode() && mBtn.getState() == MBtnState.IDLE)
            {
                mBtn.queueState(MBtnState.PRESSED);
                return;
            }
        }

        allMBtns.add(new MBtn(e.hashCode(), MBtnState.PRESSED));
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        for (MBtn mBtn : allMBtns)
        {
            if (mBtn.getBtnCode() == e.hashCode())
                mBtn.queueState(MBtnState.RELEASED);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

}
