package model.workspace;

import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;
import model.composite.RuNode;
import model.composite.RuNodeComposite;

import java.io.Serializable;

@Getter
@Setter

public class Workspace extends RuNodeComposite implements Serializable {


    public Workspace(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(RuNode child) {
        if(child == null)
            return;
        if(child instanceof Projekat) {
            super.addChild(child);
            notifySubscribers(child, Message.OTHER);
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child == null)
            return;
        child.notifySubscribers(child, Message.DELETE);
        super.removeChild(child);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
