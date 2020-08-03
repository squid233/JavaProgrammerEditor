package io.github.squid233.javaprogrammereditor;

import io.github.squid233.javaprogrammereditor.setting.Settings;

import javax.swing.*;

/**
 * @author squid233
 */
public class Main {

    public static Frame frame;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }
        Settings.load();
        frame = new Frame();
    }
}
