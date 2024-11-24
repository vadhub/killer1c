package org.example.data;

import org.example.model.Button;
import org.example.model.FrameLayout;
import org.example.model.TextView;
import org.example.model.View;
import org.simpleframework.xml.core.Persister;

import javax.swing.*;
import java.awt.*;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class LayoutInflator {

    public void inflate(String xml) throws Exception {
        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();
        FrameLayout frameLayout = serializer.read(FrameLayout.class, reader, false);
        System.out.println(frameLayout);
        createWindow(frameLayout);
    }

    public void createWindow(FrameLayout frameLayout) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        if (!frameLayout.width.isEmpty() && !frameLayout.height.isEmpty()) {
            if (frameLayout.height.equals("match_parent") && frameLayout.width.equals("match_parent")) {
                System.out.println("ok");
                frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
            } else {
                frame.setSize(Integer.parseInt(frameLayout.width), Integer.parseInt(frameLayout.height));
                frame.setLocationRelativeTo(null);
            }
        }

        frame.add(createViews(frameLayout.views), BorderLayout.NORTH);
        frame.setVisible(true);
    }

    public JButton createButton(Button button) {
        JButton jButton = new JButton(button.text);
        return jButton;
    }

    public JTextField createTextView(TextView textView) {
        JTextField jTextField = null;
        if (textView.width != null) {
            if (!textView.width.isEmpty()) {
                jTextField = new JTextField(Integer.parseInt(textView.width));
            }
        } else {
            jTextField = new JTextField();
        }
        jTextField.setText(textView.text);
        return jTextField;
    }

    public JPanel createPanel() {
        JPanel jPanel = new JPanel();
        return jPanel;
    }

    public JPanel createViews(List<View> views) {
        JPanel jPanel = createPanel();
        views.forEach(it -> {
            if (it instanceof Button) {
                System.out.println("create button");
                jPanel.add(createButton((Button) it));
            } else if (it instanceof TextView) {
                System.out.println("create  text view");
                jPanel.add(createTextView((TextView) it));
            }
        });

        return jPanel;
    }
}
