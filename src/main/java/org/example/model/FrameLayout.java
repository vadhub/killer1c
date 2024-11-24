package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class FrameLayout {
    @ElementListUnion({
            @ElementList(inline = true, entry = "Button", type = Button.class, required = false),
            @ElementList(inline = true, entry = "TextView", type = TextView.class, required = false)
    })
    public List<View> views;

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    @Override
    public String toString() {
        return "FrameLayout{" +
                "views=" + views +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
