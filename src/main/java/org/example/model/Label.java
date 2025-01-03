package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Label extends ViewGroup {

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


    public Label(String text) {
        this.text = text;
    }
}
