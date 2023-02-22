package controller.actions;

import command.EditDeviceCommand;
import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.AuthorPopUp;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import lombok.Getter;
import model.composite.RuNode;
import model.workspace.Prezentacija;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@Getter

public class AuthorAction extends AbstractRuDokAction {

    private AuthorPopUp authorPopUp;

    public AuthorAction() {
        putValue(SMALL_ICON, loadIcon("images/author.png"));
        putValue(NAME, "Author");
        putValue(SHORT_DESCRIPTION, "Author");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object prez = MainFrame.getInstance().getJTree().getLastSelectedPathComponent();

        if(prez == null){
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
            return;
        }

        authorPopUp = new AuthorPopUp();
        String name = authorPopUp.getAutor();

        if(name == null){
            ErrorFactory.getInstance().generateError(Error.EMPTY);
            return;
        }

        if (prez instanceof MyTreeNode) {
            RuNode node = ((MyTreeNode) prez).getRuNode();
            if(node instanceof Prezentacija){
                MainFrame.getInstance().getCommandManager().addCommand(new EditDeviceCommand(node, name, 1));
//                ((Prezentacija)node).setAutor(name);
                MainFrame.getInstance().getDesniPanel().updateUI();
            } else {
                ErrorFactory.getInstance().generateError(Error.AUTHOR);
            }
        }
    }

}

