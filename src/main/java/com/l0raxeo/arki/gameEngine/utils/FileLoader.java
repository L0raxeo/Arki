package com.l0raxeo.arki.gameEngine.utils;

import java.io.*;
import java.util.ArrayList;

/**
 * File loader used to load IO files.
 * These files do not include any processes
 * such as JAR files (or plugins).
 *
 * @author Lorcan A. Cheng
 */
@VersionInfo(
        version = "1.0",
        releaseDate = "11/8/2021",
        since = "1.0",
        contributors = {
                "Lorcan Andrew Cheng"
        }
)
public class FileLoader
{//test

    /**
     * Reads first line in file with associated path specified.
     *
     * @param path the path of the file.
     * @return the read string of the file.
     * @throws IOException if there is an "out" exception.
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
     * @throws IOException if there is an "out" exception.
     */
    public static String readFile(File file) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(file.getPath()));
        return br.readLine();
    }

    /**
     * Reads each line in a text file
     * @param path of the file
     * @return all lines of the text file (in form of array list), separated into different indexes in the array list
     * @throws IOException traced back to initialization method in Mark Core
     */
    public static ArrayList<String> readAllLinesFromFile(String path) throws IOException
    {
        BufferedReader br = new BufferedReader(new FileReader(path));

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
     * @throws IOException if there is an "in" exception.
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
     * @throws IOException if there is an "in" exception
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

}