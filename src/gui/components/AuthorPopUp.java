package gui.components;

import javax.swing.*;

public class AuthorPopUp extends JOptionPane {

    String autor;

    public AuthorPopUp() {
        autor = showInputDialog(MainFrame.getInstance(), "Enter autor");
    }

    public String getAutor() {
        return autor;
    }
}

