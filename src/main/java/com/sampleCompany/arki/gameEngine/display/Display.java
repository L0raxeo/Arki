package com.sampleCompany.arki.gameEngine.display;

import com.sampleCompany.arki.gameEngine.utils.VersionInfo;
import com.sampleCompany.game.sampleGame.Reference;

import javax.swing.*;
import java.awt.*;

/**
 * The display is the application window,
 * that draws all graphic components of
 * the game onto a canvas.
 *
 * The display contains a JFrame, which
 * contains a Canvas object. The canva
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
public class Display
{

    private JFrame frame;
    private Canvas canvas;

    private final String title;

    /**
     * Holds the width and the height
     * of Display
     */
    private final Dimension size;

    public Display(String title, int width, int height)
    {
        this.title = title;
        size = new Dimension(width, height);

        createDisplay();
    }

    /**
     * Creates the Display:
     * - Creates JFrame and sets attributes according to Reference file
     * - Creates Canvas (where the game draws' graphics)
     * - Adds/attaches the canvas to the JFrame
     */
    private void createDisplay()
    {
        frame = new JFrame(title);
        frame.setSize(size);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(Reference.resizeable);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        canvas = new Canvas();
        canvas.setPreferredSize(size);
        canvas.setMaximumSize(size);
        canvas.setMinimumSize(size);
        canvas.setFocusable(false);

        frame.add(canvas);
        frame.pack();
    }

    // Getters

    public Canvas getCanvas()
    {
        return canvas;
    }

    public JFrame getFrame()
    {
        return frame;
    }

}
