package controller.actions;

import controller.AbstractRuDokAction;
import lombok.Getter;
import lombok.Setter;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

@Getter
@Setter

public class BoldAction extends AbstractRuDokAction {

    private JTextPane textPane;

    public BoldAction() {
        putValue(SMALL_ICON, loadIcon("images/bold.png"));
        putValue(NAME, "Bold");
        putValue(SHORT_DESCRIPTION, "Bold");
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
        StyleConstants.setBold(asNew, !StyleConstants.isBold(as));
        doc.setCharacterAttributes(selectionStart, textPane.getSelectedText()
                .length(), asNew, true);
    }
}
