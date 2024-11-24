package org.example;

import jsyntaxpane.syntaxkits.XmlSyntaxKit;
import org.example.ui.Menu;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) throws Exception {
        Local local = new Local();
        Menu menu = new Menu();
        JFrame frame = new JFrame(local.getText("start"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        JFrame frameMain = new JFrame(local.getText("start"));
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setExtendedState(frameMain.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        menu.createMenu(frameMain);

        JPanel buttonsPanel = new JPanel();
        JPanel code = new JPanel(new BorderLayout());
        JPanel console = new JPanel();

        JButton stop = new JButton(local.getText("create_project"));

        JEditorPane jEditorPane = new JEditorPane();
        jEditorPane.setEditorKit(new XmlSyntaxKit());
        code.add(jEditorPane, BorderLayout.CENTER);

        JOptionPane dialog = new JOptionPane(local.getText("app_name"));
        stop.addActionListener(actionEvent -> {
            dialog.createDialog(local.getText("dialog")).setVisible(true);
//            frame.dispose();
        });

        frameMain.add(code, BorderLayout.CENTER);
        frameMain.setVisible(true);

        buttonsPanel.add(stop);
        frame.add(buttonsPanel, BorderLayout.SOUTH);
//        frame.setVisible(true);
    }
}