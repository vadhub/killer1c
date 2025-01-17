package org.example.data.code_gen;

import java.io.File;

public class Creator {

    public File createGenerated(String pathToProject) {
        File srcDir = new File(pathToProject+File.separator+"generate");
        if (!srcDir.exists()) {
            srcDir.mkdirs();
        }
        return srcDir;
    }

    public File createView() {
        File viewDir = new File("view");
        if (!viewDir.exists()) {
            viewDir.mkdirs();
        }
        return viewDir;
    }

    public File createManifest() {
        File manifestView = new File("manifest");
        if (!manifestView.exists()) {
            manifestView.mkdirs();
        }
        return manifestView;
    }
}
