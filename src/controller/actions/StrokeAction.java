package controller.actions;

import controller.AbstractRuDokAction;
import gui.components.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class StrokeAction extends AbstractRuDokAction {

    private Float broj = 2f;
    private int odgovor;

    public StrokeAction() {
        putValue(SMALL_ICON, loadIcon("images/stroke.png"));
        putValue(SHORT_DESCRIPTION, "Change stroke");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String odg = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter the width of stroke(int)");
        if(odg!=null)
            broj = Float.valueOf(odg);

        Object value = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Do you want your stroke to be dashed?",
                "Type of stroke", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, JOptionPane.NO_OPTION);

        if(value.equals(JOptionPane.NO_OPTION)){
            odgovor = 1;
        } else {
            odgovor = 0;
        }
    }

    public Float getBroj() {
        return broj;
    }

    public int getOdgovor() {
        return odgovor;
    }
}
