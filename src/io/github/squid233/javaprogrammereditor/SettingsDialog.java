package io.github.squid233.javaprogrammereditor;

import io.github.squid233.javaprogrammereditor.setting.Settings;
import io.github.squid233.javaprogrammereditor.util.DialogUtil;

import javax.swing.*;
import java.awt.*;

/**
 * @author squid233
 */
public class SettingsDialog extends JDialog {

    private static SettingsDialog instance;

    JComboBox<String> lookAndFeelInfo;
    JList<String> lookAndFeels;
    JTabbedPane panel = new JTabbedPane();
    JPanel lookP = new JPanel(), themeP = new JPanel();

    public SettingsDialog(JFrame parent) {
        super(parent, "设置", true);
        init();
        addListeners();
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        DialogUtil.init(parent, this, 3);
    }

    private void init() {
        this.add(panel);
        int lafLength = UIManager.getInstalledLookAndFeels().length + 1;
        String[] lafNames = new String[lafLength];
        lafNames[0] = "System Default";
        for (int i = 1; i < lafLength; i++) {
            lafNames[i] = UIManager.getInstalledLookAndFeels()[i - 1].getName();
        }
        String[] lafNamesRemoveDefault = new String[lafNames.length - 1];
        /*
         * This line has the same effect as below:
         * for (int i = 0; i < lafNames.length -1; i++) {
         *     lafNamesRemoveDefault[i] = lafNames[i + 1];
         * }
         */
        System.arraycopy(lafNames, 1, lafNamesRemoveDefault, 0, lafNames.length - 1);
        JLabel installedLook = new JLabel("已安装的外观");
        JLabel setLook = new JLabel("请选择要设置的外观：");
        lookAndFeelInfo = new JComboBox<>(lafNames);
        lookAndFeels = new JList<>(lafNamesRemoveDefault);
        GridBagLayout bagLayout = new GridBagLayout();
        GridBagConstraints lookC = new GridBagConstraints();
        lookP.setLayout(bagLayout);
        lookP.add(installedLook);
        lookP.add(lookAndFeels);
        lookP.add(setLook);
        lookP.add(lookAndFeelInfo);

        lookC.fill = GridBagConstraints.BOTH;

        lookC.gridwidth = 0;
        lookC.weightx = 1;
        lookC.weighty = 0;
        bagLayout.setConstraints(installedLook, lookC);

        lookC.gridwidth = 1;
        lookC.weighty = 1;
        bagLayout.setConstraints(lookAndFeels, lookC);

        lookC.weightx = 0;
        lookC.weighty = 0;
        bagLayout.setConstraints(setLook, lookC);

        lookC.fill = GridBagConstraints.NONE;
        bagLayout.setConstraints(lookAndFeelInfo, lookC);

        panel.addTab("外观", null, lookP, "look");
        panel.addTab("主题", null, themeP, "theme");

        lookAndFeelInfo.setSelectedItem(UIManager.getLookAndFeel().getName());
        lookAndFeelInfo.updateUI();
        init2();
    }

    private void init2() {
        GridBagLayout layout = new GridBagLayout();
        GridBagConstraints themeC = new GridBagConstraints();
        themeP.setLayout(layout);
    }

    private void addListeners() {
        lookAndFeelInfo.addItemListener(e -> {
            String systemDefault = "System Default";
            Settings.setSetting(Settings.PROGRAM_LOOK_AND_FEEL, (String) lookAndFeelInfo.getSelectedItem());
            try {
                if (systemDefault.equals(Settings.getSetting(Settings.PROGRAM_LOOK_AND_FEEL))) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Settings.setSetting(Settings.PROGRAM_LOOK_AND_FEEL, Settings.PROGRAM_LOOK_AND_FEEL_DEFAULT);
                } else {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if (info.getName().equals(Settings.getSetting(Settings.PROGRAM_LOOK_AND_FEEL))) {
                            UIManager.setLookAndFeel(info.getClassName());
                            break;
                        }
                    }
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ee) {
                System.out.println("Cannot set look and feel");
                System.out.println("Cause by:" + e);
            }
            SwingUtilities.updateComponentTreeUI(Main.frame);
            SwingUtilities.updateComponentTreeUI(this);
        });
    }

    public static void open(JFrame parent) {
        if (instance == null) {
            instance = new SettingsDialog(parent);
        } else {
            DialogUtil.init(parent, instance, 3);
        }
    }
}
