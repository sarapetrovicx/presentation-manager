package gui.state.concrete;

import gui.components.MainFrame;
import gui.state.StateManager;
import gui.tree.observer.ISubscriber;
import gui.tree.observer.Message;
import gui.tree.view.PrezentacijaView;
import gui.tree.view.SlajdView;
import model.composite.RuNode;
import model.workspace.Prezentacija;
import model.workspace.Slajd;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SlideShowView extends JPanel implements ISubscriber {

    private Prezentacija prezentacija;
    private JPanel dugmici;
    private JPanel cards;
    private JPanel exitPanel;
    private CardLayout cardLayout;
    private JButton prev;
    private JButton next;
    private JButton exit;
    private StateManager stateManager;

    public SlideShowView(Prezentacija prezentacija) {
        this.prezentacija = prezentacija;
        this.prezentacija.addSubscriber(this);

        stateManager = ((PrezentacijaView)prezentacija.getSubscriber()).getStateManager();

        dugmici = new JPanel();
        exitPanel = new JPanel();

        setLayout(new BorderLayout());
        dugmici.setLayout(new BorderLayout());
        exitPanel.setLayout(new BorderLayout());

        next = new JButton("Next");
        exit = new JButton("Exit");
        prev = new JButton("Previous");

        cards = new JPanel();
        cards.setLayout(new CardLayout());


        List<RuNode> slajdovi = prezentacija.getChildren();
        for(int i = 0; i < slajdovi.size(); i++) {
            SlajdView slajdView = new SlajdView((Slajd) prezentacija.getChildren().get(i), prezentacija.getURL());
            cards.add(slajdView);
        }

        cardLayout = (CardLayout) cards.getLayout();
        cardLayout.first(cards);

        next.addActionListener(ActionListener -> {
            cardLayout.next(cards);
            MainFrame.getInstance().getDesniPanel().updateUI();
        });

        prev.addActionListener(ActionListener -> {
            cardLayout.previous(cards);
            MainFrame.getInstance().getDesniPanel().updateUI();
        });

        exit.addActionListener(ActionListener -> {
//            MainFrame.getInstance().getDesniPanel().removeAll();
            stateManager.setEditState();
            stateManager.getCurrent().switchStates(prezentacija);
        });

        dugmici.add(prev, BorderLayout.WEST);
        dugmici.add(next, BorderLayout.EAST);

        exitPanel.add(exit, BorderLayout.WEST);
        add(dugmici, BorderLayout.SOUTH);
        add(exitPanel, BorderLayout.NORTH);

        add(cards, BorderLayout.CENTER);
    }

    @Override
    public void update(Object notification, Message message) {
        if (message.equals(Message.OTHER)) {
            if (notification instanceof Slajd) {
                SlajdView slajdView = new SlajdView((Slajd) notification, prezentacija.getURL());
                cards.add(slajdView);
                updateUI();
            }
        }
        else if (message.equals(Message.DELETE)) {
            if (notification instanceof Slajd) {
                ArrayList<RuNode> slajdovi = prezentacija.getChildren();
                cards.removeAll();
                for (RuNode slajd : slajdovi) {
                    SlajdView slajdView = new SlajdView((Slajd) slajd, prezentacija.getURL());
                    cards.add(slajdView);
                }
                cards.updateUI();
            }
        }
    }
}
