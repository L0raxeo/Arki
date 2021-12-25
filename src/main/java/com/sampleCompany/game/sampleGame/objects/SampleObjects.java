package com.sampleCompany.game.sampleGame.objects;

import com.sampleCompany.arki.gameEngine.entities.objects.GameObject;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.Physics;
import com.sampleCompany.arki.gameEngine.entities.objects.physics.collision.Collider;
import com.sampleCompany.arki.gameEngine.gfx.Animation;
import com.sampleCompany.arki.gameEngine.input.keyboard.KeyManager;
import com.sampleCompany.arki.gameEngine.utils.FileLoader;

import java.awt.*;
import java.awt.event.KeyEvent;

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
        animation = new Animation(150, FileLoader.loadResourceImage("assets/missing_texture_0.png"), FileLoader.loadResourceImage("assets/missing_texture_1.png"));
    }

    @Override
    protected void start()
    {

    }

    @Override
    public void tick()
    {
        animation.tick();

        if (unlocalizedName.equals("baseCreature1"))
        {
            moveX(3, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);

            if (KeyManager.onPress(KeyEvent.VK_UP))
                addForce(0, 10);
        }
        else if (unlocalizedName.equals("baseCreature2"))
        {
            moveX(3, 'a', 'd');

            if (KeyManager.onPress('w'))
                addForce(0, 10);
            //if (KeyManager.onPress('s'))
            //    addForce(0, -10);
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
        System.out.println("collided");
    }

    @Override
    public void onCollisionExit(GameObject gameObject)
    {
        System.out.println("exited");
    }

    @Override
    public void onCollisionStay(Collider collider)
    {

    }

}
