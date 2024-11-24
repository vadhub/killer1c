package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class TextView extends View {
    @Attribute(required = false)
    public String width;
}