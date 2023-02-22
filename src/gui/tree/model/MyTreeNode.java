package gui.tree.model;

import lombok.Getter;
import lombok.Setter;
import model.composite.RuNode;
import model.composite.RuNodeComposite;
import model.workspace.Slajd;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreeNode;
import java.util.Enumeration;


@Getter
@Setter

public class MyTreeNode implements MutableTreeNode {

    private RuNode ruNode;

    public MyTreeNode(RuNode ruNode) {
        this.ruNode = ruNode;
    }

    @Override
    public void insert(MutableTreeNode child, int index) {
        if(ruNode instanceof RuNodeComposite){
                ((RuNodeComposite) ruNode).getChildren().add(index, (RuNode) child);
        }
    }

    @Override
    public void remove(int index) {
//        if(ruNode instanceof RuNodeComposite){
//            ((RuNodeComposite) ruNode).getChildren().remove(index);
//        }
    }

    @Override
    public void remove(MutableTreeNode node) {
        if(ruNode instanceof RuNodeComposite){
            MyTreeNode myTreeNode = (MyTreeNode)node;
            ((RuNodeComposite) ruNode).removeChild(myTreeNode.getRuNode());
        }
    }

    @Override
    public void setUserObject(Object object) {
    }

    @Override
    public void removeFromParent() {
        RuNode parent = ruNode.getParent();
        if(parent instanceof RuNodeComposite)
            ((RuNodeComposite)parent).removeChild(ruNode);
    }

    @Override
    public void setParent(MutableTreeNode newParent) {
        RuNode parent = ((MyTreeNode)newParent).getRuNode();
        ruNode.setParent(parent);
    }

    @Override
    public TreeNode getChildAt(int childIndex) {
        if(ruNode instanceof RuNodeComposite){
            RuNode ruNode1 = ((RuNodeComposite) ruNode).getChildren().get(childIndex);
            MyTreeNode myTreeNode = new MyTreeNode(ruNode1);
            return myTreeNode;
        }
        return null;
    }

    @Override
    public int getChildCount() {
        if(ruNode instanceof RuNodeComposite){
            return ((RuNodeComposite) ruNode).getChildren().size();
       } else
            return 0;
    }

    @Override
    public TreeNode getParent() {
        return new MyTreeNode(ruNode.getParent());
    }

    @Override
    public int getIndex(TreeNode node) {
        if(ruNode instanceof RuNodeComposite) {
            MyTreeNode myTreeNode = new MyTreeNode(ruNode);
            return ((RuNodeComposite) myTreeNode.getRuNode()).getChildren().indexOf(myTreeNode.getRuNode());
        }
        return -1;
    }

    @Override
    public boolean getAllowsChildren() {
        return ruNode instanceof RuNodeComposite;
    }

    @Override
    public boolean isLeaf() {
        return ruNode instanceof Slajd;
    }

    @Override
    public Enumeration<? extends TreeNode> children() {
        if(ruNode instanceof RuNodeComposite)
            return (Enumeration<? extends TreeNode>)((RuNodeComposite) ruNode).getChildren();
        return null;
    }

    public void addChild(MyTreeNode node){
        if(ruNode instanceof RuNodeComposite){
            ((RuNodeComposite)ruNode).addChild(node.getRuNode());
        }
    }

    public RuNode getRuNode() {
        return ruNode;
    }

    @Override
    public String toString() {
       return ruNode.toString();
    }
}
