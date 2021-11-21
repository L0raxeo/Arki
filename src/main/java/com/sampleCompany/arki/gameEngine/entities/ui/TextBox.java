package com.sampleCompany.arki.gameEngine.entities.ui;

import com.sampleCompany.arki.gameEngine.gfx.Animation;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.*;

/**
 * Object form of text used instead in order
 * for further customization such as animations.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/20/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public abstract class TextBox extends UIObject
{

    public String text;
    public boolean center;
    public Color color;
    public Font font;
    Animation animation = null;

    // class
    public TextBox(String name, String unlocalizedName, float x, float y, String text, Animation animation)
    {
        super(name, unlocalizedName, x, y, 0, 0);

        this.text = text;
        this.animation = animation;
    }

    // class
    public TextBox(String name, String unlocalizedName, String text, float x, float y, boolean center, Color color, Font font)
    {
        super(name, unlocalizedName, x, y, 0, 0);
        this.text = text;
        this.center = center;
        this.color = color;
        this.font = font;
    }

    /**
     * Updates text as well as animation if assigned.
     */
    @Override
    public  void tick()
    {
        if (animation != null)
            animation.tick();
    }

    /**
     * Renders text to screen.
     */
    @Override
    public void render(Graphics g)
    {
        Text.drawString(g, text, (int) xWorld, (int) yWorld, center, color, font);
    }

}
