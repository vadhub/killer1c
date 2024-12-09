package org.example.data.code_gen;

public class ReplaceText {
    public static String replaceElementById(String input, String replacement) {

        String regex = "getTextByElementId\\('([^']+)'\\)";
        String output = input.replaceAll(regex, replacement);

        System.out.println("Результат: " + output);
        return output;
    }
}
