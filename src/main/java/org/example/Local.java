package org.example;

import org.example.model.config.resouces.Resources;
import org.simpleframework.xml.core.Persister;

import java.io.*;

public class Local {

    private final Resources resources;

    public Local() throws Exception {
        StringBuilder xml = new StringBuilder();
        String fileName = "values/strings_" +getLang()+".xml";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            xml.append(line);
        }
        Reader reader = new StringReader(xml.toString());
        Persister serializer = new Persister();
        resources = serializer.read(Resources.class, reader, false);
    }

    public static String getLang() {
        return System.getProperty("user.language");
    }

    public String getText(String name) {
       return resources.strings.stream()
                .filter(i -> i.name.equals(name))
                .map(i -> i.text)
                .findFirst()
                .orElse(null);
    }
}
