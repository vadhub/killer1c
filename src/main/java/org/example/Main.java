package org.example;

import org.example.data.*;
import org.example.data.file_handler.OpenFile;
import org.example.data.file_handler.SaveFile;
import org.example.ui.CodeEditor;
import org.example.ui.Menu;
import org.example.ui.ProjectTree2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws Exception {

        SaveFile saveFile = new SaveFile("/home/vadim/test____", "config.xml");
        Configurator configurator = new Configurator();
        configurator.createConfig();

        System.out.println(Context.output());

        OpenFile openFile = new OpenFile();
        Local local = new Local();
        Menu menu = new Menu();
        CodeEditor codeEditor = new CodeEditor(saveFile);

        JFrame frameMain = new JFrame(local.getText("start"));
        frameMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameMain.setExtendedState(frameMain.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        LayoutInflator inflator = new LayoutInflator();
        ActionListener run = actionEvent -> {
            try {
                //RunPythonFromJava.run(codeEditor.getText());
                inflator.inflate(codeEditor.getText());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };

        ActionListener destroy = actionEvent -> {
            inflator.destroyFrame();
        };

        menu.createMenu(frameMain, run, destroy, openFile, saveFile, configurator);
        frameMain.add(new ProjectTree2().projectTree2Create(Context.currentRootDirectory, codeEditor), BorderLayout.WEST);
        frameMain.add(codeEditor.createPanel(), BorderLayout.CENTER);
        frameMain.setVisible(true);

    }


}