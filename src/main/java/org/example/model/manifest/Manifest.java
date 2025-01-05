package org.example.model.manifest;

import org.example.model.topbar.Topbar;
import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root
public class Manifest {

    public static String TABS = "tabs";

    @Attribute(required = false)
    public String name;

    @Attribute(required = false)
    public String width;

    @Attribute(required = false)
    public String height;

    @Element(name = "Topbar", required = false)
    public Topbar topbar;
}