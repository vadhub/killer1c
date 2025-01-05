package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import javax.swing.*;

@Root
public class Label extends ViewGroup {

    public JLabel label;

    @Attribute(required = false)
    public String text;

    public Label(String id, String text, String width) {
        super(id, width);
        this.text = text;
    }

    public Label(String id, String text) {
        super(id);
        this.text = text;
    }

    public Label() {
    }

    public JLabel createLabel() {
        System.out.println("create label");
        if (text == null) {
            text = "";
        }
        label = new JLabel(text);
        return label;
    }

    public Label(String text) {
        this.text = text;
    }
}
