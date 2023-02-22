package command;

import gui.components.MainFrame;
import model.composite.RuNode;
import model.workspace.Prezentacija;

import javax.swing.*;

public class EditDeviceCommand extends AbstractCommand{

    private RuNode ruNode;
    private String oldName;
    private String name;
    private int i;

    public EditDeviceCommand(RuNode ruNode, String name, int i) {
        this.ruNode = ruNode;
        this.name = name;
        this.i = i;
        if(i == 1){
            oldName = ((Prezentacija)ruNode).getAutor();
        } else {
            oldName = ruNode.getName();
        }
    }

    @Override
    public void redoCommand() {
        if(i == 1){
            ((Prezentacija)ruNode).setAutor(name);
        } else {
            ruNode.setName(name);
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
    }

    @Override
    public void undoCommand() {
        if(i == 1){
            ((Prezentacija)ruNode).setAutor(oldName);
        } else {
            ruNode.setName(oldName);
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
    }
}
