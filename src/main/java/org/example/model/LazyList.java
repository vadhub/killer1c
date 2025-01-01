package org.example.model;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

import javax.swing.*;

@Root
public class LazyList extends Container {

    @Attribute(required = false)
    public String orientation;

    @Attribute(required = false)
    public String repeat;

    public JPanel createList(Action toDo) {
        JPanel jPanel = new JPanel();
        int repeat = Integer.parseInt(this.repeat);

        for (int i = 0; i <= repeat; i++) {
            toDo.invoke();
        }
        return jPanel;
    }
}
