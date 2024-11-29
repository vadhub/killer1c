package org.example.data;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class SaveFile implements ActionListener {

    private String filepath;
    private String directory;
    private String content;

    public SaveFile(String directory, String filepath) {
        this.directory = directory;
        this.filepath = filepath;

        new File(directory).mkdir();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        saveFile();
    }

    public void saveFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(directory + File.separator + filepath), StandardCharsets.UTF_8))) {
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
