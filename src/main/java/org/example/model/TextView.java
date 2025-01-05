package org.example.model;

import org.simpleframework.xml.Attribute;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TextView extends Label {

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
        System.out.println("create text view");
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
    public String getText() {
        return jTextField.getText();
    }

    public TextView(String id, String text, String width, String type) {
        super(id, text, width);
        this.type = type;
    }

    public TextView() {
    }
}