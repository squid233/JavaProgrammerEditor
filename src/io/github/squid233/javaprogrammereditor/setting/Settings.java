package io.github.squid233.javaprogrammereditor.setting;

import java.awt.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

/**
 * @author squid233
 */
public class Settings {

    public static final Properties SETTINGS = new Properties();

    public static final String BACKGROUND_COLOR = "backgroundColor";
    public static final String BACKGROUND_COLOR_DEFAULT = "#FFFFFF";
    public static final String FONT = "font";
    public static final String FONT_DEFAULT = "Times New Roman";
    public static final String FONT_SIZE = "fontSize";
    public static final int FONT_SIZE_DEFAULT = 16;
    public static final String FONT_STYLE = "fontStyle";
    public static final String FONT_STYLE_DEFAULT = "plain";
    public static final String LINE_WRAP = "lineWrap";
    public static final boolean LINE_WRAP_DEFAULT = false;
    public static final String PROGRAM_LOOK_AND_FEEL = "programLookAndFeel";
    public static final String PROGRAM_LOOK_AND_FEEL_DEFAULT = "default";
    public static final String TRUE = String.valueOf(true), FALSE = String.valueOf(false);

    public static void load() {
        try {
            SETTINGS.load(new FileReader("settings.properties"));
        } catch (IOException e) {
            SETTINGS.put(BACKGROUND_COLOR, BACKGROUND_COLOR_DEFAULT);
            SETTINGS.put(FONT, FONT_DEFAULT);
            SETTINGS.put(FONT_SIZE, FONT_SIZE_DEFAULT);
            SETTINGS.put(FONT_STYLE, FONT_STYLE_DEFAULT);
            SETTINGS.put(LINE_WRAP, LINE_WRAP_DEFAULT);
            SETTINGS.put(PROGRAM_LOOK_AND_FEEL, PROGRAM_LOOK_AND_FEEL_DEFAULT);
            try {
                SETTINGS.store(new FileWriter("settings.properties"), null);
            } catch (IOException ee) {
                ee.printStackTrace();
            }
        }
    }

    public static void setSetting(String setting, String value) {
        SETTINGS.setProperty(setting, value);
        try {
            SETTINGS.store(new FileWriter("settings.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getSetting(String setting) {
        return SETTINGS.getProperty(setting);
    }

    public static boolean getSettingB(String setting) {
        return Boolean.parseBoolean(getSetting(setting));
    }

    public static int getFontStyle() {
        return "bold".equals(getSetting(FONT_STYLE)) ? Font.BOLD
                : "italic".equals(getSetting(FONT_STYLE)) ? Font.ITALIC
                : "all".equals(getSetting(FONT_STYLE)) ? Font.BOLD & Font.ITALIC
                : Font.PLAIN;
    }

    public static int getSettingI(String setting) {
        return Integer.parseInt(getSetting(setting));
    }
}
