package com.sampleCompany.arki.gameEngine.input;

public class Key
{

    private final int key;
    private KeyState state;

    public Key(int key, KeyState state)
    {
        this.key = key;
        this.state = state;
    }

    public void setState(KeyState state)
    {
        this.state = state;
    }

    // Getters

    public int getKey()
    {
        return key;
    }

    public KeyState getState()
    {
        return state;
    }

}
