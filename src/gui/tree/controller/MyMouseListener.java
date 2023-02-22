package gui.tree.controller;

import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MyMouseListener implements MouseListener {
    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount()==1 || e.getClickCount() == 2){
            Object o = MainFrame.getInstance().getJTree().getLastSelectedPathComponent();
            if(o instanceof MyTreeNode){
                RuNode node = ((MyTreeNode) o).getRuNode();
                if(node instanceof Projekat) {
                    MainFrame.getInstance().getProjekatView().setProjekat((Projekat) node);
                } else if(node instanceof Prezentacija){
                    MainFrame.getInstance().getProjekatView().getJTabbedPane().
                            setSelectedIndex(MainFrame.getInstance().getProjekatView().getJTabbedPane().indexOfTab(node.getName()));
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
