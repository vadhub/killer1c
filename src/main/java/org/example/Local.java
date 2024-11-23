package org.example;

import org.simpleframework.xml.core.Persister;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.util.concurrent.atomic.AtomicReference;

public class Local {

    private final Resources resources;

    public Local() throws Exception {
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        StringBuilder xml = new StringBuilder();
        String fileName = "values/strings_"+getLang()+".xml";
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(classloader.getResourceAsStream(fileName)));
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
        AtomicReference<String> str = new AtomicReference<>();
        resources.strings.forEach(i -> {
            if (i.name.equals(name)) {
                str.set(i.text);
            }
        });

        return str.get();
    }
}
