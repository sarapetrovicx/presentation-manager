package gui.state.concrete;

import gui.components.MainFrame;
import gui.state.State;
import gui.tree.view.PrezentacijaView;
import gui.tree.view.ProjekatView;
import model.workspace.Prezentacija;
import model.workspace.Projekat;

import javax.swing.*;


public class EditState implements State {


    @Override
    public void switchStates(Prezentacija prezentacija) {
        if(prezentacija.getParent() instanceof Projekat){
            Projekat projekat = (Projekat) prezentacija.getParent();
            if(projekat.getSubscriber()!=null && projekat.getSubscriber() instanceof ProjekatView){
                PrezentacijaView prezentacijaView = (PrezentacijaView) prezentacija.getSubscriber();
                int indeks = MainFrame.getInstance().getProjekatView().getJTabbedPane().getSelectedIndex();
                MainFrame.getInstance().getProjekatView().getJTabbedPane().setComponentAt(indeks, prezentacijaView);
                ProjekatView projekatView = MainFrame.getInstance().getProjekatView();
                projekatView.setProjekat(projekat);
//                MainFrame.getInstance().getDesniPanel().repaint();
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance());
            }
            MainFrame.getInstance().getDesniPanel().updateUI();
        }
    }
}
