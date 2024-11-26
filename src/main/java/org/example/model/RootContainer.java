package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementUnion;
import org.simpleframework.xml.Root;

@Root
public class RootContainer {
    @ElementUnion(
            {@Element(name = "LazyList", type = LazyList.class, required = false),
            @Element(name = "FrameContainer", type = FrameContainer.class, required = false)}
    )
    public Container container;

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    @Override
    public String toString() {
        return "RootContainer{" +
                "container=" + container +
                '}';
    }
}
