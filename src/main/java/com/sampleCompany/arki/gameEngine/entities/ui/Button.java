package com.sampleCompany.arki.gameEngine.entities.ui;

import com.sampleCompany.arki.gameEngine.input.mouse.MouseManager;
import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

/**
 * Button object, subtype of UIObject used
 * to invoke a specific piece of code when
 * clicked on. Its presented visually on
 * the canvas by an image, and/or text.
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/14/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Button extends UIObject implements ClickListener
{

    private final BufferedImage[] textures;
    private final ClickListener clicker;

    private String text = null;
    private Color color;
    private Font font;

    private final boolean clickable;
    private boolean hovering;

    public Button(String name, String unlocalizedName, float x, float y, int width, int height, BufferedImage[] textures, boolean clickable, ClickListener clicker)
    {
        super(name, unlocalizedName, x, y, width, height);

        this.clicker = clicker;

        this.textures = textures;
        this.clickable = clickable;
    }

    public Button(String name, String unlocalizedName, float x, float y, int width, int height, String text, Color color, Font font, BufferedImage[] textures, boolean clickable, ClickListener clicker)
    {
        super(name, unlocalizedName, x, y, width, height);

        this.clicker = clicker;

        this.textures = textures;
        this.clickable = clickable;
        this.text = text;
        this.color = color;
        this.font = font;
    }

    @Override
    public void tick()
    {
        hovering = bounds.contains(MouseManager.getMouseX(), MouseManager.getMouseY());

        if (hovering && MouseManager.onPress(MouseEvent.BUTTON1))
            onPress();
    }

    @Override
    public void render(Graphics g)
    {
        if (hovering)
        {
            g.drawImage(textures[1], (int) x, (int) y, width, height, null);
            if (text != null)
                Text.drawString(g, text, (int) x + (width / 2), (int) y + (height / 2), true, color, font);
        }
        else
        {
            g.drawImage(textures[0], (int) x, (int) y, width, height, null);
            if (text != null)
                Text.drawString(g, text, (int) x + (width / 2), (int) y + (height / 2) - 9, true, color, font);
        }
    }

    public void onPress()
    {
        if (clickable)
            clicker.onClick();
    }

    @Override
    public void onClick() {}

}
