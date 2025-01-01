package org.example.data.code_gen;

import org.example.data.LayoutInflater;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analyser {

    Generator generator;

    public Analyser(Generator generator) {
        this.generator = generator;
    }

    public String createNewCode(File code, LayoutInflater layoutInflator) throws IOException {
        StringBuilder sb = new StringBuilder();
        Files.readAllLines(code.toPath()).forEach(it -> sb.append(replaceElementOnTextById(it, layoutInflator)).append(' '));
        return sb.toString();
    }

    public String getId(String text) {
        Matcher matcher = Pattern.compile("getTextByElementId\\('([^']+)'\\)").matcher(text);
        return matcher.find() ? matcher.group(1) : "";
    }

    public String replaceElementOnTextById(String input, LayoutInflater layoutInflator) {
        String text = layoutInflator.getElementById(getId(input.trim())).text;
        return ReplaceText.replaceElementById(input, text);
    }

    public String getTextFromPrint(String str) {
        Pattern pattern = Pattern.compile("print\\([^)]+\\)");
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            return matcher.group(1);
        }

        return "";
    }

    public String generateNewCode(String code) {

        return "";
    }
}
