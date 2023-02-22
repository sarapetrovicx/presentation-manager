package controller.actions;

import controller.AbstractRuDokAction;
import gui.components.MainFrame;
import gui.state.StateManager;
import gui.tree.view.PrezentacijaView;
import model.workspace.Prezentacija;
import model.workspace.Projekat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class SlideShowAction extends AbstractRuDokAction {

    StateManager stateManager;
    Projekat projekat;

    public SlideShowAction() {
        putValue(SMALL_ICON, loadIcon("images/slideshow.png"));
        putValue(NAME, "Slideshow");
        putValue(SHORT_DESCRIPTION, "Slideshow");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        projekat = MainFrame.getInstance().getProjekatView().getProjekat();

        int indeks = MainFrame.getInstance().getProjekatView().getJTabbedPane().getSelectedIndex();
        for (int i = 0; i < projekat.getChildren().size(); i++) {
            if(i == indeks){
//                MainFrame.getInstance().getDesniPanel().removeAll();
                Prezentacija prezentacija = (Prezentacija) projekat.getChildren().get(i);
                PrezentacijaView prezentacijaView = (PrezentacijaView) prezentacija.getSubscriber();

                prezentacijaView.getStateManager().setSlideShowState();
                prezentacijaView.getStateManager().getCurrent().switchStates((Prezentacija) projekat.getChildren().get(i));
                MainFrame.getInstance().getDesniPanel().updateUI();
            }
        }

    }
}
