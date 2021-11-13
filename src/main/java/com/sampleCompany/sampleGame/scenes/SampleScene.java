package com.sampleCompany.sampleGame.scenes;

import com.sampleCompany.arki.gameEngine.scenes.DefaultScene;
import com.sampleCompany.arki.gameEngine.scenes.Scene;
import com.sampleCompany.arki.gameEngine.scenes.SceneInfo;

import java.awt.*;

@DefaultScene
@SceneInfo(name = "Sample Scene", sceneID = "sampleScene")
public class SampleScene extends Scene
{

    public SampleScene()
    {
        super();
    }

    @Override
    public void awake()
    {
        System.out.println("waking");
    }

    @Override
    public void start() {
        System.out.println("starting");
    }

    @Override
    public void tick()
    {

    }

    @Override
    public void render(Graphics g)
    {

    }

}
