package org.example.data.build_project;

import org.example.data.code_gen.Generator;
import org.example.data.file_handler.SaveFile;

import java.io.File;
import java.io.IOException;

public class Builder {
    public static void main(String[] args) {
        String res = Generator.build();

        // Создание необходимых директорий
        File projectDir = new File("/home/vadim/test____/Test");
        File srcDir = new File(projectDir, "src");
        File outDir = new File(projectDir, "out");

        // Проверка и создание директорий
        if (!projectDir.exists()) {
            projectDir.mkdirs();
        }
        if (!srcDir.exists()) {
            srcDir.mkdirs();
        }
        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        // Сохранение файла Run.java
        SaveFile.saveFile(srcDir.getAbsolutePath(), "Run.java", res);

        // Создание manifest.txt
        String manifest = "Manifest-Version: 1.0\nMain-Class: Run\n";
        SaveFile.saveFile(projectDir.getAbsolutePath(), "manifest.txt", manifest);

        // Компиляция Java файлов
        ProcessBuilder processBuilder = new ProcessBuilder();
        processBuilder.command("javac", "-d", outDir.getAbsolutePath(), srcDir.getAbsolutePath() + "/Run.java");

        processBuilder.directory(new File("."));

        try {
            Process process = processBuilder.start();
            int exitCode = process.waitFor();
            System.out.println("Компиляция завершена с кодом: " + exitCode);

            // Создание JAR файла
            ProcessBuilder jarBuilder = new ProcessBuilder();
            jarBuilder.command("jar", "cfm", "MyProject.jar", "manifest.txt", "-C", outDir.getAbsolutePath(), ".");
            jarBuilder.directory(projectDir); // Устанавливаем рабочую директорию

            Process jarProcess = jarBuilder.start();
            int jarExitCode = jarProcess.waitFor();
            System.out.println("Создание JAR завершено с кодом: " + jarExitCode);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }

    public static File createSrc(String projectDir) {
        File srcDir = new File(projectDir, "src");
        if (!srcDir.exists()) {
            srcDir.mkdirs();
        }
        System.out.println("src created: " + srcDir);
        return srcDir;
    }
}
