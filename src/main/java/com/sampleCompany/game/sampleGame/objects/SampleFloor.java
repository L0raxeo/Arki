package com.sampleCompany.game.sampleGame.objects;

import com.sampleCompany.arki.gameEngine.entities.objects.GameObject;

import java.awt.*;

public class SampleFloor extends GameObject
{

    public SampleFloor(String name, String unlocalizedName, float x, float y, int width, int height, boolean isSolid)
    {
        super(name, unlocalizedName, x, y, width, height, isSolid);
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(getDisplayX(), getDisplayY(), width, height);
    }

}
