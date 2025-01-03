package org.example.model;

import javax.swing.*;

public class Button extends Label {

    private JButton jButton;

    public Button(String id, String text) {
        super(id, text);
    }

    public JButton createButton() {
        System.out.println("create button");
        jButton = new JButton(text);
        return jButton;
    }

    @Override
    public void setOnClickListener(Action toDo) {
        jButton.addActionListener(actionEvent -> toDo.invoke());
    }

    public Button() {
    }
}
