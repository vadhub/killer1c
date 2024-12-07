package org.example.ui;

import org.example.data.Context;
import org.example.data.file_handler.ReadFile;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;


public class ProjectTree2 {

    private JTree fileTree;

    public JScrollPane projectTree2Create(String pathname, Consumer<String> file) {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(Context.currentRootDirectory);
        fileTree = new JTree(rootNode);

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem createFileItem = new JMenuItem("Создать файл");
        createFileItem.addActionListener(this::createFile);
        popupMenu.add(createFileItem);

        fileTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    TreePath path = fileTree.getPathForLocation(e.getX(), e.getY());
                    if (path != null) {
                        Object node = path.getLastPathComponent();
                        if (!isPackageNode(node)) {
                            try {
                                StringBuilder p = getPathFromTreePath(path);
                                System.out.println(p);
                                file.accept(ReadFile.read(p.toString()));
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    showPopup(e);
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

    private StringBuilder getPathFromTreePath(TreePath path) {
        StringBuilder p = new StringBuilder(Context.currentRootDirectory);
        for (int i = 2; i< path.getPathCount(); i++) {
            p.append(File.separator).append(path.getPathComponent(i));
        }
        return p;
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

    private void showPopup(MouseEvent e) {
        int row = fileTree.getRowForLocation(e.getX(), e.getY());
        fileTree.setSelectionRow(row);
        if (row != -1) {
            // Используем уже созданное всплывающее меню
            JPopupMenu popup = new JPopupMenu();
            JMenuItem createFileItem = new JMenuItem("Создать файл");
            createFileItem.addActionListener(this::createFile);
            popup.add(createFileItem);

            // Отображаем меню
            popup.show(e.getComponent(), e.getX(), e.getY());
        }
    }

    private void createFile(ActionEvent e) {
        DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) fileTree.getLastSelectedPathComponent();
        if (selectedNode != null) {
            // Получаем путь к выбранному узлу
            String directoryPath = getPathFromNode(selectedNode);
            String fileName = JOptionPane.showInputDialog("Введите имя файла:");
            if (fileName != null && !fileName.trim().isEmpty()) {
                File newFile = new File(directoryPath, fileName);
                System.out.println(newFile);
                try {
                    if (newFile.createNewFile()) {
                        JOptionPane.showMessageDialog(fileTree, "Файл создан: " + newFile.getAbsolutePath());
                    } else {
                        JOptionPane.showMessageDialog(fileTree, "Файл уже существует.");
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(fileTree, "Ошибка при создании файла: " + ex.getMessage());
                }
            }
        }
    }

    private String getPathFromNode(DefaultMutableTreeNode node) {
        StringBuilder path = new StringBuilder();

        while (node != null) {
            // Получаем объект узла и преобразуем его в строку
            Object userObject = node.getUserObject();
            if (userObject != null) {
                // Добавляем имя узла и разделитель к пути
                path.insert(0, userObject + File.separator);
            }
            // Поднимаемся к родительскому узлу
            node = (DefaultMutableTreeNode) node.getParent();
        }
        // Удаляем последний разделитель, если необходимо
        if (path.length() > 0) {
            path.setLength(path.length() - 1); // Удаляем последний File.separator
        }
        return path.toString();
    }

}