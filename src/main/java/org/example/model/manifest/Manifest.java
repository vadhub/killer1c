package org.example.model.manifest;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class Manifest {

    @Attribute(required = false)
    public String name;
}