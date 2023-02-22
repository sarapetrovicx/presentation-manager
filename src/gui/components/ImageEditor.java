package gui.components;

import gui.slots.model.Slot;

import javax.swing.*;
import java.awt.*;

public class ImageEditor extends JDialog {

    private JPanel jPanel;
    private JToolBar jToolBar;
    private ImagePanel imagePanel;
    private String url;
    private JButton open;
    private JButton save;
    private Slot slot;

    public ImageEditor(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
        jPanel = new JPanel();
        jToolBar = new JToolBar(JToolBar.HORIZONTAL);
        slot = MainFrame.getInstance().getSelectedSlot();
        open = new JButton("Open");
        save = new JButton("Save");

        if(slot.getContent() == null) {
            imagePanel = new ImagePanel("");
        } else {
            imagePanel = new ImagePanel(slot.getContent());
        }

        open.addActionListener(e -> {
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.showOpenDialog(jFileChooser);
            try {
                url = jFileChooser.getSelectedFile().toString();
            } catch (Exception exception) {
                return;
            }
            imagePanel = new ImagePanel(url);
            jPanel.removeAll();
            jPanel.add(imagePanel);
            jPanel.updateUI();
        });

        save.addActionListener(e -> {
            slot.setContent(url);
            setVisible(false);
        });

        jToolBar.add(open);
        jToolBar.add(Box.createHorizontalGlue());
        jToolBar.add(save);

        jPanel.setBackground(Color.DARK_GRAY);
        jPanel.add(imagePanel);
        add(jPanel, BorderLayout.CENTER);
        add(jToolBar, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setSize(300, 300);
    }
}
