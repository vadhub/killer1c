package org.example.data.code_gen;

import org.example.model.ToDo;

import java.util.concurrent.atomic.AtomicReference;

public class Generator {
    private StringBuilder code;
    private String imports = "";

    public Generator() {
        code = new StringBuilder();
    }

    public void createMain(String... content) {
        code = new StringBuilder();
        code.append(imports)
                .append("public class Run {\n")
                .append("    public static void main(String[] args) {\n")
                .append(String.join("\n", content))
                .append("\n    }\n")
                .append("}\n");
    }

    public void createFrame(String... content) {
        imports += "import javax.swing.*;\n";
        code.append("JFrame frame = new JFrame();\n")
                .append("frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);\n")
                .append(String.join("\n", content));
    }

    public String createPanel(String name) {
        return "JPanel " + name + " = new JPanel();\n";
    }

    public String createTextView(String name) {
        return "JTextField " + name + " = new JTextField();\n";
    }

    public String createCheckBox(String name, String text) {
        return "JCheckBox " + name + " = new JCheckBox(\"" + text + "\");\n";
    }

    public String createRadioButton(String name, String text) {
        return "JRadioButton " + name + " = new JRadioButton(\"" + text + "\");\n";
    }

    public String createComboBox(String name, String[] items) {
        StringBuilder comboBoxCode = new StringBuilder();
        comboBoxCode.append("JComboBox<String> " + name + " = new JComboBox<>(new String[]{");
        for (int i = 0; i < items.length; i++) {
            comboBoxCode.append("\"").append(items[i]).append("\"");
            if (i < items.length - 1) {
                comboBoxCode.append(", ");
            }
        }
        comboBoxCode.append("});\n");
        return comboBoxCode.toString();
    }

    public String setText(String componentName, String text) {
        return componentName + ".setText(\"" + text + "\");\n";
    }

    public String setColumnWidth(String componentName, String width) {
        AtomicReference<String> command = new AtomicReference<>("");
        checkNotNull(width, () -> command.set(componentName + ".setColumnWidth(\"" + width + "\");\n"));
        return command.get();
    }

    public String setSize(String componentName, int width, int height) {
        return componentName + ".setSize(" + width + ", " + height + ");\n";
    }

    public String setBackgroundColor(String componentName, String color) {
        return componentName + ".setBackground(" + color + ");\n";
    }

    public String setForegroundColor(String componentName, String color) {
        return componentName + ".setForeground(" + color + ");\n";
    }

    public String setFont(String componentName, String fontName, int style, int size) {
        return componentName + ".setFont(new Font(\"" + fontName + "\", " + style + ", " + size + "));\n";
    }

    public String setVisible(String componentName, boolean isVisible) {
        return componentName + ".setVisible(" + isVisible + ");\n";
    }

    public String setEnabled(String componentName, boolean isEnabled) {
        return componentName + ".setEnabled(" + isEnabled + ");\n";
    }

    public String addViewToView(String container, String view) {
        return container + ".add(" + view + ");\n";
    }

    public String createButton(String name, String text) {
        return "JButton " + name + " = new JButton(\"" + text + "\");\n";
    }

    public String setFrameSize(int width, int height) {
        return "frame.setSize(" + width + ", " + height + ");\n";
    }

    public String setFrameTitle(String title) {
        return "frame.setTitle(\"" + title + "\");\n";
    }

    public String addButtonActionListener(String buttonName, String action) {
        imports += "import java.awt.event.ActionEvent;\n" +
                "import java.awt.event.ActionListener;";
        return buttonName + ".addActionListener(new ActionListener() {\n" +
                "    public void actionPerformed(ActionEvent e) {\n" +
                action + "\n" +
                "    }\n" +
                "});\n";
    }

    public String createLabel(String name, String text) {
        return "JLabel " + name + " = new JLabel(\"" + text + "\");\n";
    }

    public String setLayout(String layoutType) {
        return "frame.setLayout(new " + layoutType + "());\n";
    }

    public String createList(String name, String[] items) {
        StringBuilder listCode = new StringBuilder();
        listCode.append("JList<String> " + name + " = new JList<>(new DefaultListModel<>());\n");
        for (String item : items) {
            listCode.append(name + ".getModel().addElement(\"" + item + "\");\n");
        }
        return listCode.toString();
    }

    public String setLayout(String containerName, String layoutType) {
        return containerName + ".setLayout(new " + layoutType + "());\n";
    }

    public String setGridLayout(String containerName, int rows, int cols) {
        return containerName + ".setLayout(new GridLayout(" + rows + ", " + cols + "));\n";
    }

    public String setFlowLayout(String containerName) {
        return containerName + ".setLayout(new FlowLayout());\n";
    }

    public String setBorderLayout(String containerName) {
        return containerName + ".setLayout(new BorderLayout());\n";
    }

    public String addComponentToBorderLayout(String containerName, String componentName, String position) {
        return containerName + ".add(" + componentName + ", BorderLayout." + position.toUpperCase() + ");\n";
    }

    public String getGeneratedCode() {
        return code.toString();
    }

    public void setCode(String str) {
        code.append(str);
    }

    public void checkNotNull(String str, ToDo toDo) {
        if (str != null) {
            toDo.doIt();
        }
    }

    public static String build() {
        String namePanel = "panel";
        String nameTextView = "tv1";
        String nameButton = "button";
        Generator g = new Generator();

        g.createFrame(
                g.createTextView(nameTextView),
                g.createPanel(namePanel),
                g.addViewToView(namePanel, nameTextView),
                g.createButton(nameButton, "Click Me"),
                g.addButtonActionListener(nameButton, "System.out.println(\"Button clicked!\");"),
                g.addViewToView(namePanel, nameButton),
                g.setFrameSize(400, 300),
                g.setFrameTitle("My Swing Application"),
                g.addViewToView("frame", namePanel)
        );

        g.createMain(
                g.getGeneratedCode()
        );

        return g.getGeneratedCode();
    }

}
