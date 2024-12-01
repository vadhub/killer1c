package org.example.ui;

import jsyntaxpane.syntaxkits.XmlSyntaxKit;
import org.example.data.file_handler.SaveFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class CodeEditor {

    private final JEditorPane jEditorPane;
    private final SaveFile saveFile;

    public CodeEditor(SaveFile saveFile) {
        this.saveFile = saveFile;
        jEditorPane = new JEditorPane();
    }

    public JPanel createPanel() {
        JPanel code = new JPanel(new BorderLayout());
        LineNumberPanel lineNumberPanel = new LineNumberPanel(jEditorPane);
        JScrollPane jScrollPane = new JScrollPane(jEditorPane);
        jScrollPane.setRowHeaderView(lineNumberPanel);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jEditorPane.setEditorKit(new XmlSyntaxKit());
        jEditorPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                lineNumberPanel.repaint();
                saveFile.setContent(jEditorPane.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                lineNumberPanel.repaint();
                saveFile.setContent(jEditorPane.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                lineNumberPanel.repaint();
            }
        });

        code.add(jScrollPane, BorderLayout.CENTER);
        return code;
    }

    public String getText() {
        return jEditorPane.getText();
    }

    public void setText(String text) {
        jEditorPane.setText(text);
    }
}
