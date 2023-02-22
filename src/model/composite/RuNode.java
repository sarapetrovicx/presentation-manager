package model.composite;

import gui.tree.observer.IPublisher;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;

@Getter
@Setter

public abstract class RuNode implements IPublisher, Serializable {

    private String name;
    private RuNode parent;
    private transient ArrayList <ISubscriber> subscribers;


    public RuNode(String name, RuNode parent) {
        this.name = name;
        this.parent = parent;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification, Message message) {
        if(notification == null || message == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers){
            listener.update(notification, message);
        }
    }

    public void setName(String name) {
        this.name = name;
        notifySubscribers(this, Message.NAME);
    }

    public Object readResolve(){
        subscribers = new ArrayList<>();
        return this;
    }
}
