package org.example.data.code_gen;

import org.example.data.Context;
import org.example.data.LayoutInflator;

import java.io.File;
import java.io.IOException;

public class Creator {

    Analyser analyser;
    LayoutInflator layoutInflator;
    Generator generator;

    public Creator(LayoutInflator layoutInflator, Generator generator) {
        this.layoutInflator = layoutInflator;
        this.generator = generator;
        this.analyser = new Analyser(generator);
    }

    public void createSrc(File code) throws IOException {
//        File src = Builder.createSrc(Context.currentProject);
        String newCode = analyser.createNewCode(code, layoutInflator);
//        SaveFile.saveFile(src.getAbsolutePath(), "Code.java", newCode);
    }
}
