package controller.actions;

import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.ImageEditor;
import gui.components.MainFrame;
import gui.components.TextEditor;
import gui.slots.handler.SlotHandler;
import gui.slots.model.Slot;
import gui.slots.model.SlotType;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class EditSlotAction extends AbstractRuDokAction {

    private SlotType slotType;

    public EditSlotAction() {
        putValue(SMALL_ICON, loadIcon("images/edit.png"));
        putValue(SHORT_DESCRIPTION, "Edit slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Slot slot = MainFrame.getInstance().getSelectedSlot();
        if(slot == null){
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
            return;
        }
        if(slot.getType().equals(SlotType.TEXT)) {
            TextEditor textEditor = new TextEditor(MainFrame.getInstance(), "Text editor", true);
            textEditor.setVisible(true);
        } else {
            ImageEditor imageEditor = new ImageEditor(MainFrame.getInstance(), "Image editor", true);
            imageEditor.setVisible(true);
        }
    }
}
