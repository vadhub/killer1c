package org.example.ui;

import javax.swing.*;
import java.io.File;

public class FileChooser {
    public JFileChooser openFileChooser() {
        JFileChooser fileChooser = new JFileChooser();

        // Открываем диалог выбора файла
        int returnValue = fileChooser.showOpenDialog(null);

        // Проверяем, выбрал ли пользователь файл
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            // Выводим путь к выбранному файлу
            System.out.println("Выбранный файл: " + selectedFile.getAbsolutePath());
        } else {
            System.out.println("Выбор файла отменен.");
        }

        return fileChooser;
    }
}
