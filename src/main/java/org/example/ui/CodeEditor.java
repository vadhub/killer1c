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
    private JTabbedPane tabbedPane;

    public CodeEditor(SaveFile saveFile) {
        this.saveFile = saveFile;
        jEditorPane = new JEditorPane();
        tabbedPane = new JTabbedPane();
    }

    public JPanel createPanel() {
        JPanel code = new JPanel(new BorderLayout());

        code.add(createEditorPane(), BorderLayout.CENTER);
        return code;
    }

    private void addTab(String fileName) {
        // Создание текстовой области для вкладки
        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        // Добавление вкладки с текстовой областью
        tabbedPane.addTab(fileName, scrollPane);
    }

    private JScrollPane createEditorPane() {
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
        return jScrollPane;
    }

    public String getText() {
        return jEditorPane.getText();
    }

    public void setText(String text) {
        jEditorPane.setText(text);
    }
}
