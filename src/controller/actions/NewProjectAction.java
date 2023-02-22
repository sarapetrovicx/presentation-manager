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
import model.workspace.Workspace;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewProjectAction extends AbstractRuDokAction {

    public NewProjectAction() {
        putValue(SMALL_ICON, loadIcon("images/project.png"));
        putValue(NAME, "New project");
        putValue(SHORT_DESCRIPTION, "New project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {


        MyTreeNode node = (MyTreeNode) MainFrame.getInstance().getJTree().getLastSelectedPathComponent();

        if(node == null){
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
            return;
        }
        RuNode parent = node.getRuNode();

        if(!(parent instanceof Workspace)){
            ErrorFactory.getInstance().generateError(Error.WRONG_TYPE);
            return;
        }

        NodeFactory nodeFactory = FactoryGenerator.returnNodeFactory(node);
        if (nodeFactory != null) {
            MyTreeNode child = nodeFactory.createTreeNode(node);
            MainFrame.getInstance().getCommandManager().addCommand(new AddDeviceCommand(child, child.getRuNode(), node));
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
        }
    }
}
