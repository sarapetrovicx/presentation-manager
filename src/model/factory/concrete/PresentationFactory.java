package model.factory.concrete;

import model.factory.NodeFactory;
import model.composite.RuNode;
import model.composite.RuNodeComposite;
import model.workspace.Prezentacija;

public class PresentationFactory extends NodeFactory {
    @Override
    public RuNode createNode(RuNode parent) {

            String name = "Presentation " + (((RuNodeComposite) parent).getChildren().size() + 1);
            return new Prezentacija(name, parent, "None", "src/controller/actions/images/images.jfif");
        }
}
