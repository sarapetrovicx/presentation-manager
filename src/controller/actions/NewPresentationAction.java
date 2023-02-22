package controller.actions;

import command.AddDeviceCommand;
import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import model.factory.FactoryGenerator;
import model.factory.NodeFactory;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.composite.RuNode;
import model.workspace.Projekat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewPresentationAction extends AbstractRuDokAction {

    public NewPresentationAction() {
        putValue(SMALL_ICON, loadIcon("images/presentation.png"));
        putValue(NAME, "New presentation");
        putValue(SHORT_DESCRIPTION, "New presentation");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        MyTreeNode node = (MyTreeNode) MainFrame.getInstance().getJTree().getLastSelectedPathComponent();

        if(node == null){
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
            return;
        }

        RuNode parent = node.getRuNode();

        if(!(parent instanceof Projekat)){
                ErrorFactory.getInstance().generateError(Error.WRONG_TYPE);
                return;
        }

        NodeFactory nodeFactory = FactoryGenerator.returnNodeFactory(node);
        if (nodeFactory != null) {
            MyTreeNode child = nodeFactory.createTreeNode(node);
//            node.addChild(child);
            MainFrame.getInstance().getCommandManager().addCommand(new AddDeviceCommand(child, child.getRuNode(), node));
//            child.setParent(node);
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
        }
    }
}
