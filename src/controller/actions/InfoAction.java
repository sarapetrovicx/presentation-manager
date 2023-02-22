package controller.actions;

import controller.AbstractRuDokAction;
import gui.components.InfoDialog;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractRuDokAction {

    private InfoDialog infoDialog;

    public InfoAction() {
        putValue(SMALL_ICON, loadIcon("images/info1.png"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        infoDialog = new InfoDialog();
        infoDialog.setDialogVisible();
    }
}
