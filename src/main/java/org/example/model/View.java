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
}
