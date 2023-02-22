package controller.actions;

import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import lombok.Getter;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@Getter

public class ImageAction extends AbstractRuDokAction {

    private RuNode node;

    public ImageAction() {
        putValue(SMALL_ICON, loadIcon("images/image.png"));
        putValue(NAME, "Background");
        putValue(SHORT_DESCRIPTION, "Background");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
    }
    @Override
    public void actionPerformed(ActionEvent e){

        Object prez = MainFrame.getInstance().getJTree().getLastSelectedPathComponent();

        if(prez == null){
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
        } else if (prez instanceof MyTreeNode) {
            node = ((MyTreeNode) prez).getRuNode();
            if(!(node instanceof Prezentacija)){
                ErrorFactory.getInstance().generateError(Error.AUTHOR);
            } else {
                JFileChooser jFileChooser = new JFileChooser();
                String url = "";

                jFileChooser.showOpenDialog(jFileChooser);

                try {
                    url = jFileChooser.getSelectedFile().toString();
                } catch (Exception exception){
                    return;
                }
                ((Prezentacija) node).setURL(url);
            }
        }
    }
}
