package org.example.model;

import org.simpleframework.xml.Root;

import javax.swing.*;

@Root
public class Button extends View {

    public Button(String id, String text) {
        super(id, text);
    }

    public JButton createButton() {
        JButton jButton = new JButton(text);
        return jButton;
    }
}
