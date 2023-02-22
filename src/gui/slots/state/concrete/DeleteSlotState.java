package gui.slots.state.concrete;

import gui.slots.state.StateSlots;
import gui.slots.view.SlotView;
import gui.tree.view.SlajdView;
import model.workspace.Slajd;

import java.awt.*;

public class DeleteSlotState extends StateSlots {

    public void myMousePressed(Slajd slajd, Point point, Color color){
        SlajdView slajdView = (SlajdView) slajd.getSubscriber();
        for(SlotView slotView : slajdView.getSlotViews()){
            if(slotView.elementAt(point)){
                slajd.removeSlot(slotView.getSlot());
                break;
            }
        }
    }
}
