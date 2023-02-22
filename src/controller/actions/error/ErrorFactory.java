package controller.actions.error;


import gui.tree.observer.IPublisher;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;

import java.util.ArrayList;
import java.util.List;

public class ErrorFactory implements IPublisher {

    private static ErrorFactory instance = null;
    private List<ISubscriber> subscribers;

    private ErrorFactory(){
    }

    public static ErrorFactory getInstance() {
        if(instance == null){
            instance = new ErrorFactory();
        }
        return instance;
    }
    public void generateError(Error error){
        String message = "";
        switch(error){
            case EMPTY:
                message = "You can't leave this field empty.";
                break;
            case NO_PIC:
                message = "You have to choose background picture.";
                break;
            case WRONG_TYPE:
                message = "You can't add this here.";
                break;
            case AUTHOR:
                message = "You have to select the presentation.";
                break;
            case NONE_SELECTED:
                message = "You didn't select anything.";
                break;
            case DELETE_WORKSPACE:
                message = "You can't delete workspace.";
                break;
            case WRONG_NAME:
                message = "Wrong name";
                break;
            case PROJECT:
                message = "Choose the project you want to save.";
                break;
        }
        MyError myError = new MyError(error, message);
        notifySubscribers(myError, Message.OTHER);
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
        if(message == null || notification == null || this.subscribers == null || this.subscribers.isEmpty()) {
            return;
        }
        for(ISubscriber listener : subscribers){
            listener.update(notification, Message.OTHER);
        }
    }

}
