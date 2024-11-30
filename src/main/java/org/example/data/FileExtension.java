package org.example.data;

public class FileExtension {
    public static void main(String[] args) {
        String fileName = "example.code"; // Укажите имя файла

        // Находим индекс последней точки
        int dotIndex = fileName.lastIndexOf('.');
        String extension = "";

        // Если точка найдена и это не первый символ
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            extension = fileName.substring(dotIndex + 1); // Извлекаем расширение
        }

        // Выводим результат
        System.out.println("Расширение файла: " + extension);
    }
}