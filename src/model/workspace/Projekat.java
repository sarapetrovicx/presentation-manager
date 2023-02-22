package model.workspace;

import gui.components.MainFrame;
import gui.tree.view.ProjekatView;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;
import model.composite.RuNode;
import model.composite.RuNodeComposite;

import javax.swing.*;
import java.io.File;
import java.io.Serializable;


@Getter
@Setter

public class Projekat extends RuNodeComposite implements Serializable {

    private transient boolean changed;
    private File projectFile;

    public Projekat(String name, RuNode parent) {
        super(name, parent);
        changed = false;
        projectFile = null;
    }

    @Override
    public void addChild(RuNode child) {
        if (child == null)
            return;
        if (child instanceof Prezentacija) {
            super.addChild(child);
            notifySubscribers(child, Message.OTHER);
        }
    }


    @Override
    public void removeChild(RuNode child) {
        if (child == null)
            return;
        notifySubscribers(child, Message.DELETE);
        super.removeChild(child);
    }

    @Override
    public String toString() {
        return ((changed ? "* " : "")+ this.getName());
    }

    public ISubscriber getSubscriber() {
        for (ISubscriber iSubscriber : getSubscribers()) {
            if (iSubscriber instanceof ProjekatView) {
                return iSubscriber;
            }
        }
        return null;
    }
    public void setChanged(boolean changed) {
        if (this.changed!=changed){
            this.changed=changed;
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
        }
    }
}
