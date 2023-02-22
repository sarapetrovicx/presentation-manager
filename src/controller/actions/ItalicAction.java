package controller.actions;

import controller.AbstractRuDokAction;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@Setter

public class ItalicAction extends AbstractRuDokAction {

    private JTextPane textPane;

    public ItalicAction() {
        putValue(SMALL_ICON, loadIcon("images/italic.png"));
        putValue(NAME, "Italic");
        putValue(SHORT_DESCRIPTION, "Italic");
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        StyledDocument doc = (StyledDocument) textPane.getDocument();
        int selectionEnd = textPane.getSelectionEnd();
        int selectionStart = textPane.getSelectionStart();
        if (selectionStart == selectionEnd) {
            return;
        }
        Element element = doc.getCharacterElement(selectionStart);
        AttributeSet as = element.getAttributes();

        MutableAttributeSet asNew = new SimpleAttributeSet(as.copyAttributes());
        StyleConstants.setItalic(asNew, !StyleConstants.isItalic(as));
        doc.setCharacterAttributes(selectionStart, textPane.getSelectedText()
                .length(), asNew, true);
    }
}
