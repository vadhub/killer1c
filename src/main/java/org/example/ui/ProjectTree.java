package org.example.ui;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class ProjectTree {

    final String ROOT = "<html><font color=blue>Корневая запись";
    final String[] nodes = new String[]{"<html><pre>Напитки", "Сладости", "Фрукты"};
    final String[][] leafs = new String[][]{{"Чай", "Кофе", "Коктейль", "Сок", "Морс", "Минералка"},
            {"<html><i>Пирожное", "<html><i>Мороженое", "<html><b>Зефир", "<html><b>Халва"}, {"Груша"}};

    public JTree createProjectTree() {
        JTree tree = new JTree(createTreeModel());
        // Создание и настройка отображающего объекта
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon  (new ImageIcon("drawable/class_.png"));
        renderer.setOpenIcon  (new ImageIcon("drawable/folder_.png"));
        renderer.setClosedIcon(new ImageIcon("drawable/folder_.png"));
        // Определение в дереве отображающего объекта
        tree.setCellRenderer(renderer);
        return tree;
    }

    // Иерархическая модель данных TreeModel для деревьев
    private TreeModel createTreeModel() {
        // Корневой узел дерева
        DefaultMutableTreeNode root = new DefaultMutableTreeNode(ROOT);
        // Добавление ветвей - потомков 1-го уровня
        DefaultMutableTreeNode drink = new DefaultMutableTreeNode(nodes[0]);
        DefaultMutableTreeNode sweet = new DefaultMutableTreeNode(nodes[1]);
        DefaultMutableTreeNode fruit = new DefaultMutableTreeNode(nodes[2], true);
        // Добавление ветвей к корневой записи
        root.add(drink);
        root.add(sweet);
        root.add(fruit);
        // Добавление листьев - потомков 2-го уровня
        for (int i = 0; i < leafs[0].length; i++)
            drink.add(new DefaultMutableTreeNode(leafs[0][i], false));
        for (int i = 0; i < leafs[1].length; i++)
            sweet.add(new DefaultMutableTreeNode(leafs[1][i], false));
        for (int i = 0; i < leafs[2].length; i++)
            fruit.add(new DefaultMutableTreeNode(leafs[2][i], false));
        // Создание стандартной модели
        return new DefaultTreeModel(root);
    }
}
