package gui.tree.controller;

import command.EditDeviceCommand;
import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import model.composite.RuNode;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class MyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {

    private Object stavka=null;
    private JTextField edit=null;

    public MyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }
    public Component getTreeCellEditorComponent(JTree arg0, Object arg1, boolean arg2, boolean arg3, boolean arg4, int arg5) {

        //super.getTreeCellEditorComponent(arg0,arg1,arg2,arg3,arg4,arg5);
        stavka=arg1;

        edit=new JTextField(arg1.toString());
        edit.addActionListener(this);
        return edit;
    }


    public boolean isCellEditable(EventObject arg0) {
        if (arg0 instanceof MouseEvent)
            if (((MouseEvent)arg0).getClickCount()==3){
                return true;
            }
        return false;
    }


    public void actionPerformed(ActionEvent e) {
        if (stavka instanceof MyTreeNode) {
            RuNode node = ((MyTreeNode) stavka).getRuNode();
            MainFrame.getInstance().getCommandManager().addCommand(new EditDeviceCommand(node, e.getActionCommand(), 2));
//            node.setName(e.getActionCommand());
            SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
        }
    }
}
