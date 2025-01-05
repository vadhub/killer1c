package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

@Root
public class LinearContainer extends ViewGroup {

    public static String ORIENTATION_VERTICAL = "vertical";
    public static String ORIENTATION_HORIZONTAL = "horizontal";
    /*
    * orientation: vertical, horizontal
    * */
    @Attribute(required = false)
    public String orientation = ORIENTATION_HORIZONTAL;

    @Attribute(required = false)
    public String name;

    public LinearContainer() {
    }
}
