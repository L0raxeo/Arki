package com.sampleCompany.arki.gameEngine.entities.objects;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Physics
{

    float GRAVITATIONAL_ACCELERATION();

    float TERMINAL_VELOCITY();

    float mass();

}
