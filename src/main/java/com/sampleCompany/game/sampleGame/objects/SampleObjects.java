package com.sampleCompany.game.sampleGame.objects;

import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.entities.objects.GameObject;
import com.sampleCompany.arki.gameEngine.gfx.Animation;
import com.sampleCompany.arki.gameEngine.utils.FileLoader;

import java.awt.*;

public class SampleObjects extends GameObject
{

    private Animation animation;

    public SampleObjects(String name, String unlocalizedName, float x, float y, int width, int height)
    {
        super(name, unlocalizedName, x, y, width, height);
    }

    @Override
    public void awake()
    {
        animation = new Animation(150, FileLoader.loadImage("/assets/missing_texture_0.png"), FileLoader.loadImage("/assets/missing_texture_1.png"));
    }

    @Override
    public void tick()
    {
        animation.tick();

        move(5, 3);
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(animation.getCurrentFrame(), getDisplayX(), getDisplayY(), width, height, null);
    }

}
