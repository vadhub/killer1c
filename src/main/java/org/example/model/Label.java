package org.example.model;

import org.simpleframework.xml.Root;

@Root
public class Label extends View {
    public Label(String id, String text, String width) {
        super(id, text, width);
    }
}
