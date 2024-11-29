package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class LazyList extends Container {

    @Attribute(required = false)
    public String orientation;

    @Attribute(required = false)
    public String repeat;
}
