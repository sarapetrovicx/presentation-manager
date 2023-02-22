package gui.slots.state.concrete;

import gui.components.MainFrame;
import gui.slots.model.Slot;
import gui.slots.state.StateSlots;
import gui.slots.view.SlotView;
import gui.tree.view.SlajdView;
import model.workspace.Slajd;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SelectSlotState extends StateSlots {

    private Color oldColor;
    private List <Slot> selectedSlots = new ArrayList<>();

    public void myMousePressed(Slajd slajd, Point point, Color color){

        boolean flag = false;
        SlajdView slajdView = (SlajdView) slajd.getSubscriber();
        for(SlotView slotView : slajdView.getSlotViews()){
            if(slotView.elementAt(point) && selectedSlots.isEmpty()) {
                Slot slot = slotView.getSlot();
                oldColor = slot.getColor();
                slot.setColor(Color.RED);
                slot.setSelected(true);
                selectedSlots.add(slot);
                MainFrame.getInstance().setSelectedSlot(slot);
                flag = true;
                break;
            }
        }
        if(!flag){
            List <Slot> toDelete = new ArrayList<>();
            for(Slot slot : selectedSlots){
                slot.setColor(oldColor);
                slot.setSelected(false);
                toDelete.add(slot);
                MainFrame.getInstance().setSelectedSlot(null);
            }
            selectedSlots.removeAll(toDelete);
        }
    }

}
