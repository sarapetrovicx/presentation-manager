package gui.state;

import model.workspace.Prezentacija;

import javax.swing.*;

public interface State {

    void switchStates(Prezentacija prezentacija);
}
