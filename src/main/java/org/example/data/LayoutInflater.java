package org.example.data;

import org.example.api.Inflater;
import org.example.data.file_handler.ReadFile;
import org.example.model.Button;
import org.example.model.*;
import org.simpleframework.xml.core.Persister;

import javax.swing.*;
import java.awt.*;
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
        ViewGroup viewGroup = deserializeViewGroup(ReadFile.readRoot(new StringReader(xml)), serializer, reader);
        frame = createRoot(viewGroup);
        frame.setVisible(true);
    }

    private ViewGroup deserializeViewGroup(String rootContainer, Persister serializer, Reader reader) throws Exception {
        if (rootContainer.equals("FrameContainer")) {
            return serializer.read(FrameContainer.class, reader, false);
        } else if (rootContainer.equals("LinearContainer")){
            return serializer.read(LinearContainer.class, reader, false);
        }

        return serializer.read(FrameContainer.class, reader, false);
    }

    public JPanel createPanel() {
        JPanel jPanel = new JPanel();
        return jPanel;
    }

    public JPanel createViews(List<ViewGroup> views, ViewGroup root) {
        JPanel jPanel = new JPanel();
        jPanel.setLayout(createLayoutManager(jPanel, root));
        views.forEach(it -> {
            JComponent jComponent = null;
            if (it instanceof Button btn) {
                jComponent = btn.createButton();
            } else if (it instanceof TextView tv) {
                jComponent = tv.createTextView();
            } else if (it instanceof Table tbl) {
                jComponent = new JScrollPane(tbl.createTable());
            }
            if (jComponent != null) {
                jPanel.add(jComponent);
                components.put(it.id, it);
            }
        });
        return jPanel;
    }

    public LayoutManager createLayoutManager(Container root, ViewGroup it) {
        LayoutManager manager = null;
        if (it instanceof FrameContainer) {
            manager = new BorderLayout();
        } else if (it instanceof LinearContainer linearContainer) {
            if (linearContainer.orientation.equals(LinearContainer.ORIENTATION_VERTICAL)) {
                manager = new BoxLayout(root, BoxLayout.Y_AXIS);
            } else {
                manager = new BoxLayout(root, BoxLayout.X_AXIS);
            }
        }
        components.put(it.id, it);
        return manager;
    }

    public JFrame createRoot(ViewGroup root) {
        JFrame frame;
        if (root instanceof FrameContainer f) {
            frame = new JFrame(f.name);
        } else {
            frame = new JFrame();
        }
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (root.width != null && root.height != null) {
            if ("match_parent".equals(root.width) && "match_parent".equals(root.height)) {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else {
                int frameWidth = Integer.parseInt(root.width);
                int frameHeight = Integer.parseInt(root.height);
                frame.setSize(frameWidth, frameHeight);
                frame.setLocationRelativeTo(null);
            }
        }

        if (root instanceof FrameContainer frameContainer1) {
            frame.add(createViews(root.views, root), frameContainer1.getPosition());
        } else if (root instanceof LinearContainer) {
            frame.add(createViews(root.views, root));
        }
        return frame;
    }

    public View getElementById(String id) {
        return components.get(id);
    }
}
