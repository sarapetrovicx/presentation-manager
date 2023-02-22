package gui.components;


import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar() {
        add(MainFrame.getInstance().getActionManager().getNewProjectAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getPresentationAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getNewSlideAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getDeleteAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getAuthorAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getImageAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getInfoAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getSharingPresentationAction());
        addSeparator(new Dimension(15, 20));
        add(MainFrame.getInstance().getActionManager().getExportAction());
        setFloatable(false);
    }

}
