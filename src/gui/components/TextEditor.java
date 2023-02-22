package gui.components;

import gui.slots.model.Slot;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;

public class TextEditor extends JDialog {

    private JToolBar toolBar;
    private JTextPane jTextPane;
    private JButton save;
    private Slot slot;

    public TextEditor(Frame owner, String title, boolean modal) {
        super(owner, title, modal);

        toolBar = new JToolBar(JToolBar.HORIZONTAL);
        jTextPane = new JTextPane();
        slot = MainFrame.getInstance().getSelectedSlot();
        save = new JButton("Save");



        MainFrame.getInstance().getActionManager().getBoldAction().setTextPane(jTextPane);
        MainFrame.getInstance().getActionManager().getUnderlineAction().setTextPane(jTextPane);
        MainFrame.getInstance().getActionManager().getItalicAction().setTextPane(jTextPane);

        toolBar.add(MainFrame.getInstance().getActionManager().getBoldAction());
        toolBar.addSeparator();
        toolBar.add(MainFrame.getInstance().getActionManager().getItalicAction());
        toolBar.addSeparator();
        toolBar.add(MainFrame.getInstance().getActionManager().getUnderlineAction());
        toolBar.add(Box.createHorizontalGlue());
        toolBar.add(save);
        add(toolBar, BorderLayout.NORTH);
        add(jTextPane, BorderLayout.CENTER);

        save.addActionListener(e -> {
            AttributeSet set;
            String string = "";

            for(int i=0; i<jTextPane.getText().length(); i++) {
                set = jTextPane.getStyledDocument().getCharacterElement(i).getAttributes();
                char s = jTextPane.getText().charAt(i);

                if (StyleConstants.isBold(set)){
                    string+= "~" + s + "~";
                }
                else if (StyleConstants.isItalic(set)){
                    string+= "&"+ s + "&";
                }
                else if (StyleConstants.isUnderline(set)){
                    string+= "$"+ s + "$";
                } else {
                    string += s;
                }
            }
            slot.setContent(string);
            System.out.println(slot.getContent());
            setVisible(false);
        });

        setLocationRelativeTo(null);
        setSize(300, 300);
    }
}
