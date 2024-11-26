package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class Container {

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    @ElementListUnion({
            @ElementList(inline = true, entry = "Button", type = Button.class, required = false),
            @ElementList(inline = true, entry = "TextView", type = TextView.class, required = false)
    })
    public List<View> views;

    @ElementListUnion({
            @ElementList(inline = true, entry = "LazyList", type = LazyList.class, required = false),
            @ElementList(inline = true, entry = "FrameContainer", type = FrameContainer.class, required = false)
    })
    public List<Container> containers;

    @Override
    public String toString() {
        return "Container{" +
                "width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", views=" + views +
                ", containers=" + containers +
                '}';
    }
}
