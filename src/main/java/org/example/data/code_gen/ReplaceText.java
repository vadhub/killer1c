package org.example.data.code_gen;

import org.example.data.Context;

import java.io.*;
import java.util.ArrayList;

public class ReplaceText {

    public static String replaceElementById(String input, String replacement) {

        String regex = "getTextByElementId\\('([^']+)'\\)";
        String output = input.replaceAll(regex, replacement);

        System.out.println("Результат: " + output);
        return output;
    }

    public static void replaceEnd(String filePath, String textToAdd) throws IOException {

        ArrayList<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Удалить последние 3 строки
        if (lines.size() >= 3) {
            lines.remove(lines.size() - 1);
            lines.remove(lines.size() - 1);
            lines.remove(lines.size() - 1);
        }

        // Добавить новые строки
        lines.add(textToAdd+"}\n}");

        // Записать список строк обратно в файл
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        }
    }
}
