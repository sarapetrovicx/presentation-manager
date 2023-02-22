package command;

import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.composite.RuNode;

import javax.swing.*;

public class AddDeviceCommand extends AbstractCommand{

    private MyTreeNode child;
    private MyTreeNode parent;
    private RuNode ruNode;

    public AddDeviceCommand(MyTreeNode child, RuNode ruNode, MyTreeNode parent) {
        this.child = child;
        this.ruNode = ruNode;
        this.parent = parent;
    }

    @Override
    public void redoCommand() {
        if(parent == null){
            child = new MyTreeNode(ruNode);
        }
        else{
            child = new MyTreeNode(ruNode);
            parent.addChild(child);
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
    }

    @Override
    public void undoCommand() {
        if(parent != null){
            parent.remove(child);
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
    }
}
