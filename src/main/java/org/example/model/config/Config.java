package org.example.model.config;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Config {
    @Attribute(required = false)
    public String currentRootDirectory;

    @Attribute(required = false)
    public String currentProject;


    public Config(String currentRootDirectory, String currentProject) {
        this.currentRootDirectory = currentRootDirectory;
        this.currentProject = currentProject;
    }

    public Config() {
    }
}
