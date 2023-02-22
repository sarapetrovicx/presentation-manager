package model.factory.concrete;

import model.factory.NodeFactory;
import model.composite.RuNode;
import model.composite.RuNodeComposite;
import model.workspace.Slajd;

public class SlideFactory extends NodeFactory {
    @Override
    public RuNode createNode(RuNode parent) {
        String name = "Slide " + (((RuNodeComposite)parent).getChildren().size() + 1);
        Slajd slajd = new Slajd(name, parent);
        slajd.setRedniBroj((((RuNodeComposite)parent).getChildren().size() + 1));

        for(int i = 0; i < ((RuNodeComposite) parent).getChildren().size(); i++){
            if(slajd.getRedniBroj()  == ((Slajd) ((RuNodeComposite) parent).getChildren().get(i)).getRedniBroj()){
                slajd.setRedniBroj(i+1);
                slajd.setName("Slide " + (i+1));
            }
        }
        return slajd;
    }
}
