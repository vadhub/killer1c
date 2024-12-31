package org.example.data;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;
import org.example.data.code_gen.Generator;
import org.example.data.file_handler.SaveFile;
import org.example.model.*;
import org.simpleframework.xml.core.Persister;

import javax.lang.model.element.Modifier;
import javax.swing.*;
import java.io.File;
import java.io.Reader;
import java.io.StringReader;
import java.util.HashMap;
import java.util.List;

public class LayoutInflator {

    private JFrame frame;
    private HashMap<String, View> components = new HashMap<>();
    private Generator g;
    private File srcDir;

    public void destroyFrame() {
        frame.dispose();
    }

    public void inflate(String xml) throws Exception {
        srcDir = new File("generate");
        if (!srcDir.exists()) {
            srcDir.mkdirs();
        }
        this.g= new Generator();
        Reader reader = new StringReader(xml);
        Persister serializer = new Persister();
        RootContainer container = serializer.read(RootContainer.class, reader, false);
        frame = createRoot(container);
        frame.add(createViews(container.views));
        frame.setVisible(true);
        g.createMain(g.getGeneratedCode());
        System.out.println(g.getGeneratedCode());
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
                createButton(it);
                JComponent component = ((Button) it).createButton();
                g.setCode(g.createButton(btn.id, btn.text));
                g.setCode(g.addViewToView("panel", btn.id));
                jPanel.add(component);
                components.put(it.id, it);
            } else if (it instanceof TextView textView) {
                System.out.println("create text view");
                createTextView(it);
                g.setCode(g.createTextView(textView.id));
                g.setCode(g.setText(textView.id, textView.text));
                g.setCode(g.setColumnWidth(textView.id, textView.width));
                g.setCode(g.addViewToView("panel", textView.id));
                JComponent component = ((TextView) it).createTextView();
                jPanel.add(component);
                components.put(it.id, it);
            }
        });
        g.setCode(g.addViewToView("frame", "panel"));
        g.setCode(g.setVisible("frame", true));
        return jPanel;
    }

    private void createTextView(View it) {
        File javaClass = new File("TextView_" + it.id);
        TypeSpec view = TypeSpec.classBuilder(javaClass.getName())
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(String.class, "id", Modifier.PUBLIC).initializer(it.id).build())
                .addField(FieldSpec.builder(String.class, "text", Modifier.PUBLIC).initializer(it.text).build())
                .addField(FieldSpec.builder(String.class, "height", Modifier.PUBLIC).initializer(it.height).build())
                .addField(FieldSpec.builder(String.class, "width", Modifier.PUBLIC).initializer(it.width).build())
                .addField(FieldSpec.builder(String.class, "type", Modifier.PUBLIC).initializer(((TextView) it).type).build())
                .build();
        SaveFile.saveFile(srcDir.getPath(), javaClass.getName()+".java", view.toString());
    }

    private TypeSpec createButton(View it) {
        File javaClass = new File("Button_" + it.id);
        TypeSpec view = TypeSpec.classBuilder(javaClass.getName())
                .addModifiers(Modifier.PUBLIC)
                .addField(FieldSpec.builder(String.class, "id", Modifier.PUBLIC).initializer(it.id).build())
                .addField(FieldSpec.builder(String.class, "text", Modifier.PUBLIC).initializer(it.text).build())
                .addField(FieldSpec.builder(String.class, "height", Modifier.PUBLIC).initializer(it.height).build())
                .addField(FieldSpec.builder(String.class, "width", Modifier.PUBLIC).initializer(it.width).build())
                .build();
        SaveFile.saveFile(srcDir.getPath(), javaClass.getName()+".java", view.toString());
        return view;
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
                    int frameWidth = Integer.parseInt(rootContainer.width);
                    int frameHeight = Integer.parseInt(rootContainer.height);
                    frame.setSize(frameWidth,frameHeight);
                    frame.setLocationRelativeTo(null);
                    sb.append("frame.setSize(").
                            append(frameWidth).append(',').
                            append(frameHeight).append(")").
                            append("; frame.setLocationRelativeTo(null);");

                }
            }
        g.createFrame(
                g.setFrameTitle("My Swing Application"),
                sb.toString()
        );
        return frame;
    }

    public View getElementById(String id) {
        System.out.println(id);
        System.out.println(components.get("tv1").text);
        return components.get(id);
    }
}
