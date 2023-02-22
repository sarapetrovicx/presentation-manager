package gui.slots.handler;

import gui.slots.model.Slot;

import java.awt.*;

public interface SlotHandler {
    Object readContent();
    void setContent();
    void paint(Graphics2D g, Slot slot);
    void format();
}
