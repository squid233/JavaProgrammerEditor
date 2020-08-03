package io.github.squid233.javaprogrammereditor;

import javax.swing.*;

/**
 * @author squid233
 */
public class SettingsFrame extends JDialog {

    public SettingsFrame(JFrame parent) {
        super(parent, "设置", true);
        if ((parent.getExtendedState() & Frame.MAXIMIZED_BOTH) == Frame.MAXIMIZED_BOTH) {
            /* parent.getWidth() / 2 + parent.getWidth() / 4, parent.getHeight() - 8 - 8 */
            this.setSize((parent.getWidth() >> 1) + (parent.getWidth() >> 2), parent.getHeight() - 16);
        } else {
            this.setSize((parent.getWidth() >> 1) + (parent.getWidth() >> 2), parent.getHeight());
        }
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setVisible(true);
    }
}
