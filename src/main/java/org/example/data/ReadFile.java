package org.example.data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ReadFile {
    public static String read(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.readAllLines(new File(path).toPath()).forEach(it -> {
            sb.append(it).append('\n');
        });

        return sb.toString();
    }
}
