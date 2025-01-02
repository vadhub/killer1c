package org.example.model;

import org.simpleframework.xml.*;

import java.util.List;

@Root
public class RootContainer {

    @Attribute(required = false)
    public String name;

    @ElementListUnion({
            @ElementList(inline = true, entry = "TextView", type = TextView.class, required = false),
            @ElementList(inline = true, entry = "Button", type = Button.class, required = false),
            @ElementList(inline = true, entry = "Table", type = Table.class, required = false)
    })
    public List<View> views;

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    public RootContainer(String name, List<View> views, String width, String height) {
        this.name = name;
        this.views = views;
        this.width = width;
        this.height = height;
    }

    public RootContainer() {
    }

    @Override
    public String toString() {
        return "RootContainer{" +
                "views=" + views +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
