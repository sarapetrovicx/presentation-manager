package model.composite;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter

public abstract class RuNodeComposite extends RuNode implements Serializable {

    private ArrayList<RuNode> children;

    public RuNodeComposite(String name, RuNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }

    public void addChild(RuNode child){
        if(this.children == null)
            children = new ArrayList<>();
        if(!this.children.contains(child)) {
            this.children.add(child);
        }
    }

    public void removeChild(RuNode child){
        if(this.children == null || !this.children.contains(child))
            return;
        this.children.remove(child);
    }

}
