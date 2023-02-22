package gui.slots.state.concrete;

import gui.components.MainFrame;
import gui.slots.model.Slot;
import gui.slots.state.StateSlots;
import model.workspace.Slajd;

import java.awt.*;

public class AddSlotState extends StateSlots {

    private float debljina = 2f;
    private int dashed;

    @Override
    public void myMousePressed(Slajd slajd, Point point, Color color) {
        debljina = MainFrame.getInstance().getActionManager().getStrokeAction().getBroj();
        dashed = MainFrame.getInstance().getActionManager().getStrokeAction().getOdgovor();

        Slot slot = new Slot(new Dimension(90, 40), point, Color.BLACK, new BasicStroke(2f));
        slot.setColor(color);
        if(dashed == 0){
            slot.setStroke(new BasicStroke(debljina, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                    0, new float[]{9}, 0));
        } else {
            slot.setStroke(new BasicStroke(debljina));
        }

        slajd.addSlot(slot);
    }
}
