package org.example.data.code_gen;

import javax.swing.*;
public class Run {
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setTitle("My Swing Application");

        frame.setSize(500,500); frame.setLocationRelativeTo(null);JPanel panel = new JPanel();
        JTextField tv1 = new JTextField();
        tv1.setText("hello");
        panel.add(tv1);
        JTextField tv2 = new JTextField();
        tv2.setText("world");
        panel.add(tv2);
        JButton btn = new JButton("concat");
        panel.add(btn);
        frame.add(panel);
        frame.setVisible(true);
    }
}
