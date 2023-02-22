package controller.actions;

import controller.AbstractRuDokAction;
import controller.actions.error.Error;
import controller.actions.error.ErrorFactory;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.workspace.Projekat;
import serializable.MyFileFilter;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class SaveProjectAction extends AbstractRuDokAction {

    private MyTreeNode selected;

    public SaveProjectAction() {
        putValue(SMALL_ICON, loadIcon("images/save.png"));
        putValue(NAME, "Save project");
        putValue(SHORT_DESCRIPTION, "Save Project");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        selected = (MyTreeNode) MainFrame.getInstance().getJTree().getLastSelectedPathComponent();
        if(!(selected.getRuNode() instanceof Projekat)){
            ErrorFactory.getInstance().generateError(Error.PROJECT);
            return;
        }
        JFileChooser jfc = new JFileChooser();
        jfc.setFileFilter(new MyFileFilter());

        Projekat projekat = MainFrame.getInstance().getProjekatView().getProjekat();

        File projectFile = projekat.getProjectFile();
        if (!projekat.isChanged()){
            return;
        }

        if (projekat.getProjectFile()==null){
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                projectFile = jfc.getSelectedFile();

            }else{
                return;
            }

        }


        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(new FileOutputStream(projectFile));
            os.writeObject(projekat);
            projekat.setProjectFile(projectFile);
            projekat.setChanged(false);
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public MyTreeNode getSelected() {
        return selected;
    }

    public void setSelected(MyTreeNode selected) {
        this.selected = selected;
    }
}
