package io.github.squid233.javaprogrammereditor;

import io.github.squid233.javaprogrammereditor.setting.Settings;
import io.github.squid233.javaprogrammereditor.util.ExtendedTextArea;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * @author squid233
 */
public class EditArea extends ExtendedTextArea {

    // private static String[] buffer = {""};

    JPopupMenu rightClick = new JPopupMenu();
    JMenuItem revoke = new JMenuItem("撤销(U)"), cut = new JMenuItem("剪切(T)"), copy = new JMenuItem("复制(C)"), paste = new JMenuItem("粘贴(P)"),
            delete = new JMenuItem("删除(D)");

    public void init() {
        this.setBackground(Color.decode(Settings.getSetting(Settings.BACKGROUND_COLOR)));
        //noinspection MagicConstant
        this.setFont(new Font(Settings.getSetting(Settings.FONT), Settings.getFontStyle(), Settings.getSettingI(Settings.FONT_SIZE)));
        this.setComponentPopupMenu(rightClick);
        /// rightClick.add(revoke);
        rightClick.addSeparator();
        rightClick.add(cut); rightClick.add(copy); rightClick.add(paste); rightClick.add(delete);
        revoke.setMnemonic(KeyEvent.VK_U);
        cut.setMnemonic(KeyEvent.VK_T);
        copy.setMnemonic(KeyEvent.VK_C);
        paste.setMnemonic(KeyEvent.VK_P);
        delete.setMnemonic(KeyEvent.VK_D);
        initListeners();
        this.add(rightClick);
    }

    private void initListeners() {
        cut.addActionListener(this::action);
        copy.addActionListener(this::action);
        paste.addActionListener(this::action);
        delete.addActionListener(this::action);
    }

    public void action(ActionEvent e) {
        String str = e.getActionCommand();
        // 复制
        if (str.equals(copy.getText())) {
            this.copy();
        }
        // 粘贴
        else if (str.equals(paste.getText())) {
            this.paste();
        }
        // 剪切
        else if (str.equals(cut.getText())) {
            this.cut();
        }
        // 删除
        else if (str.equals(delete.getText())) {
            this.delete();
        }
    }
}
