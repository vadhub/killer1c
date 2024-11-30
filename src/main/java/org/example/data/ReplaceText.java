package org.example.data;

public class ReplaceText {
    public static String replaceGetElementById(String input, String replacement) {
        // Исходная строка
//        String input = "x = get_element_by_id('tv1')";

        // Находим нужный текст с помощью регулярного выражения
        String regex = "get_element_by_id\\('([^']+)'\\)";

        // Заменяем текст
        String output = input.replaceAll(regex, replacement);

        // Выводим результат
        System.out.println("Результат: " + output);
        return output;
    }
}
