package controller.actions;

import gui.slots.model.Slot;
import gui.tree.view.PrezentacijaView;
import gui.tree.view.SlajdView;
import model.workspace.Prezentacija;
import model.workspace.Slajd;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MyMouseController extends MouseAdapter {

    private Slajd slajd;

    public MyMouseController(Slajd slajd) {
        this.slajd = slajd;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point point = e.getPoint();

        Prezentacija prezentacija = (Prezentacija) slajd.getParent();
        PrezentacijaView prezentacijaView = (PrezentacijaView) prezentacija.getSubscriber();

        prezentacijaView.myMousePressed(slajd, point);

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point point = e.getPoint();
        Prezentacija prezentacija = (Prezentacija) slajd.getParent();
        PrezentacijaView prezentacijaView = (PrezentacijaView) prezentacija.getSubscriber();
        prezentacijaView.myMouseDragged(point);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Prezentacija prezentacija = (Prezentacija) slajd.getParent();
        PrezentacijaView prezentacijaView = (PrezentacijaView) prezentacija.getSubscriber();
        prezentacijaView.myMouseReleased();
    }
}
