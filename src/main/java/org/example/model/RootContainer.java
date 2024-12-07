package org.example.model;

import org.simpleframework.xml.*;

import java.util.List;

@Root
public class RootContainer {

    @ElementListUnion({
            @ElementList(inline = true, entry = "TextView", type = TextView.class, required = false),
            @ElementList(inline = true, entry = "Button", type = Button.class, required = false)
    })
    public List<View> views;

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    public RootContainer(List<View> views, String width, String height) {
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
