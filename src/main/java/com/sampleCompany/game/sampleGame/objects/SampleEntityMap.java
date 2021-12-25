package com.sampleCompany.game.sampleGame.objects;

import com.sampleCompany.arki.gameEngine.entities.EntityMap;
import com.sampleCompany.arki.gameEngine.misc.Reference;

import java.util.Random;

public class SampleEntityMap extends EntityMap
{

    @Override
    public void mapEntities()
    {
        Random r = new Random();

        addEntity(new SampleObjects("Base Creature", "baseCreature1", 400, 50, 25, 25));
        addEntity(new SampleObjects("Base Creature", "baseCreature2", 400, 0, 25, 25));
        //addEntity(new SampleObjects("Base Creature", "baseCreature3", Reference.displayWidth - 200, 100, 25, 25));

        addEntity(new SampleFloor("Sample Floor", "sampleFloor", 0, 400, Reference.displayWidth, 25, true));
    }

}
