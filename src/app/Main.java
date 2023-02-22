package app;

import gui.components.MainFrame;

public class Main {

    public static void main(String[] args) {
        MainFrame mainFrame = MainFrame.getInstance();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
}
