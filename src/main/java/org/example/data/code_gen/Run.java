package org.example.data.code_gen;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;import javax.swing.*;
public class Run {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JTextField tv1 = new JTextField();

        JPanel panel = new JPanel();

        panel.add(tv1);

        JButton button = new JButton("Click Me");

        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Button clicked!");
            }
        });

        panel.add(button);

        frame.setSize(400, 300);

        frame.setTitle("My Swing Application");

        frame.add(panel);

        frame.setVisible(true);

    }
}