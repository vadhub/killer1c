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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class CodeEditor {

    private final SaveFile saveFile;
    private final JTabbedPane tabbedPane;
    private final List<JEditorPane> jEditorPanes;
    private final List<String> filenames;

    public CodeEditor(SaveFile saveFile) {
        this.saveFile = saveFile;
        tabbedPane = new JTabbedPane();
        filenames = new ArrayList<>();
        jEditorPanes = new ArrayList<>();
    }

    public JPanel createPanel() {
        JPanel code = new JPanel(new BorderLayout());
        code.add(tabbedPane, BorderLayout.CENTER);
        return code;
    }

    public void addTabIfNotOpen(String fileName) {
        int index = filenames.indexOf(fileName);
        if (index != -1) {
            tabbedPane.setSelectedIndex(index);
        } else {
            addTab(fileName);
            saveFile.setFilepath(fileName);
        }
    }

    private void addTab(String fileName) {
        JEditorPane jEditorPane = createEditorPane(fileName);
        jEditorPanes.add(jEditorPane);
        tabbedPane.addTab(fileName, createScrollPane(jEditorPane));
        filenames.add(fileName);
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
            public void insertUpdate(DocumentEvent e) {
                saveFile.setContent(getText());
                saveFile.setFilepath(filenames.get(tabbedPane.getSelectedIndex()));
                saveFile.saveFile();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                saveFile.setContent(getText());
                saveFile.setFilepath(filenames.get(tabbedPane.getSelectedIndex()));
                saveFile.saveFile();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }
        });
        return jEditorPane;
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
