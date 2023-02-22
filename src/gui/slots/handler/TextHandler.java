package gui.slots.handler;

import gui.slots.model.Slot;

import java.awt.*;
import java.text.AttributedString;

public class TextHandler implements SlotHandler{
    @Override
    public Object readContent() {
        return null;
    }

    @Override
    public void setContent() {

    }

    @Override
    public void paint(Graphics2D g, Slot slot) {
        AttributedString attributedString = new AttributedString(slot.getContent());


        g.drawString(slot.getContent(), slot.getPoint().x, slot.getPoint().y);

    }

    @Override
    public void format() {

    }
}
