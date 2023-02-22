package gui.tree.observer;

public interface ISubscriber {
    void update(Object notification, Message message);
}
