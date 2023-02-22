package controller;

import gui.components.MainFrame;
import gui.tree.model.MyTreeNode;
import gui.tree.observer.Message;
import gui.tree.view.PrezentacijaView;
import gui.tree.view.ProjekatView;
import lombok.SneakyThrows;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Workspace;
import serializable.MyFileFilter;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyWindowListener extends WindowAdapter {

    @Override
    public void windowOpened(WindowEvent e) {
        int value = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Do you want to open other workspace?",
                "Open workspace", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, null, JOptionPane.NO_OPTION);

        //0 - YES
        //1 - NO
        if(value == 0) {
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.isDirectory() ||
                            f.getName().toLowerCase().endsWith(".txt"));
                }

                @Override
                public String getDescription() {
                    return "Text files (*.txt)";
                }
            });

            if (jfc.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
                Scanner scanner = null;
                try {
                    scanner = new Scanner(jfc.getSelectedFile());
                } catch (FileNotFoundException fileNotFoundException) {
                    fileNotFoundException.printStackTrace();
                }
                if (scanner != null) {
                    while (scanner.hasNextLine()) {
                        try {
                            ObjectInputStream os = new ObjectInputStream(new FileInputStream(scanner.nextLine()));
                            Projekat projekat = null;
                            try {
                                projekat = (Projekat) os.readObject();
                            } catch (ClassNotFoundException ex) {
                                ex.printStackTrace();
                            }
                            if (projekat != null) {
                                System.out.println("uslo");

    //                            MyTreeNode proj = new MyTreeNode(projekat);
                                MainFrame.getInstance().getWorkspace().addChild(projekat);
    //                            MyTreeNode ws = new MyTreeNode(MainFrame.getInstance().getWorkspace());
    //                            ws.addChild(proj);
    //                            MainFrame.getInstance().getCommandManager().addCommand(new AddDeviceCommand(proj, projekat, selected));

                                ProjekatView projekatView = new ProjekatView(projekat);
                                projekat.addSubscriber(projekatView);

                                ArrayList<RuNode> prezentacije = projekat.getChildren();

                                for (RuNode ruNode : prezentacije) {
                                    Prezentacija prezentacija = (Prezentacija) ruNode;

                                    PrezentacijaView prezentacijaView = new PrezentacijaView(prezentacija);
                                    prezentacija.addSubscriber(prezentacijaView);
                                    List<RuNode> slajdovi = prezentacija.getChildren();
                                    for (RuNode slajd : slajdovi) {
                                        prezentacijaView.update(slajd, Message.OTHER);
                                    }
                                    prezentacijaView.update(prezentacija, Message.OTHER);
                                }

                            }
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                }
                SwingUtilities.updateComponentTreeUI(MainFrame.getInstance().getJTree());
                if (scanner != null) {
                    scanner.close();
                }
            }
        }

    }

    @SneakyThrows
    @Override
    public void windowClosing(WindowEvent e) {
        int value = JOptionPane.showOptionDialog(MainFrame.getInstance(), "Do you want to save this workspace?",
                "Save workspace", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, null, JOptionPane.NO_OPTION);


        if(value == 0){
            JFileChooser jfc = new JFileChooser();
            jfc.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    return (f.isDirectory() ||
                            f.getName().toLowerCase().endsWith(".txt"));
                }

                @Override
                public String getDescription() {
                    return "Text files (*.txt)";
                }
            });

            Workspace ws = MainFrame.getInstance().getWorkspace();
            File file;
            if(jfc.showSaveDialog(MainFrame.getInstance())==JFileChooser.APPROVE_OPTION){
                file = jfc.getSelectedFile();
            } else {
                return;
            }
            PrintWriter printWriter = new PrintWriter(file);
            for(RuNode ruNode : ws.getChildren()){
                Projekat projekat = (Projekat) ruNode;
                if(projekat.getProjectFile()!=null) {
                    printWriter.append(projekat.getProjectFile().toString());
                    printWriter.append("\n");
                    projekat.setChanged(false);
                }
            }
            printWriter.close();
        }
    }
}
