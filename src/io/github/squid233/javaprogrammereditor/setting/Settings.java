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
    public static final String USE_DARK_THEME = "useDarkTheme";
    public static final String TRUE = String.valueOf(true), FALSE = String.valueOf(false);

    public static void load() {
        try {
            SETTINGS.load(new FileReader("settings.properties"));
        } catch (IOException e) {
            SETTINGS.put(LINE_WRAP, false);
            SETTINGS.put(USE_DARK_THEME, false);
            try {
                SETTINGS.store(new FileWriter("settings.properties"), null);
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        }
    }
}
