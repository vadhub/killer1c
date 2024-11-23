package org.example;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Text;

@Root(name = "string")
public class StringElement {
    @Attribute(required = false)
    public String name;

    @Text(required = false)
    public String text;
}
