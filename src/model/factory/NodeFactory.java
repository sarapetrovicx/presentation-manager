package model.factory;

import gui.tree.model.MyTreeNode;
import model.composite.RuNode;

public abstract class NodeFactory {

    public MyTreeNode createTreeNode(MyTreeNode myTreeNode){
        RuNode ruNode = createNode(myTreeNode.getRuNode());
        return new MyTreeNode(ruNode);
    }

    public abstract RuNode createNode(RuNode parent);
}
