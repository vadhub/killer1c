package org.example.ui;

import org.fife.ui.rsyntaxtextarea.RSyntaxTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class LineNumberPanel extends JPanel {
    private RSyntaxTextArea editorPane;

    public LineNumberPanel(RSyntaxTextArea editorPane) {
        this.editorPane = editorPane;
        setBackground(Color.LIGHT_GRAY);
        setPreferredSize(new Dimension(50, 0));

        // Обработка изменения размера панели
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                revalidate();
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);

        int lineHeight = editorPane.getFontMetrics(editorPane.getFont()).getHeight();
        int lineCount = editorPane.getDocument().getDefaultRootElement().getElementCount();

        for (int i = 0; i < lineCount; i++) {
            int y = (i + 1) * lineHeight;
            g.drawString(String.valueOf(i + 1), 5, y);
        }
    }
}
