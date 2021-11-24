package com.sampleCompany.game.sampleGame.objects;

import com.sampleCompany.arki.gameEngine.entities.objects.GameObject;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.Physics;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.collision.Collider;
import com.sampleCompany.arki.gameEngine.gfx.Animation;
import com.sampleCompany.arki.gameEngine.input.keyboard.KeyManager;
import com.sampleCompany.arki.gameEngine.utils.FileLoader;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Objects;

@Physics(GRAVITATIONAL_ACCELERATION = 1, TERMINAL_VELOCITY = 300, mass = 5)
public class SampleObjects extends GameObject
{

    private Animation animation;

    public SampleObjects(String name, String unlocalizedName, float x, float y, int width, int height)
    {
        super(name, unlocalizedName, x, y, width, height, true);
    }

    @Override
    public void awake()
    {
        animation = new Animation(150, FileLoader.loadImage("/assets/missing_texture_0.png"), FileLoader.loadImage("/assets/missing_texture_1.png"));
    }

    @Override
    protected void start()
    {

    }

    @Override
    public void tick()
    {
        animation.tick();

        if (!Objects.equals(unlocalizedName, "baseCreature1"))
        {
            if (KeyManager.isHeld(KeyEvent.VK_LEFT))
                moveX(-2);
            else if (KeyManager.isHeld(KeyEvent.VK_RIGHT))
                moveX(2);
        }
        else
        {
            if (KeyManager.isHeld('a'))
                moveX(-2);
            else if (KeyManager.isHeld('d'))
                moveX(2);
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(animation.getCurrentFrame(), getDisplayX(), getDisplayY(), width, height, null);
    }

    @Override
    public void onCollision(Collider collider)
    {
        System.out.println("colliding");
    }

    @Override
    public void onCollisionExit(Collider collider)
    {
        System.out.println("exiting");
    }

    @Override
    public void onCollisionStay(Collider collider)
    {

    }

}
