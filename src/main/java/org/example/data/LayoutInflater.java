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
        ViewGroup container = serializer.read(ViewGroup.class, reader, false);
        frame = createRoot(container);
        frame.setVisible(true);
    }

    public JPanel createPanel() {
        JPanel jPanel = new JPanel();
        return jPanel;
    }

    public JPanel createViews(List<ViewGroup> views) {
        JPanel jPanel = createPanel();
        views.forEach(it -> {
            JComponent jComponent = null;
            if (it instanceof Button btn) {
                jComponent = btn.createButton();
            } else if (it instanceof TextView tv) {
                jComponent = tv.createTextView();
            } else if (it instanceof Table tbl) {
                jComponent = new JScrollPane(tbl.createTable());
            }
            jPanel.add(jComponent);
            components.put(it.id, it);
        });
        return jPanel;
    }

    public JFrame createRoot(ViewGroup frameContainer) {
        JFrame frame;
        if (frameContainer instanceof FrameContainer f) {
            frame = new JFrame(f.name);
        } else {
            frame = new JFrame();
        }
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (frameContainer.width != null && frameContainer.height != null) {
            if ("match_parent".equals(frameContainer.width) && "match_parent".equals(frameContainer.height)) {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else {
                int frameWidth = Integer.parseInt(frameContainer.width);
                int frameHeight = Integer.parseInt(frameContainer.height);
                frame.setSize(frameWidth, frameHeight);
                frame.setLocationRelativeTo(null);
            }
        }
        frame.add(createViews(frameContainer.views));
        return frame;
    }

    public View getElementById(String id) {
        return components.get(id);
    }
}
