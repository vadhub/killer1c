package org.example.data.code_gen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class RunPythonFromJava implements RunCode {

    public void run(String pythonCode) {

        try {
            ProcessBuilder processBuilder = new ProcessBuilder("python3");
            processBuilder.redirectErrorStream(true); // Объединяем стандартный и поток ошибок

            Process process = processBuilder.start();

            try (OutputStream os = process.getOutputStream()) {
                os.write(pythonCode.getBytes());
                os.flush();
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            int exitCode = process.waitFor();
            System.out.println("Exited with code: " + exitCode);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}