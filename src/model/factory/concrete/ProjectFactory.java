package model.factory.concrete;

import model.factory.NodeFactory;
import model.composite.RuNode;
import model.composite.RuNodeComposite;
import model.workspace.Projekat;

public class ProjectFactory extends NodeFactory {
    @Override
    public RuNode createNode(RuNode parent) {
        String name = "Project " + (((RuNodeComposite)parent).getChildren().size() + 1);
        for(int i = 0; i < ((RuNodeComposite) parent).getChildren().size(); i++){
            if(name.equals(((RuNodeComposite) parent).getChildren().get(i).getName())){
                name = "Project " + (i+1);
            }
        }
        return new Projekat(name, parent);
    }
}
