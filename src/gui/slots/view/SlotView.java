package gui.slots.view;

import gui.slots.handler.SlotHandler;
import gui.slots.model.Slot;
import gui.state.concrete.SlideShowState;
import gui.tree.view.PrezentacijaView;
import lombok.Getter;
import lombok.Setter;
import model.workspace.Prezentacija;

import java.awt.*;
import java.util.Objects;

@Getter
@Setter

public class SlotView {

    private Slot slot;
    private SlotHandler slotHandler;

    public SlotView(Slot slot) {
        this.slot = slot;
    }

    public void paint(Graphics2D g, Slot slot, Prezentacija prezentacija){
        if(slot.getContent() != null && ((PrezentacijaView)prezentacija.getSubscriber()).
                getStateManager().getCurrent() instanceof SlideShowState) {
            slotHandler.paint(g, slot);
        } else {
            g.setPaint(slot.getColor());
            g.setStroke(slot.getStroke());
            g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
            g.fillRect(slot.getPoint().x, slot.getPoint().y, slot.getSize().width, slot.getSize().height);
            g.drawRect(slot.getPoint().x, slot.getPoint().y, slot.getSize().width, slot.getSize().height);
        }
    }
    public boolean elementAt(Point point){
        return point.x >= slot.getPoint().x && point.x <= slot.getPoint().x + slot.getSize().width &&
                point.y >= slot.getPoint().y && point.y <= slot.getPoint().y + slot.getSize().height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SlotView)) return false;
        SlotView slotView = (SlotView) o;
        return Objects.equals(slot, slotView.slot);
    }

}
