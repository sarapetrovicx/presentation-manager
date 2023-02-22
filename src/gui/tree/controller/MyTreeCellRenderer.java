package gui.tree.controller;

import gui.tree.model.MyTreeNode;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Slajd;
import model.workspace.Workspace;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer {

    public MyTreeCellRenderer() {
    }

    public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean sel,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);

        if (value instanceof MyTreeNode) {
            RuNode value2 = ((MyTreeNode) value).getRuNode();

            if (value2 instanceof Slajd) {
                URL imageURL = getClass().getResource("images2/slide3.png");
                ImageIcon icon = null;
                if (imageURL != null)
                    icon = new ImageIcon(imageURL);
                setIcon(icon);

            } else if (value2 instanceof Prezentacija) {
                URL imageURL = getClass().getResource("images2/presentation2.png");
                ImageIcon icon = null;
                if (imageURL != null)
                    icon = new ImageIcon(imageURL);
                setIcon(icon);

            } else if (value2 instanceof Projekat) {
                URL imageURL = getClass().getResource("images2/project2.png");
                ImageIcon icon = null;
                if (imageURL != null)
                    icon = new ImageIcon(imageURL);
                setIcon(icon);
            } else if (value2 instanceof Workspace) {
                URL imageURL = getClass().getResource("images2/workspace1.png");
                ImageIcon icon = null;
                if (imageURL != null)
                    icon = new ImageIcon(imageURL);
                setIcon(icon);
            }
        }
        return this;
    }
}
