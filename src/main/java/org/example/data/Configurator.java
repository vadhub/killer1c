package org.example.data;

import org.example.model.Button;
import org.example.model.RootContainer;
import org.example.model.TextView;
import org.example.model.View;
import org.example.model.config.Config;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Configurator {

    private Writer writer;
    private final Serializer serializer;
    private final String path;

    public Configurator() {
        writer = new StringWriter();
        serializer = new Persister();
        path = System.getProperty("user.home") + File.separator + "killer1c";
        Context.currentRootDirectory = path;
    }

    public void createConfig() throws Exception {
        if (Files.exists(Path.of(path))) {
            readConfig();
        } else {
            new File(path).mkdir();
            Context.currentRootDirectory = path;
            setConfig("");
        }
    }

    public void readConfig() throws Exception {
        String content = ReadFile.read(path + File.separator + "config");
        Reader reader = new StringReader(content);
        Config config = serializer.read(Config.class, reader, false);
        Context.currentProject = config.currentProject;
        Context.currentRootDirectory = config.currentRootDirectory;
    }

    public void createProject(String nameProject) throws Exception {
        new File(path+File.separator+nameProject).mkdir();
        setConfig(nameProject);
        writer = new StringWriter();
        TextView textView = new TextView("tv1", "hello", "10", "text");
        Button button = new Button("btn", "hello");
        RootContainer container = new RootContainer(List.of(textView, button), "400", "500");
        serializer.write(container, writer);
        SaveFile.saveFile(path + File.separator + nameProject, "screen.xml", writer.toString());
        Context.currentProject = nameProject;
    }

    private void setConfig(String project) throws Exception {
        Config config = new Config(path, project);
        serializer.write(config, writer);
        SaveFile.saveFile(path, "config", writer.toString());
    }
}
