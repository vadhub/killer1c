package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import javax.swing.*;

@Root
public class TextView extends View {

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
        JTextField jTextField = null;
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
}