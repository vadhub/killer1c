package org.example.data.file_handler;

public class FileExtension {
    public static String extension(String fileName) {

        int dotIndex = fileName.lastIndexOf('.');
        String extension = "";

        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1);
        }

        return extension;
    }
}