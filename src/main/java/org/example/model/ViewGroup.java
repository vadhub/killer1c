package org.example.model;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.ElementListUnion;

import java.util.List;

public class ViewGroup extends View {

    @ElementListUnion({
            @ElementList(inline = true, entry = "FrameContainer", type = FrameContainer.class, required = false),
            @ElementList(inline = true, entry = "Button", type = Button.class, required = false),
            @ElementList(inline = true, entry = "TextView", type = TextView.class, required = false),
            @ElementList(inline = true, entry = "Table", type = Table.class, required = false)
    })
    public List<ViewGroup> views;

    public ViewGroup(List<ViewGroup> views) {
        this.views = views;
    }

    public ViewGroup(String id, String width) {
        super(id, width);
    }

    public ViewGroup(String id) {
        super(id);
    }

    public ViewGroup() {
    }
}
