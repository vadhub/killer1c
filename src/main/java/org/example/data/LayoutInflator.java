package org.example.data;

import org.example.data.code_gen.Generator;
import org.example.model.*;
import org.simpleframework.xml.core.Persister;

import javax.swing.*;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

public class LayoutInflator {

    private JFrame frame;
    private HashMap<String, JComponent> components = new HashMap<>();
    private Generator g;

    public void destroyFrame() {
        frame.dispose();
    }

    public void inflate(String xml) throws Exception {
        g = new Generator();
        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();
        RootContainer container = serializer.read(RootContainer.class, reader, false);
        frame = createRoot(container);
        frame.add(createViews(container.views));

        System.out.println(g.getGeneratedCode());

        frame.setVisible(true);

        g.createMain(
                g.getGeneratedCode()
        );

       // System.out.println(g.getGeneratedCode());
    }

    public JPanel createPanel() {
        JPanel jPanel = new JPanel();
        return jPanel;
    }

    public JPanel createViews(List<View> views) {
        JPanel jPanel = createPanel();
        g.setCode(g.createPanel("panel"));
        views.forEach(it -> {
            if (it instanceof Button btn) {
                System.out.println("create button");
                JComponent component = ((Button) it).createButton();
                g.setCode(g.createButton(btn.id, btn.text));
                g.setCode(g.addViewToView("panel", btn.id));
                jPanel.add(component);
                components.put(it.id, component);
            } else if (it instanceof TextView textView) {
                System.out.println("create text view");
                g.setCode(g.createTextView(textView.id));
                g.setCode(g.setText(textView.id, textView.text));
                g.setCode(g.setColumnWidth(textView.id, textView.width));
                g.setCode(g.addViewToView("panel", textView.id));
                JComponent component = ((TextView) it).createTextView();
                jPanel.add(component);
                components.put(it.id, component);
            }
        });
        g.setCode(g.addViewToView("frame", "panel"));
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
        JFrame frame = new JFrame();
        StringBuilder sb = new StringBuilder();

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            if (rootContainer.width != null && rootContainer.height != null) {
                if ("match_parent".equals(rootContainer.width) && "match_parent".equals(rootContainer.height)) {
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                    sb.append("frame.setExtendedState(JFrame.MAXIMIZED_BOTH);");
                } else {
                    frame.setSize(Integer.parseInt(rootContainer.width), Integer.parseInt(rootContainer.height));
                    frame.setLocationRelativeTo(null);
                    sb.append("frame.setSize(").
                            append(Integer.parseInt(rootContainer.width)).
                            append("),").append(Integer.parseInt(rootContainer.height)).
                            append("; frame.setLocationRelativeTo(null);");

                }
            }
        g.createFrame(
                g.setFrameTitle("My Swing Application"),
                sb.toString()
        );
        return frame;
    }

    public JComponent getElementById(String id) {
        return components.get(id);
    }
}
