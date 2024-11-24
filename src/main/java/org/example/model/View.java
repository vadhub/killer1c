package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root(name = "view")
public class View {
    @Attribute(required = false)
    public String id;

    @Attribute(required = false)
    public String text;

    @Attribute(required = false)
    public String background;

    @Override
    public String toString() {
        return "View{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
