package gui.slots.model;

import lombok.Getter;
import lombok.Setter;
import serializable.SerializableStrokeAdapter;

import java.awt.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter

public class Slot implements Serializable {

    private Dimension size;
    private Point point;
    private Color color;
    private SerializableStrokeAdapter stroke;
    private boolean selected;
    private SlotType type;
    private String content;

    public Slot(Dimension size, Point point, Color color, Stroke stroke) {
        setStroke(stroke);
        this.size = size;
        this.point = point;
        this.color = color;
        this.type = SlotType.TEXT;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Slot)) return false;
        Slot slot = (Slot) o;
        return Objects.equals(size, slot.size) && Objects.equals(point, slot.point) && Objects.equals(color, slot.color) && Objects.equals(stroke, slot.stroke);
    }

    public void setStroke(Stroke stroke) {
        this.stroke = new SerializableStrokeAdapter(stroke);
    }
}
