package com.sampleCompany.game.sampleGame.objects;

import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.entities.objects.GameObject;
import com.sampleCompany.arki.gameEngine.gfx.Animation;
import com.sampleCompany.arki.gameEngine.utils.FileLoader;

import java.awt.*;
import java.util.Random;

public class SampleObjects extends GameObject
{

    private Animation animation;

    public int xSpeed, ySpeed;
    public int xBaseSpeed = 0, yBaseSpeed = 0;

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
    protected void start()
    {
        Random r = new Random();

        xSpeed = r.nextInt(xBaseSpeed + 5 - (xBaseSpeed - 5)) + xBaseSpeed - 5;
        ySpeed = r.nextInt(yBaseSpeed + 5 - (yBaseSpeed - 5)) + yBaseSpeed - 5;

        EntityManager.focusCam(this);
    }

    @Override
    public void tick()
    {
        move(xSpeed, ySpeed);
        super.checkCollision();

        animation.tick();
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(animation.getCurrentFrame(), getDisplayX(), getDisplayY(), width, height, null);
    }

    @Override
    public void onCollision(GameObject collider)
    {

    }

    @Override
    public void onCollisionExit(GameObject collider)
    {

    }

    @Override
    public void onCollisionStay(GameObject collider)
    {

    }

}
