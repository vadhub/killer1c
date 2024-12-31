package org.example;

import org.example.data.LayoutInflator;
import org.example.data.file_handler.ReadFile;

public class Main {
    public static void main(String[] args) throws Exception {
        LayoutInflator inflator = new LayoutInflator();
        try {
            inflator.inflate(ReadFile.read("values/codetest.xml"));
            //creator.createSrc(new File(Context.codesFolder + File.separator + "HelloWorld"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


}