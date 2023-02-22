package gui.tree.view;

import gui.tree.controller.MyMouseListener;
import gui.tree.controller.MyTreeCellEditor;
import gui.tree.controller.MyTreeCellRenderer;
import gui.tree.controller.MyTreeSelectionListener;
import gui.tree.model.MyTreeNode;


import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

public class MyJTree extends JTree {

    public MyJTree(DefaultTreeModel model) {
        super(model);
        addTreeSelectionListener(new MyTreeSelectionListener());
        setCellEditor(new MyTreeCellEditor(this, new DefaultTreeCellRenderer()));
        setCellRenderer(new MyTreeCellRenderer());
        addMouseListener(new MyMouseListener());
        setEditable(true);
    }

    public void addChild(MyTreeNode treeNode){
        ((MyTreeNode)getModel().getRoot()).addChild(treeNode);
        SwingUtilities.updateComponentTreeUI(this);
    }


}
