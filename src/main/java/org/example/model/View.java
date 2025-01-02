package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class View {

    @Attribute(required = false)
    public String id;

    @Attribute(required = false)
    public String text;

    @Attribute(required = false)
    public String background;

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    public View(String id, String text, String width) {
        this.id = id;
        this.text = text;
        this.width = width;
    }

    public View(String id, String text) {
        this.id = id;
        this.text = text;
    }

    public View() {
    }

    public static String checkNotNull(String str) {
        if (str != null) {
            return str;
        }
        return "";
    }

    public void setOnClickListener(Action toDo) {
    }
}
