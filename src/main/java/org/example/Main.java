package org.example;

import org.example.api.Inflater;
import org.example.data.LayoutInflater;
import org.example.data.file_handler.ReadFile;
import org.example.model.TextView;

public class Main {
    public static void main(String[] args) {
        Inflater inflater = new LayoutInflater();
        try {
            inflater.inflate(ReadFile.read("values/codetest.xml"), "src/main/java/org/example");
            TextView tv1 = (TextView) inflater.getElementById("tv1");
            TextView tv2 = (TextView) inflater.getElementById("tv2");
            TextView result = (TextView) inflater.getElementById("result");

            inflater.getElementById("btn").setOnClickListener(() -> result.setText(tv1.getText() + tv2.getText()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}