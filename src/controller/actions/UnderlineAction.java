package controller.actions;

import controller.AbstractRuDokAction;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
@Setter

public class UnderlineAction extends AbstractRuDokAction {

    private JTextPane textPane;

    public UnderlineAction() {
        putValue(SMALL_ICON, loadIcon("images/underline.png"));
        putValue(NAME, "Underline");
        putValue(SHORT_DESCRIPTION, "Underline");
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
        StyleConstants.setUnderline(asNew, !StyleConstants.isUnderline(as));
        doc.setCharacterAttributes(selectionStart, textPane.getSelectedText()
                .length(), asNew, true);
    }
}
