package org.example.data;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveFile {

    private String filepath;
    private String directory;
    private String content;

    public SaveFile(String directory, String filepath) {
        this.directory = directory;
        this.filepath = filepath;

        new File(directory).mkdir();
    }

    public void saveFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(directory + File.separator + filepath), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFile(String directory, String filename, String content) {
        new File(directory).mkdir();

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(directory + File.separator + filename, false), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
