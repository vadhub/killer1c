package org.example.data;

import org.example.model.*;
import org.simpleframework.xml.core.Persister;

import javax.swing.*;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

public class LayoutInflator {

    private JFrame frame;

    public void destroyFrame() {
        frame.dispose();
    }

    public void inflate(String xml) throws Exception {
        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();
        RootContainer container = serializer.read(RootContainer.class, reader, false);
        System.out.println(container);
        frame = createRoot(container);
        frame.add(createViews(container.views));
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

    public JPanel createList(LazyList list) {
        JPanel jPanel = createPanel();
        int repeat = Integer.parseInt(list.repeat);

        for (int i = 0; i <= repeat; i++) {
            jPanel.add(createViews(list.views));
        }

        return jPanel;
    }

    public JPanel createContainers(List<Container> containers) {
        JPanel jPanel = createPanel();
        for (Container container : containers) {
            jPanel.add(createContainer(container));
        }
        return jPanel;
    }

    public JPanel createContainer(Container container) {
        JPanel jPanel = createPanel();

        if (container instanceof LazyList) {
            return createList((LazyList) container);
        } else {
            jPanel.add(createViews(container.views));
        }

        if (container.containers != null) {
            return createContainers(container.containers);
        }

        return jPanel;
    }

    public JFrame createRoot(RootContainer rootContainer) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (rootContainer.width != null && rootContainer.height != null) {
                if ("match_parent".equals(rootContainer.width) && "match_parent".equals(rootContainer.height)) {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } else {
                    frame.setSize(Integer.parseInt(rootContainer.width), Integer.parseInt(rootContainer.height));
                    frame.setLocationRelativeTo(null);
                }
            }
        return frame;
    }
}
