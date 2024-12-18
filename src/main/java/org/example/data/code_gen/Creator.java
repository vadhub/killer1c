package org.example.data.code_gen;

import org.example.data.Context;
import org.example.data.LayoutInflator;
import org.example.data.build_project.Builder;
import org.example.data.file_handler.SaveFile;

import java.io.File;
import java.io.IOException;

public class Creator {

    Analyser analyser;
    LayoutInflator layoutInflator;

    public Creator(Analyser analyser, LayoutInflator layoutInflator) {
        this.analyser = analyser;
        this.layoutInflator = layoutInflator;
    }

    public void createSrc(File code) throws IOException {
        File src = Builder.createSrc(Context.currentProject);
        String newCode = analyser.findTextMain(code, layoutInflator);
        SaveFile.saveFile(src.getAbsolutePath(), "Code.java", newCode);
    }
}
