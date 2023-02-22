package controller.actions;

import controller.AbstractRuDokAction;
import gui.components.MainFrame;
import gui.slots.state.StateManagerSlots;
import gui.tree.view.PrezentacijaView;
import model.workspace.Prezentacija;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class AddSlotAction extends AbstractRuDokAction {

    StateManagerSlots stateManagerSlots;

    public AddSlotAction() {
        putValue(SMALL_ICON, loadIcon("images/addSlot.png"));
        putValue(SHORT_DESCRIPTION, "Add slot");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int indeks  = MainFrame.getInstance().getProjekatView().getJTabbedPane().getSelectedIndex();
        Prezentacija prezentacija = (Prezentacija) MainFrame.getInstance().getProjekatView().getProjekat().getChildren().get(indeks);
        PrezentacijaView prezentacijaView = (PrezentacijaView) prezentacija.getSubscriber();

        stateManagerSlots = prezentacijaView.getStateManagerSlots();

        stateManagerSlots.setAddSlotState();
        MainFrame.getInstance().getProjekatView().updateUI();
    }
}
