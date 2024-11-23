package org.example;

import javax.swing.*;
import java.awt.*;

import static org.example.Local.getLang;

public class Main {
    public static void main(String[] args) throws Exception {
        Local local = new Local();
        JFrame frame = new JFrame(local.getText("start"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(null);

        JPanel buttonsPanel = new JPanel();
        JPanel textPanel = new JPanel();

        JButton stop = new JButton(local.getText("create_project"));

        JTextField project = new JTextField(10);
        project.setText(getLang());
        textPanel.add(project);

        JOptionPane dialog = new JOptionPane(local.getText("app_name"));
        stop.addActionListener(actionEvent -> dialog.createDialog(local.getText("dialog")).setVisible(true));
        buttonsPanel.add(stop);
        frame.add(textPanel, BorderLayout.CENTER);
        frame.add(BorderLayout.SOUTH, buttonsPanel);
        frame.setVisible(true);
    }
}