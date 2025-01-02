package org.example.model;

import org.simpleframework.xml.Attribute;

import javax.swing.*;

public class Cell {

    @Attribute(required = false)
    Icon icon;
    @Attribute(required = false)
    String text;

    public Cell(Icon icon, String text) {
        this.icon = icon;
        this.text = text;
    }

    public Cell(String text) {
        this.text = text;
    }

}
