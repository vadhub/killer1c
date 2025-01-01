package org.example.model;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import javax.lang.model.element.Modifier;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

@Root
public class TextView extends View {

    private JTextField jTextField;
    /**
     * number
     * date
     * text
     * phone
     * ...
     * **/
    @Attribute(required = false)
    public String type;

    public JTextField createTextView() {
        if (width != null) {
            if (!width.isEmpty()) {
                jTextField = new JTextField(Integer.parseInt(width));
            }
        } else {
            jTextField = new JTextField();
        }
        jTextField.setText(text);
        return jTextField;
    }

    @Override
    public void setOnClickListener(Action toDo) {
        jTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                toDo.invoke();
            }
        });
    }

    public void setText(String text) {
        jTextField.setText(text);
    }

    public Pair<File, TypeSpec> createClass() {
        File javaClass = new File("TextView_" + id);
        TypeSpec view = TypeSpec.classBuilder(javaClass.getName())
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(String.class, "id", Modifier.PUBLIC).initializer('"'+checkNotNull(id)+'"').build())
                .addField(FieldSpec.builder(String.class, "text", Modifier.PUBLIC).initializer('"'+checkNotNull(text)+'"').build())
                .addField(FieldSpec.builder(String.class, "height", Modifier.PUBLIC).initializer('"'+checkNotNull(height)+'"').build())
                .addField(FieldSpec.builder(String.class, "width", Modifier.PUBLIC).initializer('"'+checkNotNull(width)+'"').build())
                .addField(FieldSpec.builder(String.class, "type", Modifier.PUBLIC).initializer('"'+checkNotNull(type)+'"').build())
                .build();
        return new Pair<>(javaClass, view);
    }

    public TextView(String id, String text, String width, String type) {
        super(id, text, width);
        this.type = type;
    }

    public TextView() {
    }
}