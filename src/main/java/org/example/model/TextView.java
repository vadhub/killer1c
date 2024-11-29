package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class TextView extends View {

    /**
     * number
     * date
     * text
     * phone
     * ...
     * **/
    @Attribute
    public String type;
}