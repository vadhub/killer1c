package org.example.data;

import org.example.model.Action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ListenerEvent implements ActionListener {

    private final Action action;

    public ListenerEvent(Action action) {
        this.action = action;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        action.invoke();
    }
}
