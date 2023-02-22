package gui.slots.state;

import gui.slots.state.concrete.AddSlotState;
import gui.slots.state.concrete.DeleteSlotState;
import gui.slots.state.concrete.MoveSlotState;
import gui.slots.state.concrete.SelectSlotState;

public class StateManagerSlots {

    private StateSlots current;
    private AddSlotState addSlotState;
    private DeleteSlotState deleteSlotState;
    private MoveSlotState moveSlotState;
    private SelectSlotState selectSlotState;

    public StateManagerSlots() {
        init();
    }

    public void init() {
        addSlotState = new AddSlotState();
        deleteSlotState = new DeleteSlotState();
        moveSlotState = new MoveSlotState();
        selectSlotState = new SelectSlotState();
        current = selectSlotState;
    }

    public void setAddSlotState() {
        current = addSlotState;
    }

    public void setDeleteSlotState() {
        current = deleteSlotState;
    }

    public void setMoveSlotState() {
        current = moveSlotState;
    }

    public void setSelectSlotState() {
        current = selectSlotState;
    }

    public StateSlots getCurrent() {
        return current;
    }
}
