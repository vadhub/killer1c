package org.example.model;

import org.simpleframework.xml.*;

import java.awt.*;
import java.util.List;

@Root
public class FrameContainer extends ViewGroup {

    /*
     * top, bottom, center, left, right
     * */
    @Attribute(required = false)
    public String position;

    @Attribute(required = false)
    public String name;

    public FrameContainer(String name, List<ViewGroup> views, String width, String height) {
        super(views);
        this.name = name;
        this.width = width;
        this.height = height;
    }

    public FrameContainer() {
    }

    public String getPosition() {
        return switch (position) {
            case "top" -> BorderLayout.NORTH;
            case "bottom" -> BorderLayout.SOUTH;
            case "left" -> BorderLayout.WEST;
            case "right" -> BorderLayout.EAST;
            case "center" -> BorderLayout.CENTER;
            default -> "";
        };
    }

    @Override
    public String toString() {
        return "RootContainer{" +
                "views=" + views +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                '}';
    }
}
