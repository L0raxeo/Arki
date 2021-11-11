package com.sampleCompany.arki.gameEngine.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

/**
 * File loader used to load IO files.
 * These files do not include any processes
 * such as JAR files (or plugins).
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/10/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class FileLoader
{

    /**
     * Reads first line in file with associated path specified.
     *
     * @param path the path of the file.
     * @return the read string of the file.
     */
    public static String readFile(String path) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));
        return br.readLine();
    }

    /**
     * Reads first line in specified file.
     *
     * @param file being read.
     * @return the first line in the file.
     */
    public static String readFile(File file) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
        return br.readLine();
    }

    /**
     * Reads specified line in specified file.
     *
     * @param path to file being read.
     * @param lineNum of line being returned.
     * @return content of line in specified file.
     */
    public static String readLine(String path, int lineNum) throws IOException
    {
        BufferedReader br;
        String line;
        ArrayList<String> allLines = new ArrayList<>();

        try
        {
            br = new BufferedReader(new FileReader(path));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        while ((line = br.readLine()) != null)
        {
            allLines.add(line);
        }

        return allLines.get(lineNum - 1);
    }

    /**
     * Reads each line in a text file
     * @param path of the file
     * @return all lines of the text file (in form of array list), separated into different indexes in the array list
     */
    public static ArrayList<String> readAllLinesFromFile(String path) throws IOException
    {
        BufferedReader br;

        try
        {
            br = new BufferedReader(new FileReader(path));
        }
        catch (FileNotFoundException e)
        {
            return null;
        }

        String line;
        ArrayList<String> allLines = new ArrayList<>();

        while ((line = br.readLine()) != null)
        {
            allLines.add(line);
        }

        return allLines;
    }

    /**
     * Writes in a file associated with the path parameter.
     *
     * @param path the path of the file being modified.
     * @param data the data that is being written into the specified file.
     */
    public static void writeFile(String path, String data) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));
        bw.write(String.valueOf(data));
        bw.flush();
        bw.close();
    }

    /**
     *
     * Writes a file associated with a path parameter.
     *
     * @param path to file being modified
     * @param dataPerLine unbound array of data per line
     */
    public static void writeFile(String path, String... dataPerLine) throws IOException
    {
        BufferedWriter bw = new BufferedWriter(new FileWriter(path));

        for (String data : dataPerLine)
        {
            bw.write(data);
            bw.newLine();
        }

        bw.flush();
        bw.close();
    }

    /**
     * Writes specified data to line in file associated
     * path.
     *
     * @param path of file being written in.
     * @param data being written into file.
     * @param line that the data is being written on.
     */
    public static void writeFile(String path, String data, int line) throws IOException
    {
        ArrayList<String> oldFileContent = readAllLinesFromFile(path);

        if (oldFileContent == null)
        {
            System.out.println("File does not exist");
            return;
        }

        BufferedWriter bw = new BufferedWriter(new FileWriter(path));

        System.out.println(oldFileContent.size());

        if (line > oldFileContent.size())
        {
            for (int i = 0; i < line - 1; i++)
            {
                if (i < oldFileContent.size())
                {
                    bw.write(oldFileContent.get(i));
                }

                bw.newLine();
            }

            bw.write(data);
        }
        else if (line < oldFileContent.size())
        {
            for (int i = 0; i < oldFileContent.size(); i++)
            {
                if (i == line - 1)
                {
                    bw.write(data);
                }
                else
                {
                    bw.write(oldFileContent.get(i));
                }

                if (i < oldFileContent.size() - 1)
                    bw.newLine();
            }
        }
        else // if (line == oldFileContent.size())
        {
            for (int i = 0; i < oldFileContent.size(); i++)
            {
                if (i != oldFileContent.size() - 1)
                    bw.write(oldFileContent.get(i));
                else
                {
                    bw.write(data);
                    break;
                }

                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
    }

    /**
     * Creates a directory with the path specified in the parameters.
     *
     * @param path to directory being created
     */
    public static void createDir(String path)
    {
        File newDir = new File(path);

        if (!newDir.exists())
        {
            if (!newDir.mkdirs())
            {
                try
                {
                    throw new IOException();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Gives the buffered image form of the file
     * associated with the path specified in the
     * parameters.
     */
    public static BufferedImage loadImage(String path)
    {
        try
        {
            return ImageIO.read(Objects.requireNonNull(FileLoader.class.getResource(path)));
        }
        catch (IOException e)
        {
            e.printStackTrace();
            System.exit(1);
        }

        return null;
    }

}