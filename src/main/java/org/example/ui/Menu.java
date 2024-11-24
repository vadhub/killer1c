package org.example.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class Menu {

    public void createMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        // Добавление в главное меню выпадающих пунктов меню
        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu());
        frame.setJMenuBar(menuBar);
    }

    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu file = new JMenu("Файл");
        // Пункт меню "Открыть" с изображением
        JMenuItem open = new JMenuItem("Открыть",
                new ImageIcon("images/open.png"));
        // Пункт меню из команды с выходом из программы
        JMenuItem exit = new JMenuItem(new ExitAction());
        // Добавление к пункту меню изображения
        exit.setIcon(new ImageIcon("images/exit.png"));
        // Добавим в меню пункта open
        file.add(open);
        // Добавление разделителя
        file.addSeparator();
        file.add(exit);

        open.addActionListener(arg0 -> System.out.println("ActionListener.actionPerformed : open"));
        return file;
    }

    private JMenu createViewMenu() {
        // создадим выпадающее меню
        JMenu viewMenu = new JMenu("Вид");
        // меню-флажки
        JCheckBoxMenuItem line = new JCheckBoxMenuItem("Линейка");
        JCheckBoxMenuItem grid = new JCheckBoxMenuItem("Сетка");
        JCheckBoxMenuItem navig = new JCheckBoxMenuItem("Навигация");
        // меню-переключатели
        JRadioButtonMenuItem one = new JRadioButtonMenuItem("Одна страница");
        JRadioButtonMenuItem two = new JRadioButtonMenuItem("Две страницы");
        // организуем переключатели в логическую группу
        ButtonGroup bg = new ButtonGroup();
        bg.add(one);
        bg.add(two);
        // добавим все в меню
        viewMenu.add(line);
        viewMenu.add(grid);
        viewMenu.add(navig);
        // разделитель можно создать и явно
        viewMenu.add(new JSeparator());
        viewMenu.add(one);
        viewMenu.add(two);
        return viewMenu;
    }

    static class ExitAction extends AbstractAction {
        private static final long serialVersionUID = 1L;

        ExitAction() {
            putValue(NAME, "Выход");
        }

        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }
}
