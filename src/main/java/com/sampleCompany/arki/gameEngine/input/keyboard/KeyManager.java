package com.sampleCompany.arki.gameEngine.input.keyboard;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

/**
 * Manages all keys, and can be used to
 * retrieve information about the current
 * state of a specific key.
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
public class KeyManager implements KeyListener
{

    /**
     * All registered keys in the program.
     */
    private static final ArrayList<Key> allKeys = new ArrayList<>();

    /**
     * Updates all keys and their states.
     */
    public void tick()
    {
        for (Key k : allKeys)
        {
            k.setState();

            if (k.getState() == KeyState.RELEASED)
                k.queueState(KeyState.IDLE);
            else if (k.getState() == KeyState.PRESSED)
                k.queueState(KeyState.HELD);
        }
    }

    /**
     * @return true if the specified key
     * associated with the key event is
     * being held.
     */
    public static boolean isHeld(int e)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == e && k.getState() == KeyState.HELD || k.getState() == KeyState.PRESSED)
                return true;
        }

        return false;
    }

    /**
     * @return true if the specified key
     * associated with the key event is
     * being pressed.
     */
    public static boolean onPress(int e)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == e && k.getState() == KeyState.PRESSED)
                return true;
        }

        return false;
    }

    /**
     * @return true if the specified key
     * associated with the key event is
     * being pressed.
     */
    public static boolean onRelease(int e)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == e && k.getState() == KeyState.RELEASED)
                return true;
        }

        return false;
    }

    // Implemented methods

    // Is invoked once key has gone down, and released
    @Override
    public void keyTyped(KeyEvent e) {}

    // Is invoked while key is held
    @Override
    public void keyPressed(KeyEvent e)
    {
        for (Key k : allKeys)
        {
            if (k.getState() == KeyState.HELD)
                return;

            if (k.getKeyCode() == e.getKeyCode() && k.getState() == KeyState.IDLE)
            {
                k.queueState(KeyState.PRESSED);
                return;
            }
        }

        allKeys.add(new Key(e.getKeyCode(), KeyState.PRESSED));
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        for (Key k : allKeys)
        {
            if (k.getKeyCode() == e.getKeyCode())
                k.queueState(KeyState.RELEASED);
        }
    }

}
