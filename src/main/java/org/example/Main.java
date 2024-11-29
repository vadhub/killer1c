package org.example;

import jsyntaxpane.syntaxkits.XmlSyntaxKit;
import org.example.data.LayoutInflator;
import org.example.data.OpenFile;
import org.example.data.SaveFile;
import org.example.model.RunPythonFromJava;
import org.example.ui.LineNumberPanel;
import org.example.ui.Menu;
import org.example.ui.ProjectTree2;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws Exception {

        SaveFile saveFile = new SaveFile("/home/vadim/test____", "config.xml");
        OpenFile openFile = new OpenFile();
        Local local = new Local();
        Menu menu = new Menu();
        JFrame frame = new JFrame(local.getText("start"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        JFrame frameMain = new JFrame(local.getText("start"));
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setExtendedState(frameMain.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        JEditorPane jEditorPane = new JEditorPane();
        LayoutInflator inflator = new LayoutInflator();
        ActionListener run = actionEvent -> {
            try {
                RunPythonFromJava.run(jEditorPane.getText());
                //inflator.inflate(jEditorPane.getText());
                System.out.println("Run KILL 1c");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        ActionListener destroy = actionEvent -> {
            inflator.destroyFrame();
            System.out.println("Destroy KILL 1c");
        };

        menu.createMenu(frameMain, run, destroy, openFile, saveFile);
        frameMain.add(new ProjectTree2().projectTree2Create("/home/vadim/test____", jEditorPane::setText), BorderLayout.WEST);
        JPanel buttonsPanel = new JPanel();
        JPanel code = new JPanel(new BorderLayout());
        JPanel console = new JPanel();

        JButton createProject = new JButton(local.getText("create_project"));

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

        JOptionPane dialog = new JOptionPane(local.getText("app_name"));
        createProject.addActionListener(actionEvent -> {
            dialog.createDialog(local.getText("dialog")).setVisible(true);
//            frame.dispose();
        });

        frameMain.add(code, BorderLayout.CENTER);
        frameMain.setVisible(true);

        buttonsPanel.add(createProject);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
//        frame.setVisible(true);

    }


}