package org.example.model;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.simpleframework.xml.Root;

import javax.lang.model.element.Modifier;
import javax.swing.*;
import java.io.File;

@Root
public class Button extends View {

    public Button(String id, String text) {
        super(id, text);
    }

    public JButton createButton() {
        JButton jButton = new JButton(text);
        return jButton;
    }

    public Pair<File, TypeSpec> createClass() {
        File javaClass = new File("TextView_" + id);
        TypeSpec view = TypeSpec.classBuilder(javaClass.getName())
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(String.class, "id", Modifier.PUBLIC).initializer('"'+checkNotNull(id)+'"').build())
                .addField(FieldSpec.builder(String.class, "text", Modifier.PUBLIC).initializer('"'+checkNotNull(text)+'"').build())
                .addField(FieldSpec.builder(String.class, "height", Modifier.PUBLIC).initializer('"'+checkNotNull(height)+'"').build())
                .addField(FieldSpec.builder(String.class, "width", Modifier.PUBLIC).initializer('"'+checkNotNull(width)+'"').build())
                .build();
        return new Pair<>(javaClass, view);
    }

    public Button() {
    }
}
