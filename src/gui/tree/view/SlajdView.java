package gui.tree.view;

import gui.components.ImagePanel;
import gui.slots.handler.MultimediaHandler;
import gui.slots.handler.TextHandler;
import gui.slots.model.Slot;
import gui.slots.model.SlotType;
import gui.slots.view.SlotView;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;
import model.workspace.Prezentacija;
import model.workspace.Slajd;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class SlajdView extends ImagePanel implements ISubscriber {

    private Slajd slajd;
    private String url;
    private JLabel redniBrojlbl;
    private JLabel autor;
    private Prezentacija prezentacija;
    //private ImagePanel imagePanel;
    private ArrayList<SlotView> slotViews = new ArrayList<>();

    public SlajdView(Slajd slajd, String url) {
        super(url);
        this.slajd = slajd;
        this.slajd.addSubscriber(this);
        this.url = url;
        this.prezentacija = (Prezentacija)slajd.getParent();
        setBackground(Color.gray);

        redniBrojlbl = new JLabel(String.valueOf(slajd.getRedniBroj()));

        autor = new JLabel(prezentacija.getAutor());
        add(autor);
        add(redniBrojlbl);
    }

    @Override
    public void update(Object notification, Message message) {
        if(message.equals(Message.OTHER)) {
            if (notification instanceof Prezentacija) {
                autor.setText(((Prezentacija) notification).getAutor());
                url = ((Prezentacija) notification).getURL();
                File file = new File(url);
                try {
                    super.setImg(ImageIO.read(file));
                    //repaint();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                updateUI();
            } else if (notification instanceof Slot) {
                SlotView slotView = new SlotView((Slot) notification);
                slotViews.add(slotView);
            }
        } else if(message.equals(Message.DELETE)){
            ArrayList<SlotView> toRemove = new ArrayList<>();
            //da bih izbegla brisanje u iteraciji i exception
            if(notification instanceof Slot){
                for(SlotView slotView : slotViews){
                    if(slotView.getSlot().equals(notification)){
                        toRemove.add(slotView);
                    }
                }
                slotViews.removeAll(toRemove);
                repaint();
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
//      super.paintComponent(g);
//      super.repaint();
        Image img = super.getImg();
        g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                (int)(this.getSize().getHeight()-img.getHeight(null))/2, null);
        Graphics2D g2 = (Graphics2D)g;
        updateSlots();
        for(SlotView slot : slotViews){
            slot.paint(g2, slot.getSlot(), prezentacija);
        }
    }

    public void updateSlots(){
        List<Slot> slots2 = slajd.getSlots();
        for(Slot slot : slots2){
            SlotView slotView = new SlotView(slot);
            if(slot.getType().equals(SlotType.IMAGE)){
                slotView.setSlotHandler(new MultimediaHandler());
            } else {
                slotView.setSlotHandler(new TextHandler());
            }
            if(!slotViews.contains(slotView)){
                slotViews.add(slotView);
            }
        }
    }

    public void setAutor(String autor) {
        this.autor.setText(autor);
    }

    public void setUrl(String url) {
        File file = new File(url);
        try {
            super.setImg(ImageIO.read(file));
        } catch (Exception e) {
            e.printStackTrace();
        }
        updateUI();
    }

}
