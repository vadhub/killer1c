package org.example.model.menu;

import org.example.model.View;
import org.simpleframework.xml.Root;

@Root
public class MenuItem extends View {

    public MenuItem(String id, String text) {
        super(id, text);
    }
}
