package org.example.data.file_handler;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class SaveFile {

    private String filepath;
    private String directory;
    private String content;

    public SaveFile(String directory, String filepath) {
        this.directory = directory;
        this.filepath = filepath;

        new File(directory).mkdir();
    }

    public SaveFile(String directory) {
        this.directory = directory;
    }

    public void saveFile() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(directory + File.separator + filepath), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveWithoutDirectory() {
        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(filepath), StandardCharsets.UTF_8))) {
            System.out.println(filepath + " save");
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveFile(String directory, String filename, String content) {
        // Создание директории, включая все родительские директории, если они не существуют
        File dir = new File(directory);
        if (!dir.exists()) {
            boolean created = dir.mkdirs();
            if (!created) {
                throw new RuntimeException("Не удалось создать директорию: " + directory);
            }
        }

        // Формирование полного пути к файлу
        File file = Paths.get(directory, filename).toFile();

        // Запись содержимого в файл
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file, false), StandardCharsets.UTF_8))) {
            writer.write(content);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при записи в файл: " + file.getAbsolutePath(), e);
        }

    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getDirectory() {
        return directory;
    }

    public void setDirectory(String directory) {
        this.directory = directory;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
