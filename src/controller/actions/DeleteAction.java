package controller.actions;

import command.DeleteDeviceCommand;
import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.composite.RuNode;
import model.workspace.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteAction extends AbstractRuDokAction {

    public DeleteAction() {
        putValue(SMALL_ICON, loadIcon("images/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode object = (MyTreeNode) MainFrame.getInstance().getJTree().getLastSelectedPathComponent();
        if(object == null){
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
            return;
        }
        RuNode node = object.getRuNode();

        if(node instanceof Workspace){
            ErrorFactory.getInstance().generateError(Error.DELETE_WORKSPACE);
            return;

        } else {
//            RuNodeComposite parent = (RuNodeComposite) node.getParent();
            MainFrame.getInstance().getCommandManager().addCommand(new DeleteDeviceCommand(object, node));
        }
        object.removeFromParent();
        SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());

    }

}
