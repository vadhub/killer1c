package org.example.model.menu;

import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root
public class Menu {
    @ElementList(inline = true, entry = "MenuItem", type = MenuItem.class, required = false)
    public List<? extends MenuItem> items;
}
