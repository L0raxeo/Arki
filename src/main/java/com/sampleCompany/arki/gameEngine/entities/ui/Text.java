package com.sampleCompany.arki.gameEngine.entities.ui;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;

import java.awt.*;

/**
 * Raw form of displayed text.
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
public class Text
{

    /**
     * Draws specified text with specified attributes to canvas.
     */
    public static void drawString(Graphics g, String text, int xPos, int yPos, boolean center, Color color, Font font)
    {
        g.setColor(color);
        g.setFont(font);
        int x = xPos;
        int y = yPos;

        if (center)
        {
            FontMetrics fm = g.getFontMetrics(font);
            x = xPos - fm.stringWidth(text) / 2;
            y = (yPos - fm.getHeight() / 2) + fm.getAscent();
        }

        g.drawString(text, x, y);
    }

}
