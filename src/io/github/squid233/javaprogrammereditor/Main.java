package io.github.squid233.javaprogrammereditor;

import io.github.squid233.javaprogrammereditor.setting.Settings;

import javax.swing.*;

/**
 * @author squid233
 */
public class Main {

    public static Frame frame;
    public static final String VERSION = "0.1.0-pre-alpha";
    public static final String UPDATE_DATE = "2020-8-3";
    public static final String BUILD_VERSION = "build.1";

    public static void main(String[] args) {
        Settings.load();
        try {
            if (Settings.getSetting(Settings.PROGRAM_LOOK_AND_FEEL).equals(Settings.PROGRAM_LOOK_AND_FEEL_DEFAULT)) {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } else {
                for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if (info.getName().equals(Settings.getSetting(Settings.PROGRAM_LOOK_AND_FEEL))) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.out.println("Cannot set look and feel");
            System.out.println("Cause by:" + e);
        }
        frame = new Frame();
    }
}
