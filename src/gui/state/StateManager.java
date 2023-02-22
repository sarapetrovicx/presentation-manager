package gui.state;

import gui.state.concrete.EditState;
import gui.state.concrete.SlideShowState;

public class StateManager {
    private State current;
    private EditState editState;
    private SlideShowState slideShowState;

    public StateManager() {
        init();
    }

    public void init(){
        editState = new EditState();
        slideShowState = new SlideShowState();
        current = editState;
    }

    public State getCurrent() {
        return current;
    }

    public void setEditState() {
        current = editState;
    }

    public void setSlideShowState() {
        current = slideShowState;
    }
}
