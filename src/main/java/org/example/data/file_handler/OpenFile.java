package org.example.data.file_handler;

import org.example.ui.FileChooser;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OpenFile implements ActionListener {

    private final FileChooser fileChooser;

    public OpenFile() {
        fileChooser = new FileChooser();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        fileChooser.openFileChooser();
    }
}
