package io.github.squid233.javaprogrammereditor;

import io.github.squid233.javaprogrammereditor.setting.Settings;
import io.github.squid233.javaprogrammereditor.util.DialogUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author squid233
 */
public class FontDialog extends JDialog {

    private static FontDialog instance;

    JTextField fontF = new JTextField(Settings.getSetting(Settings.FONT));
    JList<String> fontL = new JList<>(GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());

    public FontDialog(JFrame parent) {
        super(parent, "字体", true);
        init();
        addListeners();
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        DialogUtil.init(parent, this, 4);
    }

    private void init() {
        JPanel panel = new JPanel();
        JLabel font = new JLabel("字体(F):");
        JLabel glyph = new JLabel("字形(Y):");
        JLabel size = new JLabel("大小(S):");
        JTextField glyphF = new JTextField();
        JTextField sizeF = new JTextField();
        JScrollPane fontSp = new JScrollPane(fontL);
        fontSp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        font.setDisplayedMnemonic('F');
        glyph.setDisplayedMnemonic('Y');
        size.setDisplayedMnemonic('S');
        fontL.setSelectedValue(Settings.getSetting(Settings.FONT), true);
        this.add(panel);
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints constraints = new GridBagConstraints();
        panel.setLayout(layout);
        panel.add(font);
        panel.add(glyph);
        panel.add(size);
        panel.add(fontF);
        panel.add(glyphF);
        panel.add(sizeF);
        panel.add(fontSp);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridwidth = 1;
        layout.setConstraints(font, constraints);
        layout.setConstraints(glyph, constraints);
        constraints.gridwidth = 0;
        layout.setConstraints(size, constraints);
        constraints.gridwidth = 1;
        layout.setConstraints(fontF, constraints);
        layout.setConstraints(glyphF, constraints);
        constraints.gridwidth = 0;
        layout.setConstraints(sizeF, constraints);
        constraints.gridwidth = 1;
        layout.setConstraints(fontSp, constraints);
    }

    private void addListeners() {
        fontL.addListSelectionListener(e -> fontF.setText(fontL.getSelectedValue()));
    }

    public static void open(JFrame parent) {
        if (instance == null) {
            instance = new FontDialog(parent);
        } else {
            DialogUtil.init(parent, instance, 4);
        }
    }
}
