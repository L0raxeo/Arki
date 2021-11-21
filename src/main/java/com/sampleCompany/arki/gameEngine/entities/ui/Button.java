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
        releaseDate = "11/20/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class Button extends UIObject implements ClickListener
{

    /**
     * All button state textures:
     * - highlighted
     * - idle
     */
    private final BufferedImage[] textures;

    /**
     * Implemented method invoked on press.
     */
    private final ClickListener clicker;

    // Font

    /**
     * Text overlaying button texture (optional)
     */
    private String text = null;

    /**
     * Color of button text.
     */
    private Color color;

    /**
     * Font of button text.
     */
    private Font font;

    /**
     * Defines ability to click button.
     */
    private final boolean clickable;

    /**
     * Determines current state of cursor associated with button.
     * Returns whether mouse is hovering over button.
     */
    private boolean hovering;

    // Class

    /**
     * Plain (no text) button.
     */
    public Button(String name, String unlocalizedName, float x, float y, int width, int height, BufferedImage[] textures, boolean clickable, ClickListener clicker)
    {
        super(name, unlocalizedName, x, y, width, height);

        this.clicker = clicker;

        this.textures = textures;
        this.clickable = clickable;
    }

    // Class

    /**
     * Text button.
     */
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

    /**
     * Updates mouse and its attributes, as well as checking mouse relation to button.
     * Checks whether mouse is hovering, and if the button is clicked.
     */
    @Override
    public void tick()
    {
        hovering = bounds.contains(MouseManager.getMouseX(), MouseManager.getMouseY());

        if (hovering && MouseManager.onPress(MouseEvent.BUTTON1))
            onPress();
    }

    /**
     * Renders all changes to the button, and final attributes assigned at instantiation.
     * @param g is the drawing tool.
     */
    @Override
    public void render(Graphics g)
    {
        if (hovering)
        {
            g.drawImage(textures[1], (int) xWorld, (int) yWorld, width, height, null);
            if (text != null)
                Text.drawString(g, text, (int) xWorld + (width / 2), (int) yWorld + (height / 2), true, color, font);
        }
        else
        {
            g.drawImage(textures[0], (int) xWorld, (int) yWorld, width, height, null);
            if (text != null)
                Text.drawString(g, text, (int) xWorld + (width / 2), (int) yWorld + (height / 2) - 9, true, color, font);
        }
    }

    /**
     * Invokes implemented onClick method on mouse press on button.
     */
    public void onPress()
    {
        if (clickable)
            clicker.onClick();
    }

    /**
     * Implemented method invoked on click.
     */
    @Override
    public void onClick() {}

}
