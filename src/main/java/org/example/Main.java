package org.example;

import org.example.api.Inflater;
import org.example.data.LayoutInflater;
import org.example.data.file_handler.ReadFile;

public class Main {
    public static void main(String[] args) throws Exception {
        Inflater inflater = new LayoutInflater();
        try {
            inflater.inflate(ReadFile.read("values/codetest.xml"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}