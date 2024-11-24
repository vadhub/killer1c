package org.example.ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class Menu {

    public void createMenu(JFrame frame, ActionListener run, ActionListener destroy) {
        JMenuBar menuBar = new JMenuBar();
        JButton start = new JButton(new ImageIcon("drawable/play_arrow.png"));
        start.addActionListener(run);
        start.setBorder(BorderFactory.createEtchedBorder());

        JButton stop = new JButton(new ImageIcon("drawable/stop_.png"));
        stop.addActionListener(destroy);
        stop.setBorder(BorderFactory.createEtchedBorder());

        menuBar.add(createFileMenu());
        menuBar.add(createViewMenu());
        menuBar.add(start);
        menuBar.add(stop);
        frame.setJMenuBar(menuBar);
    }

    private JMenu createFileMenu() {
        // Создание выпадающего меню
        JMenu file = new JMenu("Файл");
        // Пункт меню "Открыть" с изображением
        JMenuItem open = new JMenuItem("Открыть", new ImageIcon("images/open.png"));
        JMenuItem save = new JMenuItem("Сохранить", new ImageIcon("images/open.png"));
        save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
        JMenuItem saveAs = new JMenuItem("Сохранить как", new ImageIcon("images/open.png"));
        JMenuItem exit = new JMenuItem(new ExitAction());
        exit.setIcon(new ImageIcon("images/exit.png"));

        file.add(open);
        file.add(save);
        file.add(saveAs);

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
