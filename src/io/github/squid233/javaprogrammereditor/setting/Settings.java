package io.github.squid233.javaprogrammereditor.setting;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author squid233
 */
public class Settings {

    public static final Properties SETTINGS = new Properties();

    public static final String LINE_WRAP = "lineWrap";
    public static final boolean LINE_WRAP_DEFAULT = false;
    public static final String PROGRAM_LOOK_AND_FEEL = "programLookAndFeel";
    public static final String PROGRAM_LOOK_AND_FEEL_DEFAULT = "default";
    public static final String USE_DARK_THEME = "useDarkTheme";
    public static final boolean USE_DARK_THEME_DEFAULT = false;
    public static final String FONT = "font";
    public static final String FONT_DEFAULT = "Times New Roman";
    public static final String TRUE = String.valueOf(true), FALSE = String.valueOf(false);

    public static void load() {
        try {
            SETTINGS.load(new FileReader("settings.properties"));
        } catch (IOException e) {
            SETTINGS.put(FONT, FONT_DEFAULT);
            SETTINGS.put(LINE_WRAP, LINE_WRAP_DEFAULT);
            SETTINGS.put(PROGRAM_LOOK_AND_FEEL, PROGRAM_LOOK_AND_FEEL_DEFAULT);
            SETTINGS.put(USE_DARK_THEME, USE_DARK_THEME_DEFAULT);
            try {
                SETTINGS.store(new FileWriter("settings.properties"), null);
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        }
    }

    public static void setSetting(String setting, String value) {
        Settings.SETTINGS.setProperty(setting, value);
        try {
            Settings.SETTINGS.store(new FileWriter("settings.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
