package gui.slots.state.concrete;

import gui.slots.state.StateSlots;
import gui.slots.view.SlotView;
import gui.tree.view.SlajdView;
import model.workspace.Slajd;

import java.awt.*;

public class MoveSlotState extends StateSlots {

    private Point lastPoint;
    private SlotView slotViewNovi;

    public void myMousePressed(Slajd slajd, Point point, Color color){
        SlajdView slajdView = (SlajdView) slajd.getSubscriber();
        for(SlotView slotView : slajdView.getSlotViews()) {
            if (slotView.elementAt(point) && slotView.getSlot().isSelected()) {
                lastPoint = point;
                slotViewNovi = slotView;
            }
        }
    }
    public void myMouseDragged(Point point){
        if(slotViewNovi != null){
            slotViewNovi.getSlot().setPoint(point);
            lastPoint = point;
        }

    }
    public void myMouseReleased(){
        slotViewNovi = null;
        lastPoint = null;
    }
}
