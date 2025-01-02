package org.example.data;

import org.example.api.Inflater;
import org.example.model.*;
import org.simpleframework.xml.core.Persister;

import javax.swing.*;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

public class LayoutInflater implements Inflater {

    private JFrame frame;
    private final HashMap<String, View> components = new HashMap<>();

    public void destroyWindow() {
        frame.dispose();
    }

    public void inflate(String xml, String pathProject) throws Exception {
        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();
        RootContainer container = serializer.read(RootContainer.class, reader, false);
        frame = createRoot(container);
        frame.add(createViews(container.views));
        frame.setVisible(true);
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
                JButton component = ((Button) it).createButton();
                jPanel.add(component);
                components.put(it.id, it);
            } else if (it instanceof TextView) {
                System.out.println("create text view");
                JTextField component = ((TextView) it).createTextView();
                jPanel.add(component);
                components.put(it.id, it);
            } else if (it instanceof Table) {
                System.out.println("create table");
                JTable component = ((Table) it).createTable();
                jPanel.add(new JScrollPane(component));
                components.put(it.id, it);
            }
        });
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
            return ((LazyList) container).createList(() -> {});
        } else {
            jPanel.add(createViews(container.views));
        }

        if (container.containers != null) {
            return createContainers(container.containers);
        }

        return jPanel;
    }

    public JFrame createRoot(RootContainer rootContainer) {
        JFrame frame = new JFrame(rootContainer.name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (rootContainer.width != null && rootContainer.height != null) {
                if ("match_parent".equals(rootContainer.width) && "match_parent".equals(rootContainer.height)) {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } else {
                    int frameWidth = Integer.parseInt(rootContainer.width);
                    int frameHeight = Integer.parseInt(rootContainer.height);
                    frame.setSize(frameWidth,frameHeight);
                    frame.setLocationRelativeTo(null);
                }
            }
        return frame;
    }

    public View getElementById(String id) {
        return components.get(id);
    }
}
