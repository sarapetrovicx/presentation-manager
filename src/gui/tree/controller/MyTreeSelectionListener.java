package gui.tree.controller;



import lombok.Builder;
import model.workspace.Slajd;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MyTreeSelectionListener implements TreeSelectionListener {
    @Override
    public void valueChanged(TreeSelectionEvent e) {
//        TreePath path = e.getPath();
//        for (int i = 0; i < path.getPathCount(); i++) {
//            if (path.getPathComponent(i) instanceof Slajd) {
//                Slajd d = (Slajd) path.getPathComponent(i);
//
//                System.out.println("Selektovan dijagram:" + d);
//
//                System.out.println("getPath: " + e.getPath());
//                System.out.println("getPath: " + e.getNewLeadSelectionPath());
//                break;
//            }
//        }
    }
}

