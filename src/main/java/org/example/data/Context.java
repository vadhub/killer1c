package org.example.data;

public class Context {
    public static String currentFile = "";
    public static String currentProject = "";
    public static String currentRootDirectory = "";

    public static String output() {
        return currentProject + " " + currentRootDirectory;
    }
}