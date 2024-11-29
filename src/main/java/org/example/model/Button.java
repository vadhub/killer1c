package org.example.model;

import org.simpleframework.xml.Root;

import javax.swing.*;

@Root
public class Button extends View {
    public JButton createButton() {
        JButton jButton = new JButton(text);
        return jButton;
    }
}
