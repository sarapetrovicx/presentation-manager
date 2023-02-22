package gui.components;

import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@Getter
@Setter

public class ImagePopUp extends JDialog {

    private JButton choose;
    private JLabel label;
    private JFileChooser fileChooser;
    private String url;

    public ImagePopUp(JFrame parent, String title, boolean modal) {
        setSize(200, 100);
        setTitle(title);
        setLayout(new BorderLayout());
        setLocationRelativeTo(parent);

        label = new JLabel("Choose background of slide");
        choose = new JButton("Choose");
        fileChooser = new JFileChooser();

        label.setHorizontalAlignment(SwingConstants.CENTER);

        choose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(fileChooser);
//                url = null;
                try {
                    url = fileChooser.getSelectedFile().toString();
                    System.out.println(url);
                } catch (Exception exception){
                    exception.printStackTrace();
                }
//                FileNameExtensionFilter nameExtensionFilter = new FileNameExtensionFilter("null", "jpg", "png", "gif");
//                fileChooser.setFileFilter(nameExtensionFilter);
                setVisible(false);
            }
        });
        add(label, BorderLayout.CENTER);
        add(choose, BorderLayout.SOUTH);
        setVisible(true);
    }
}
