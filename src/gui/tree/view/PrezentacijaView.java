package gui.tree.view;

import controller.actions.MyMouseController;
import gui.components.MainFrame;
import gui.slots.state.StateManagerSlots;
import gui.state.StateManager;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import lombok.Getter;
import lombok.Setter;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Projekat;
import model.workspace.Slajd;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
@Getter
@Setter

public class PrezentacijaView extends JPanel implements ISubscriber {

    private Prezentacija prezentacija;
    private JScrollPane scrollPane;
    private JLabel autor;
    private JPanel jPanel;
    private JPanel umanjeno;
    private JScrollPane levi;
    private JButton slideShow;
    private StateManager stateManager;
    private StateManagerSlots stateManagerSlots;
    private ArrayList <SlajdView> slajdViews = new ArrayList<>();
    private ArrayList <SlajdView> slajdViews2 = new ArrayList<>();

    public PrezentacijaView(Prezentacija prezentacija) {
        this.prezentacija = prezentacija;
        prezentacija.addSubscriber(this);

        stateManagerSlots = new StateManagerSlots();

        setLayout(new BorderLayout());
        setMinimumSize(new Dimension(200,200));

        stateManager = new StateManager();
        jPanel = new JPanel();
        autor = new JLabel("Author: " + prezentacija.getAutor());
        umanjeno = new JPanel();

        jPanel.setLayout(new BoxLayout(jPanel,BoxLayout.Y_AXIS));
        umanjeno.setLayout(new BoxLayout(umanjeno,BoxLayout.Y_AXIS));


        levi = new JScrollPane(umanjeno,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        levi.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/4, MainFrame.getInstance().getHeight()/5));
        levi.setMaximumSize(new Dimension(MainFrame.getInstance().getWidth()/4, MainFrame.getInstance().getHeight()/5));


        scrollPane = new JScrollPane(jPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setMaximumSize(new Dimension(MainFrame.getInstance().getWidth()/2, MainFrame.getInstance().getHeight()/2));
        scrollPane.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/2, MainFrame.getInstance().getHeight()/2));

        add(scrollPane, BorderLayout.CENTER);
        add(autor, BorderLayout.SOUTH);
        add(levi, BorderLayout.WEST);

    }

    @Override
    public void update(Object notification, Message message) {
        if (message.equals(Message.OTHER)) {
            if (notification instanceof Prezentacija) {
                autor.setText("Author: " + prezentacija.getAutor());
                jPanel.removeAll();
                umanjeno.removeAll();
                for (SlajdView slajd : slajdViews) {
                    slajd.setAutor(((Prezentacija) notification).getAutor());
                    slajd.setUrl(((Prezentacija) notification).getURL());
                    jPanel.add(Box.createVerticalStrut(10));
                    jPanel.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/2, (MainFrame.getInstance().getHeight())*slajdViews.size()));
                    jPanel.add(slajd);
                }
                for(SlajdView slajdView : slajdViews2){
                    slajdView.setAutor(((Prezentacija) notification).getAutor());
                    slajdView.setUrl(((Prezentacija) notification).getURL());
                    umanjeno.add(Box.createVerticalStrut(10));
                    umanjeno.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/5, (MainFrame.getInstance().getHeight())*slajdViews2.size()));
                    umanjeno.add(slajdView);
                }
                jPanel.updateUI();
                umanjeno.updateUI();

            } else if (notification instanceof Slajd) {
                jPanel.add(Box.createVerticalStrut(10));
                umanjeno.add(Box.createVerticalStrut(10));
                SlajdView slajdView = new SlajdView((Slajd) notification, prezentacija.getURL());
                slajdView.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/3, MainFrame.getInstance().getHeight()/3));
                slajdView.setMaximumSize(new Dimension(MainFrame.getInstance().getWidth()/3, MainFrame.getInstance().getHeight()/3));
                SlajdView slajdView2 = new SlajdView((Slajd) notification, prezentacija.getURL());
                slajdView2.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/5, MainFrame.getInstance().getHeight()/5));
                slajdView2.setMaximumSize(new Dimension(MainFrame.getInstance().getWidth()/5, MainFrame.getInstance().getHeight()/5));
                MyMouseController mouseController = new MyMouseController((Slajd) notification);
                slajdView.addMouseListener(mouseController);
                slajdView.addMouseMotionListener(mouseController);
                slajdViews.add(slajdView);
                slajdViews2.add(slajdView2);
                jPanel.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/2, (MainFrame.getInstance().getHeight())*slajdViews.size()));
                umanjeno.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/5, (MainFrame.getInstance().getHeight())*slajdViews2.size()));
                jPanel.add(slajdView);
                umanjeno.add(slajdView2);

            }
            updateUI();
        } else if(message.equals(Message.DELETE)) {
            if (notification instanceof Slajd) {
                ArrayList<SlajdView> toRemove1 = new ArrayList<>();
                for(SlajdView s: slajdViews){
                    if(notification.equals(s.getSlajd())){
                        toRemove1.add(s);
                    }
                }
                slajdViews.removeAll(toRemove1);

                ArrayList<SlajdView> toRemove = new ArrayList<>();
                for(SlajdView s: slajdViews2){
                    if(notification.equals(s.getSlajd())){
                        toRemove.add(s);
                    }
                }
                slajdViews2.removeAll(toRemove);

                jPanel.removeAll();
                umanjeno.removeAll();
                for (SlajdView slajd : slajdViews) {
                    jPanel.add(Box.createVerticalStrut(10));
                    jPanel.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/2, (MainFrame.getInstance().getHeight())*slajdViews.size()));
                    jPanel.add(slajd);
                }
                for(SlajdView slajdView : slajdViews2){
                    umanjeno.add(Box.createVerticalStrut(10));
                    umanjeno.setPreferredSize(new Dimension(MainFrame.getInstance().getWidth()/5, (MainFrame.getInstance().getHeight())*slajdViews2.size()));
                    umanjeno.add(slajdView);
                }
                jPanel.updateUI();
                umanjeno.updateUI();
            }
        } else if(message.equals(Message.NAME)){
            if(notification instanceof Prezentacija) {
                ArrayList<RuNode> deca = ((Projekat) ((Prezentacija) notification).getParent()).getChildren();
                int indeks = 0;
                for (int i = 0; i < deca.size(); i++) {
                    if (deca.get(i).equals(notification)) {
                        indeks = i;
                    }
                }
                MainFrame.getInstance().getProjekatView().getJTabbedPane().setTitleAt(indeks, ((Prezentacija) notification).getName());
            }
        }
    }

    public void myMousePressed(Slajd slajd, Point point){
        Color color = MainFrame.getInstance().getActionManager().getSelectColorAction().getColor();
        stateManagerSlots.getCurrent().myMousePressed(slajd, point, color);
        updateUI();
    }
    public void myMouseDragged(Point point){
        stateManagerSlots.getCurrent().myMouseDragged(point);
        updateUI();
    }
    public void myMouseReleased(){
        stateManagerSlots.getCurrent().myMouseReleased();
        updateUI();
    }
}
