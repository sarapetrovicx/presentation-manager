package gui.tree.view;

import gui.components.MainFrame;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

@Getter
@Setter


public class ProjekatView extends JPanel implements ISubscriber {

    private Projekat projekat;
    private JPanel jPanel;
    private JTabbedPane jTabbedPane;
    private JLabel ime;
    private JPanel jPanel2;
    private JSplitPane splitPane;
    private JToolBar slotoviTulBar;
    private JToolBar izmenaSlotova;
    private ArrayList<PrezentacijaView> prezentacijaViews = new ArrayList<>();

    public ProjekatView(Projekat projekat) {
        this.projekat = projekat;
        projekat.addSubscriber(this);

        setMinimumSize(new Dimension(400, 500));
        setLayout(new BorderLayout());

        slotoviTulBar = new JToolBar(JToolBar.HORIZONTAL);
        izmenaSlotova = new JToolBar(JToolBar.VERTICAL);

        jPanel = new JPanel(new BorderLayout());

        ime = new JLabel(projekat.getName());
        add(ime, BorderLayout.NORTH);

        jTabbedPane = new JTabbedPane();
        jTabbedPane.setBounds(50, 100, 300, 300);

        jPanel.add(jTabbedPane, BorderLayout.CENTER);
        jPanel.add(slotoviTulBar, BorderLayout.NORTH);
        jPanel.add(izmenaSlotova, BorderLayout.EAST);

        add(jPanel, BorderLayout.CENTER);
        add(ime, BorderLayout.NORTH);
    }

    @Override
    public void update(Object notification, Message message) {
        projekat.setChanged(true);
        if(message.equals(Message.OTHER)){
            if (notification instanceof Prezentacija) {

                PrezentacijaView prezentacijaView = new PrezentacijaView((Prezentacija) notification);
                prezentacijaViews.add(prezentacijaView);
                jTabbedPane.addTab(prezentacijaView.getPrezentacija().getName(), prezentacijaView);
                jTabbedPane.updateUI();
            }
        } else if(message.equals(Message.DELETE)){
            if (notification instanceof Projekat) {
                jTabbedPane.removeAll();
                ime.setText("");
            } else if(notification instanceof Prezentacija){
                for(int i = 0; i < projekat.getChildren().size(); i++){
                    if(projekat.getChildren().get(i).equals(notification)){
                        jTabbedPane.removeTabAt(i);
                        jTabbedPane.updateUI();
                        break;
                    }
                }
            }
        } else if(message.equals(Message.NAME)){
            if(notification instanceof Projekat){
                ime.setText(projekat.getName());
            }
        }
    }


    public void setProjekat(Projekat projekat) {
        if (this.projekat != projekat) {
            if (this.projekat != null) {
                this.projekat.removeSubscriber(this);
            }
            this.projekat = projekat;
            jTabbedPane.removeAll();
            if (projekat != null) {
                projekat.addSubscriber(this);

                this.ime.setText(projekat.getName());
                slotoviTulBar.removeAll();
                slotoviTulBar.add(MainFrame.getInstance().getActionManager().getSlideShowAction());
                slotoviTulBar.add(Box.createHorizontalGlue());
                slotoviTulBar.add(MainFrame.getInstance().getActionManager().getSelectSlotAction());
                slotoviTulBar.add(MainFrame.getInstance().getActionManager().getAddSlotAction());
                slotoviTulBar.add(MainFrame.getInstance().getActionManager().getMoveSlotAction());
                slotoviTulBar.add(MainFrame.getInstance().getActionManager().getDeleteSlotAction());

                izmenaSlotova.removeAll();
                izmenaSlotova.add(Box.createVerticalGlue());
                izmenaSlotova.add(MainFrame.getInstance().getActionManager().getTypeOfSlotAction());
                izmenaSlotova.add(MainFrame.getInstance().getActionManager().getEditSlotAction());
                izmenaSlotova.add(MainFrame.getInstance().getActionManager().getSelectColorAction());
                izmenaSlotova.add(MainFrame.getInstance().getActionManager().getStrokeAction());

                ArrayList<RuNode> prezentacije = projekat.getChildren();

                for (int i = 0; i < prezentacije.size(); i++) {
                    PrezentacijaView prezentacijaView = (PrezentacijaView)((Prezentacija)(prezentacije.get(i))).getSubscriber();
                    jTabbedPane.addTab(prezentacijaView.getPrezentacija().getName(), prezentacijaView);
                }
            }
        }

    }

    public void setIme(String ime) {
        this.ime.setText(ime);
    }
}