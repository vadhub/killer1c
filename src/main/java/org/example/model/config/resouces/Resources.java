package org.example.model.config.resouces;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "resources")
public class Resources {
    @ElementList(name = "string", inline = true)
    public List<StringElement> strings;
}
