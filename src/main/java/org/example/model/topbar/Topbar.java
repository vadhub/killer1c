package org.example.model.topbar;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class Topbar {
    @ElementList(inline = true, entry = "Item", type = Item.class, required = false)
    public List<Item> bars;

    @Override
    public String toString() {
        return "Topbar{" +
                "bars=" + bars +
                '}';
    }
}
