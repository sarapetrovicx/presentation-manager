package gui.components;

import gui.components.MainFrame;

import javax.swing.*;

public class Menu extends JMenuBar {

    private JMenu file;
    private JMenu help;
    private JMenu edit;

    public Menu() {
        file = new JMenu("File");
        help = new JMenu("Help");
        edit = new JMenu("Edit");

        file.add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        file.add(MainFrame.getInstance().getActionManager().getPresentationAction());
        file.add(MainFrame.getInstance().getActionManager().getNewSlideAction());
        file.addSeparator();
        file.add(MainFrame.getInstance().getActionManager().getDeleteAction());
        file.addSeparator();
        file.add(MainFrame.getInstance().getActionManager().getSaveProjectAction());
        file.add(MainFrame.getInstance().getActionManager().getOpenProjectAction());

        help.add(MainFrame.getInstance().getActionManager().getInfoAction());

        edit.add(MainFrame.getInstance().getActionManager().getAuthorAction());
        edit.add(MainFrame.getInstance().getActionManager().getImageAction());

        add(file);
        add(edit);
        add(help);
    }
}
