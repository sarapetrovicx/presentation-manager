package controller.actions;

import command.AddDeviceCommand;
import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import gui.tree.observer.Message;
import gui.tree.view.PrezentacijaView;
import gui.tree.view.ProjekatView;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Workspace;
import serializable.MyFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class OpenProjectAction extends AbstractRuDokAction {
    public OpenProjectAction() {
        putValue(SMALL_ICON, loadIcon("images/open.png"));
        putValue(NAME, "Open project");
        putValue(SHORT_DESCRIPTION, "Open Project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MyTreeNode selected = (MyTreeNode) MainFrame.getInstance().getJTree().getLastSelectedPathComponent();
        if(!(selected.getRuNode() instanceof Workspace)){
            ErrorFactory.getInstance().generateError(Error.WRONG_TYPE);
            return;
        }

        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new MyFileFilter());

        if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            try {
                ObjectInputStream os = new ObjectInputStream(new FileInputStream(jfc.getSelectedFile()));

                Projekat projekat = null;
                try {
                    projekat = (Projekat) os.readObject();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }

                if (projekat != null) {
                    MyTreeNode proj = new MyTreeNode(projekat);
                    MainFrame.getInstance().getWorkspace().addChild(projekat);
                    MainFrame.getInstance().getCommandManager().addCommand(new AddDeviceCommand(proj, projekat, selected));

                    ProjekatView projekatView = new ProjekatView(projekat);
                    projekat.addSubscriber(projekatView);

                    ArrayList<RuNode> prezentacije = projekat.getChildren();

                    for (RuNode ruNode : prezentacije) {
                        Prezentacija prezentacija = (Prezentacija) ruNode;

                        PrezentacijaView prezentacijaView = new PrezentacijaView(prezentacija);
                        prezentacija.addSubscriber(prezentacijaView);
                        List<RuNode> slajdovi = prezentacija.getChildren();
                        for (RuNode slajd : slajdovi) {
                            prezentacijaView.update(slajd, Message.OTHER);
                        }
                        prezentacijaView.update(prezentacija, Message.OTHER);
                    }
                }

                } catch(FileNotFoundException e1){
                    e1.printStackTrace();
                } catch(IOException e1){
                    e1.printStackTrace();
                }
        }
    }
}
