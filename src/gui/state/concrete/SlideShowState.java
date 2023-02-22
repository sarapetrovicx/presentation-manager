package gui.state.concrete;

import gui.components.MainFrame;
import gui.state.State;
import gui.tree.view.ProjekatView;
import lombok.Getter;
import lombok.Setter;
import model.workspace.Prezentacija;
import model.workspace.Projekat;

@Getter
@Setter

public class SlideShowState implements State {

    SlideShowView slideShow;

    @Override
    public void switchStates(Prezentacija prezentacija) {
        slideShow = new SlideShowView(prezentacija);
        int indeks = MainFrame.getInstance().getProjekatView().getJTabbedPane().getSelectedIndex();
        MainFrame.getInstance().getProjekatView().getJTabbedPane().setComponentAt(indeks, slideShow);
        Projekat projekat = (Projekat) prezentacija.getParent();
        ProjekatView projekatView = MainFrame.getInstance().getProjekatView();
        projekatView.setProjekat(projekat);
    }

}
