package org.example.data.file_handler;

import java.io.*;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReadFile {
    public static String read(String path) throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.readAllLines(new File(path).toPath()).forEach(it -> {
            sb.append(it).append('\n');
        });

        return sb.toString();
    }

    public static String readRoot(Reader reader) {
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            String rootElement = null;

            while ((line = br.readLine()) != null) {
                line = line.trim();
                Pattern pattern = Pattern.compile("<\\s*(\\w+)\\s+");
                Matcher matcher = pattern.matcher(line);

                if (matcher.find()) {
                    rootElement = matcher.group(1);
                    break;
                }
            }

            if (rootElement != null) {
                System.out.println("Корневой элемент: " + rootElement);
                return rootElement;
            } else {
                System.out.println("Не удалось определить корневой элемент.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
