package gui.slots.handler;

import gui.components.ImagePanel;
import gui.slots.model.Slot;

import java.awt.*;

public class MultimediaHandler implements SlotHandler{
    @Override
    public Object readContent() {
        return null;
    }

    @Override
    public void setContent() {

    }

    @Override
    public void paint(Graphics2D g, Slot slot) {
        ImagePanel imagePanel = new ImagePanel(slot.getContent());
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
        g.drawImage(imagePanel.getImg(), slot.getPoint().x, slot.getPoint().y, null);
    }

    @Override
    public void format() {

    }
}
