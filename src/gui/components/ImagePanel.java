package gui.components;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import java.awt.*;

@Getter
@Setter

public class ImagePanel extends JPanel {

    private Image img;

    public ImagePanel(String img) {
        this(new ImageIcon(img).getImage());
    }

    public ImagePanel(Image img) {
        this.img = img;
    }
    public void paintComponent(Graphics g) {
        g.drawImage(img, (int)(this.getSize().getWidth()-img.getWidth(null))/2,
                (int)(this.getSize().getHeight()-img.getHeight(null))/2, null);
    }

    @Override
    public Dimension getPreferredSize() {
        return img == null ? new Dimension(200, 200) :
                new Dimension(img.getWidth(this), img.getHeight(this));
    }

    public void setSmallerImage() {
        this.img = img.getScaledInstance(170,100,Image.SCALE_DEFAULT);
    }
}
