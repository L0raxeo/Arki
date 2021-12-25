package com.sampleCompany.game.sampleGame.scenes;

import com.sampleCompany.arki.gameEngine.entities.EntityManager;
import com.sampleCompany.arki.gameEngine.scenes.DefaultScene;
import com.sampleCompany.arki.gameEngine.scenes.Scene;
import com.sampleCompany.arki.gameEngine.scenes.SceneInfo;
import com.sampleCompany.game.sampleGame.objects.SampleEntityMap;

import java.awt.*;

@DefaultScene
@SceneInfo(name = "Sample Scene", sceneID = "sampleScene", sideView = true, gravitationalConstant = 9.8)
public class SampleScene extends Scene
{

    @Override
    public void awake()
    {

    }

    @Override
    public void start()
    {
        EntityManager.mapEntities(new SampleEntityMap());
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
