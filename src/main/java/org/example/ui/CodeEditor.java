package org.example.ui;

import jsyntaxpane.DefaultSyntaxKit;
import jsyntaxpane.lexers.EmptyLexer;
import jsyntaxpane.syntaxkits.CppSyntaxKit;
import jsyntaxpane.syntaxkits.JavaSyntaxKit;
import jsyntaxpane.syntaxkits.PythonSyntaxKit;
import jsyntaxpane.syntaxkits.XmlSyntaxKit;
import org.example.data.file_handler.FileExtension;
import org.example.data.file_handler.SaveFile;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CodeEditor {

    private final SaveFile saveFile;
    private final JTabbedPane tabbedPane;
    private final List<JEditorPane> jEditorPanes;
    private final List<String> filepath;

    public CodeEditor(SaveFile saveFile) {
        this.saveFile = saveFile;
        tabbedPane = new JTabbedPane();
        filepath = new ArrayList<>();
        jEditorPanes = new ArrayList<>();
    }

    public JPanel createPanel() {
        JPanel code = new JPanel(new BorderLayout());
        code.add(tabbedPane, BorderLayout.CENTER);
        return code;
    }

    public void addTabIfNotOpen(String pathfile, String fileName) {
        int index = filepath.indexOf(fileName);
        if (index != -1) {
            tabbedPane.setSelectedIndex(index);
        } else {
            addTab(pathfile, fileName);
            saveFile.setFilepath(pathfile);
        }
    }


    private void addTab(String pathfile, String filename) {
        JEditorPane jEditorPane = createEditorPane(pathfile);
        jEditorPanes.add(jEditorPane);
        tabbedPane.addTab(filename, createScrollPane(jEditorPane));
        filepath.add(pathfile);
        tabbedPane.setSelectedIndex(tabbedPane.getTabCount() - 1);
    }

    private JScrollPane createScrollPane(JEditorPane jEditorPane) {
        LineNumberPanel lineNumberPanel = new LineNumberPanel(jEditorPane);
        JScrollPane jScrollPane = new JScrollPane(jEditorPane);
        jScrollPane.setRowHeaderView(lineNumberPanel);
        jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        return jScrollPane;
    }

    private JEditorPane createEditorPane(String filename) {
        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setEditorKit(getSyntaxKit(filename));
        jEditorPane.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {saveText();}

            @Override
            public void removeUpdate(DocumentEvent e) {saveText();}

            @Override
            public void changedUpdate(DocumentEvent e) {}
        });
        return jEditorPane;
    }

    private void saveText() {
        saveFile.setContent(getText());
        saveFile.setFilepath(filepath.get(tabbedPane.getSelectedIndex()));
        saveFile.saveWithoutDirectory();
    }

    public void setText(String text) {
        int selectedIndex = tabbedPane.getSelectedIndex();
        if (selectedIndex != -1) {
            jEditorPanes.get(selectedIndex).setText(text);
        }
    }

    public String getText() {
        int selectedIndex = tabbedPane.getSelectedIndex();
        return selectedIndex != -1 ? jEditorPanes.get(selectedIndex).getText() : "";
    }

    public DefaultSyntaxKit getSyntaxKit(String file) {
        return switch (FileExtension.extension(file)) {
            case "xml" -> new XmlSyntaxKit();
            case "java" -> new JavaSyntaxKit();
            case "py" -> new PythonSyntaxKit();
            case "cpp" -> new CppSyntaxKit();
            default -> new DefaultSyntaxKit(new EmptyLexer());
        };
    }
}
