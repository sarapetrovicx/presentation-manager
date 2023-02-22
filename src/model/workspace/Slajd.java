package model.workspace;

import gui.slots.model.Slot;
import gui.tree.observer.Message;
import gui.tree.view.SlajdView;
import gui.tree.observer.ISubscriber;
import lombok.Getter;
import lombok.Setter;
import model.composite.RuNode;

import java.io.Serializable;
import java.util.ArrayList;


@Getter
@Setter


public class Slajd extends RuNode implements Serializable {

    private int redniBroj;
    private ArrayList<Slot> slots = new ArrayList<>();

    public Slajd(String name, RuNode parent) {
        super(name, parent);
    }

    @Override
    public String
    toString() {
        return super.toString();
    }

    public ISubscriber getSubscriber(){
        for(ISubscriber sub: this.getSubscribers()){
            if (sub instanceof SlajdView) {
                return sub;
            }
        }
        return null;
    }

    public void addSlot(Slot slot){
        if(slot != null && !this.slots.contains(slot)){
            slots.add(slot);
            notifySubscribers(slot, Message.OTHER);
        }
    }
    public void removeSlot(Slot slot){
        if(slot == null || this.slots == null || !this.slots.contains(slot))
            return;
        this.slots.remove(slot);
        notifySubscribers(slot, Message.DELETE);
    }

}
