package io.github.squid233.javaprogrammereditor.util;

import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * @author squid233
 */
public class DialogUtil {
    public static void init(@NotNull JFrame parent, JDialog jDialog, int i) {
        if ((parent.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
            /* parent.getWidth() / 2 + parent.getWidth() / 4, parent.getHeight() - 8 - 8 */
            jDialog.setSize((parent.getWidth() >> 1) + parent.getWidth() / i, parent.getHeight() - 16);
        } else {
            jDialog.setSize((parent.getWidth() >> 1) + parent.getWidth() / i, parent.getHeight());
        }
        jDialog.setLocationRelativeTo(parent);
        jDialog.setVisible(true);
    }
}
