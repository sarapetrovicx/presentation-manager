package controller.actions;

import command.AddDeviceCommand;
import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Workspace;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class SharingPresentationAction extends AbstractRuDokAction {

    public SharingPresentationAction() {
        putValue(SMALL_ICON, loadIcon("images/share.png"));
        putValue(NAME, "Share");
        putValue(SHORT_DESCRIPTION, "Share");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode selected = (MyTreeNode) MainFrame.getInstance().getJTree().getLastSelectedPathComponent();

        if (selected == null) {
            ErrorFactory.getInstance().generateError(Error.NONE_SELECTED);
            return;
        }
        RuNode selectedPres = selected.getRuNode();
        Workspace workspace = MainFrame.getInstance().getWorkspace();

        if (!(selectedPres instanceof Prezentacija)) {
            ErrorFactory.getInstance().generateError(Error.AUTHOR);
            return;
        }
        Prezentacija prezentacija = (Prezentacija) selectedPres;

        String ime = JOptionPane.showInputDialog(MainFrame.getInstance(), "Enter the name of project");

        if (ime == null) {
            ErrorFactory.getInstance().generateError(Error.EMPTY);
            return;
        }

        for (RuNode projekat : workspace.getChildren()) {
            if (projekat instanceof Projekat && projekat.getName().equalsIgnoreCase(ime)) {
                MyTreeNode myTreeNode = new MyTreeNode(selectedPres);
                MyTreeNode projTreeNode = new MyTreeNode(projekat);
//                projTreeNode.addChild(myTreeNode);
                //((Projekat) projekat).addChild(myTreeNode.getRuNode());

                MainFrame.getInstance().getCommandManager().
                        addCommand(new AddDeviceCommand(myTreeNode, myTreeNode.getRuNode(), projTreeNode));
                prezentacija.addClone((Projekat) projekat);


                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
            }
        }
    }
}
