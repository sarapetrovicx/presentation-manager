package controller.actions;

import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.MainFrame;
import gui.slots.model.Slot;
import gui.slots.model.SlotType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class TypeOfSlotAction extends AbstractRuDokAction {

    private SlotType slotType;

    public TypeOfSlotAction() {
        putValue(SMALL_ICON, loadIcon("images/type_of_slot.png"));
        putValue(SHORT_DESCRIPTION, "Change type of slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Slot slot = MainFrame.getInstance().getSelectedSlot();

        if(slot == null){
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
            return;
        }

        Object[] buttons = {"Image", "Text"};

        int n = JOptionPane.showOptionDialog(null,
                "Do you want your slot to contain image or text?",
                    "Choose a type",
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    buttons,
                    buttons[0]);
        if (n == 0) slotType = SlotType.IMAGE;
        else slotType = SlotType.TEXT;

        slot.setType(slotType);
    }
}
