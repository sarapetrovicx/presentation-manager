package gui.components;

import javax.swing.*;
import java.awt.*;


public class InfoDialog extends JDialog {

    private JLabel jLabel1;
    private JLabel jLabel2;

    public InfoDialog() {
        super(MainFrame.getInstance(), "Info", true);
        setSize(400, 500);
        setLocationRelativeTo(MainFrame.getInstance());
        setLayout(new BorderLayout());

        jLabel1 = new JLabel("Sara Petrović 45/20RN");
        jLabel1.setHorizontalAlignment(SwingConstants.CENTER);
        jLabel2 = new JLabel(new ImageIcon("src/controller/actions/images/myPicture.jpeg"));
        jLabel2.setHorizontalAlignment(SwingConstants.CENTER);

        add(jLabel1, BorderLayout.NORTH);
        add(jLabel2,BorderLayout.CENTER);
    }

    public void setDialogVisible(){
        this.setVisible(true);
    }
}
