package org.example.data;

import org.example.model.config.Config;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;

import java.io.*;

public class Configurator {

    private final Writer writer;
    private final Serializer serializer;

    public Configurator() {
        writer = new StringWriter();
        serializer = new Persister();
    }

    public void createConfig() throws Exception {
        String path = System.getProperty("user.home") + File.separator + "killer1c";
        new File(path).mkdir();
        Context.currentRootDirectory = path;
        Config config = new Config(path, "");
        serializer.write(config, writer);
        SaveFile.saveFile(path, "config", writer.toString());
    }

    public void readConfig() throws Exception {
        String content = ReadFile.read(System.getProperty("user.home") + File.separator + "killer1c" + File.separator + "config");
        Reader reader = new StringReader(content);
        Configurator config = serializer.read(Configurator.class, reader, false);
    }

    public void createProject(String nameProject) {

    }
}
