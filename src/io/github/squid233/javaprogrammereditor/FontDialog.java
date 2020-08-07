package io.github.squid233.javaprogrammereditor;

import io.github.squid233.javaprogrammereditor.util.DialogUtil;

import javax.swing.*;

/**
 * @author squid233
 */
public class FontDialog extends JDialog {

    private static FontDialog instance;

    public FontDialog(JFrame parent) {
        super(parent, "字体", true);
        init();
        addListeners();
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        DialogUtil.init(parent, this, 4);
    }

    private void init() {

    }

    private void addListeners() {

    }

    public static void open(JFrame parent) {
        if (instance == null) {
            instance = new FontDialog(parent);
        } else {
            DialogUtil.init(parent, instance, 4);
        }
    }
}
