package org.example.ui;

import org.example.data.ReadFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;


public class ProjectTree2 {

    private JTree fileTree;

    public JScrollPane projectTree2Create(String pathname, Consumer<String> file) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode("Project");
        fileTree = new JTree(rootNode);

        fileTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TreePath path = fileTree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        Object node = path.getLastPathComponent();
                        if (!isPackageNode(node)) {
                            try {
                                file.accept(ReadFile.read(pathname+File.separator+node));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }
        });

        JScrollPane treeScroll = new JScrollPane(fileTree);
        treeScroll.setPreferredSize(new Dimension(300, 300));
        treeScroll.setBackground(Color.LIGHT_GRAY);
        treeScroll.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        loadFileTree(rootNode, new File(pathname));
        return treeScroll;
    }

    private boolean isPackageNode(Object node) {
        return node instanceof DefaultMutableTreeNode && !((DefaultMutableTreeNode) node).isLeaf();
    }

    private void loadFileTree(DefaultMutableTreeNode parentNode, File file) {
        if (file.isDirectory()) {
            DefaultMutableTreeNode directoryNode = new DefaultMutableTreeNode(file.getName());
            parentNode.add(directoryNode);

            File[] files = file.listFiles();
            if (files != null) {
                for (File childFile : files) {
                    loadFileTree(directoryNode, childFile);
                }
            }
        } else {
            parentNode.add(new DefaultMutableTreeNode(file.getName()));
        }
    }
}
