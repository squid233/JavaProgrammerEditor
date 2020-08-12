package io.github.squid233.javaprogrammereditor;

import javax.swing.event.MouseInputListener;
import java.awt.event.MouseEvent;

import static io.github.squid233.javaprogrammereditor.Main.frame;

/**
 * @author squid233
 */
public class MouseListener implements MouseInputListener {
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int button;
        if (e != null) {
            button = e.getButton();
        } else {
            button = MouseEvent.BUTTON1;
        }

        if (button == MouseEvent.BUTTON3) {
            frame.editArea.copy.setEnabled(frame.editArea.canCopy());
            frame.editArea.paste.setEnabled(frame.editArea.isClipboardHasString());
            frame.editArea.cut.setEnabled(frame.editArea.canCopy());
            frame.editArea.delete.setEnabled(frame.editArea.canCopy());
            frame.editArea.rightClick.show(frame.editArea, e.getX(), e.getY());
        } else if (button == MouseEvent.BUTTON1) {
            frame.cut.setEnabled(frame.editArea.canCopy());
            frame.copy.setEnabled(frame.editArea.canCopy());
            frame.paste.setEnabled(frame.editArea.isClipboardHasString());
            frame.delete.setEnabled(frame.editArea.canCopy());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
