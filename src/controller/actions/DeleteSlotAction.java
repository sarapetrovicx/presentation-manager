package controller.actions;

import controller.AbstractRuDokAction;
import gui.components.MainFrame;
import gui.slots.state.StateManagerSlots;
import gui.tree.view.PrezentacijaView;
import model.workspace.Prezentacija;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteSlotAction extends AbstractRuDokAction {

    StateManagerSlots stateManagerSlots;

    public DeleteSlotAction() {
        putValue(SMALL_ICON, loadIcon("images/deleteSlot.png"));
        putValue(SHORT_DESCRIPTION, "Delete slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int indeks  = MainFrame.getInstance().getProjekatView().getJTabbedPane().getSelectedIndex();
        Prezentacija prezentacija = (Prezentacija) MainFrame.getInstance().getProjekatView().getProjekat().getChildren().get(indeks);
        PrezentacijaView prezentacijaView = (PrezentacijaView) prezentacija.getSubscriber();

        stateManagerSlots = prezentacijaView.getStateManagerSlots();
        stateManagerSlots.setDeleteSlotState();
    }
}
