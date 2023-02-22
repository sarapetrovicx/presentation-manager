package command;

import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;

import javax.swing.*;
import java.util.ArrayList;

public class DeleteDeviceCommand extends AbstractCommand{
    private MyTreeNode child;
    private MyTreeNode parent;
    private RuNode ruNode;
    private ArrayList<Projekat> toRemove;

    public DeleteDeviceCommand(MyTreeNode child, RuNode ruNode) {
        this.child = child;
        this.ruNode = ruNode;
        this.parent = (MyTreeNode) child.getParent();
    }

    @Override
    public void redoCommand() {
        if(parent != null){
            if(ruNode instanceof Prezentacija && !(((Prezentacija) ruNode).getClones().isEmpty())){
                toRemove = new ArrayList<>();
                for (Projekat projekat : ((Prezentacija) ruNode).getClones()) {
                    MyTreeNode proj = new MyTreeNode(projekat);
                    proj.remove(child);
                    toRemove.add(projekat);
                }
                ((Prezentacija) ruNode).getClones().removeAll(toRemove);
            } else {
                parent.remove(child);
            }
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
    }

    @Override
    public void undoCommand() {
        if(parent == null){
            child = new MyTreeNode(ruNode);
        }
        else{
            if(ruNode instanceof Prezentacija && toRemove != null){
                for(Projekat projekat : toRemove){
                    MyTreeNode projTreeNode = new MyTreeNode(projekat);
                    projTreeNode.addChild(child);
                }
                child = new MyTreeNode(ruNode);
                parent.addChild(child);
            } else {
                child = new MyTreeNode(ruNode);
                parent.addChild(child);
            }
        }
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
    }
}
