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

    @Override
    public String toString() {
        return "RootContainer{" +
                "views=" + views +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
