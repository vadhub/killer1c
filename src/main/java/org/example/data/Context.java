package org.example.data;

public class Context {
    public static String currentFile = "";
    public static String currentProject = "";
    public static String currentRootDirectory = "";
    public static String viewsFolder = "views";
    public static String codesFolder = "codes";

    public static String output() {
        return currentProject + " " + currentRootDirectory;
    }
}
