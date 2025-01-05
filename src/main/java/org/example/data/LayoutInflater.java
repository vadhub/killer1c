package org.example.data;

import org.example.api.Inflater;
import org.example.data.file_handler.ReadFile;
import org.example.model.Button;
import org.example.model.Label;
import org.example.model.*;
import org.example.model.manifest.Manifest;
import org.example.model.topbar.Item;
import org.simpleframework.xml.core.Persister;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileReader;
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

    public ViewGroup inflateView(File xml) throws Exception {
        Reader reader = new FileReader(xml);
        Persister serializer = new Persister();
        return deserializeViewGroup(ReadFile.readRoot(new FileReader(xml)), serializer, reader);
    }

    public ViewGroup inflateView(String xml) throws Exception {
        Reader reader = new FileReader(xml);
        Persister serializer = new Persister();
        return deserializeViewGroup(ReadFile.readRoot(new StringReader(xml)), serializer, reader);
    }

    public void inflate(String xml, String pathProject) throws Exception {
        frame = createRoot(new File("values/res"));
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
            } else if (it instanceof Label lb) {
                jComponent = lb.createLabel();
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

    public JTabbedPane createTabs(File resDirectory, List<Item> items) throws Exception {
        JTabbedPane tabPanel = new JTabbedPane();
        if (resDirectory.isDirectory()) {
            File[] files = resDirectory.listFiles();

            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        System.out.println("Чтение файла: " + files[i].getName());
                        ViewGroup viewGroup = inflateView(files[i]);
                        tabPanel.add(items.get(i).name, createViews(viewGroup.views, viewGroup));
                        System.out.println(ReadFile.read(files[i].getPath()));
                    }
                }
            } else {
                System.out.println("Ошибка: не удалось получить список файлов.");
            }
        } else {
            System.out.println("Указанный путь не является директорией.");
        }
        return tabPanel;
    }

    public JFrame createRoot(File res) throws Exception {
        Persister serializer = new Persister();
        Manifest manifest =  serializer.read(Manifest.class, new FileReader("values/manifest.xml"), false);
        JFrame frame = new JFrame(manifest.name);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        if (manifest.width != null && manifest.height != null) {
            if ("match_parent".equals(manifest.width) && "match_parent".equals(manifest.height)) {
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } else {
                int frameWidth = Integer.parseInt(manifest.width);
                int frameHeight = Integer.parseInt(manifest.height);
                frame.setSize(frameWidth, frameHeight);
                frame.setLocationRelativeTo(null);
            }
        }

        frame.add(createTabs(res, manifest.topbar.bars));
        return frame;
    }

    public View getElementById(String id) {
        return components.get(id);
    }
}
