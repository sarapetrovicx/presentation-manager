package model.factory;

import model.factory.concrete.PresentationFactory;
import model.factory.concrete.ProjectFactory;
import model.factory.concrete.SlideFactory;
import gui.tree.model.MyTreeNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Workspace;

public class FactoryGenerator {

    private static ProjectFactory projectFactory = new ProjectFactory();
    private static PresentationFactory presentationFactory = new PresentationFactory();
    private static SlideFactory slideFactory = new SlideFactory();

    public static NodeFactory returnNodeFactory(MyTreeNode myTreeNode){
        if(myTreeNode.getRuNode() instanceof Workspace){
            return projectFactory;
        } else if(myTreeNode.getRuNode() instanceof Projekat){
            return presentationFactory;
        } else if(myTreeNode.getRuNode() instanceof Prezentacija){
            return slideFactory;
        }
        return  null;
    }
}
