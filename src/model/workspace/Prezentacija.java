package model.workspace;

import gui.tree.view.PrezentacijaView;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;
import model.composite.RuNode;
import model.composite.RuNodeComposite;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class Prezentacija extends RuNodeComposite implements Serializable {

    private String autor;
    private String URL;
    private transient List<Projekat> clones = new ArrayList<>();


    public Prezentacija(String name, RuNode parent, String autor, String url) {
        super(name, parent);
        this.autor = autor;
        this.URL = url;
//        notifySubscribers(this, Message.OTHER);
    }

    @Override
    public void addChild(RuNode child) {
        if(child == null)
            return;
        if(child instanceof Slajd) {
            super.addChild(child);
            notifySubscribers(child, Message.OTHER);
        }
    }

    @Override
    public void removeChild(RuNode child) {
        if(child == null)
            return;
        super.removeChild(child);
        notifySubscribers(child, Message.DELETE);
    }
    @Override
    public String
    toString() {
        return super.toString();
    }

    public ISubscriber getSubscriber(){
        for(ISubscriber sub: this.getSubscribers()){
            if (sub instanceof PrezentacijaView) {
                return sub;
            }
        }
        return null;
    }

    public void addClone(Projekat projekat){
        if(clones == null)
            clones = new ArrayList<>();
        if(projekat == null || clones.contains(projekat))
            return;
        clones.add(projekat);
    }

    public void setAutor(String autor) {
        this.autor = autor;
        notifySubscribers(this, Message.OTHER);
    }

    public void setURL(String URL) {
        this.URL = URL;
        notifySubscribers(this, Message.OTHER);
    }
    public Object readResolve(){
        clones = new ArrayList<>();
        return this;
    }
}
