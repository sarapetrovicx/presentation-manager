package controller.actions;

import controller.AbstractRuDokAction;
import gui.components.MainFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SelectColorAction extends AbstractRuDokAction {

    Color color = Color.BLACK;

    public SelectColorAction() {
        putValue(SMALL_ICON, loadIcon("images/colors.png"));
        putValue(SHORT_DESCRIPTION, "Change color");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Color initialcolor = Color.BLACK;
        color = JColorChooser.showDialog(MainFrame.getInstance().getDesniPanel(), "Select a color", initialcolor);
    }

    public Color getColor() {
        return color;
    }
}
