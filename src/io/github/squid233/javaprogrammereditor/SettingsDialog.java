package io.github.squid233.javaprogrammereditor;

import io.github.squid233.javaprogrammereditor.setting.Settings;

import javax.swing.*;
import java.awt.*;

/**
 * @author squid233
 */
public class SettingsDialog extends JDialog {

    JLabel setLook = new JLabel("请选择要设置的外观：");
    JComboBox<String> lookAndFeelInfo;
    JList<String> lookAndFeels;
    JPanel panel = new JPanel();
    JPanel controlPanel = new JPanel();
    JButton lookB = new JButton("外观");
    JButton themeB = new JButton("主题");
    CardLayout cardLayout = new CardLayout();
    GridBagLayout bagLayout = new GridBagLayout();
    GridBagConstraints constraints = new GridBagConstraints();

    public SettingsDialog(JFrame parent) {
        super(parent, "设置", true);
        init();
        addListeners();
        if ((parent.getExtendedState() & JFrame.MAXIMIZED_BOTH) == JFrame.MAXIMIZED_BOTH) {
            /* parent.getWidth() / 2 + parent.getWidth() / 3, parent.getHeight() - 8 - 8 */
            this.setSize((parent.getWidth() >> 1) + parent.getWidth() / 3, parent.getHeight() - 16);
        } else {
            this.setSize((parent.getWidth() >> 1) + (parent.getWidth() >> 2), parent.getHeight());
        }
        this.setLocationRelativeTo(parent);
        this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    private void init() {
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
        JLabel installedLook = new JLabel("已安装的外观：");
        JPanel lookP = new JPanel();
        lookAndFeelInfo = new JComboBox<>(lafNames);
        lookAndFeels = new JList<>(lafNamesRemoveDefault);

        panel.setLayout(cardLayout);
        panel.add("look", lookP);

        lookP.setLayout(bagLayout);
        lookP.add(installedLook);
        lookP.add(lookAndFeels);
        lookP.add(setLook);
        lookP.add(lookAndFeelInfo);

        constraints.fill = GridBagConstraints.BOTH;

        constraints.gridwidth = 0;
        constraints.weightx = 0;
        constraints.weighty = 0;
        bagLayout.setConstraints(installedLook, constraints);

        constraints.gridwidth = 1;
        constraints.weighty = 1;
        bagLayout.setConstraints(lookAndFeels, constraints);

        constraints.weighty = 0;
        bagLayout.setConstraints(setLook, constraints);

        constraints.fill = GridBagConstraints.NONE;
        constraints.gridwidth = 0;
        bagLayout.setConstraints(lookAndFeelInfo, constraints);

        controlPanel.add(lookB);
        controlPanel.add(themeB);

        this.add(panel, BorderLayout.CENTER);
        this.add(controlPanel, BorderLayout.NORTH);

        lookAndFeelInfo.setSelectedItem(UIManager.getLookAndFeel().getName());
        lookAndFeelInfo.updateUI();
        init2();
    }

    private void init2() {

    }

    private void addListeners() {
        lookB.addActionListener(e -> cardLayout.show(panel, "look"));
        themeB.addActionListener(e -> cardLayout.show(panel, "theme"));
        lookAndFeelInfo.addItemListener(e -> {
            String systemDefault = "System Default";
            Settings.setSetting(Settings.PROGRAM_LOOK_AND_FEEL, (String) lookAndFeelInfo.getSelectedItem());
            try {
                if (systemDefault.equals(Settings.SETTINGS.getProperty(Settings.PROGRAM_LOOK_AND_FEEL))) {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                    Settings.setSetting(Settings.PROGRAM_LOOK_AND_FEEL, Settings.PROGRAM_LOOK_AND_FEEL_DEFAULT);
                } else {
                    for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                        if (info.getName().equals(Settings.SETTINGS.getProperty(Settings.PROGRAM_LOOK_AND_FEEL))) {
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
}
