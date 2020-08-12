package io.github.squid233.javaprogrammereditor;

import io.github.squid233.browse.Browse;
import io.github.squid233.javaprogrammereditor.setting.Settings;
import io.github.squid233.javaprogrammereditor.util.DateUtil;

import javax.swing.*;
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.LocalTime;

import static java.awt.event.KeyEvent.*;

/**
 * @author squid233
 */
public class Frame extends JFrame {

    private static final String HYPHEN = " - ";
    private static final String TITLE = "Java Programmer Editor v" +
            Main.VERSION + " [By:squid233] 2020" + " Insider Version:[" + Main.UPDATE_DATE + "+" + Main.BUILD_VERSION + "]";

    private final LocalTime time = LocalTime.now();
    private final LocalDate date = LocalDate.now();

    MouseListener mouseListener = new MouseListener();
    JMenuBar menuBar = new JMenuBar();
    JMenu files = new JMenu("文件(F)"),
            edit = new JMenu("编辑(E)"),
            format = new JMenu("格式(O)"),
            help = new JMenu("帮助(H)");
    JCheckBoxMenuItem lineWrap = new JCheckBoxMenuItem("自动换行(W)");
    JMenuItem font = new JMenuItem("字体(F)...");
    JMenuItem settings = new JMenuItem("设置(T)..."),
            exit = new JMenuItem("退出(X)");
    JMenuItem cut = new JMenuItem("剪切(T)"),
            copy = new JMenuItem("复制(C)"),
            paste = new JMenuItem("粘贴(P)"),
            delete = new JMenuItem("删除(L)"),
            insertIndent = new JMenuItem("插入缩进（4格）(I)"),
            selectAll = new JMenuItem("全选(A)"),
            timeOrDate = new JMenuItem("时间/日期(D)");
    JMenuItem viewHelp = new JMenuItem("查看帮助(H)"),
            about = new JMenuItem("About Java Programmer Editor");
    JScrollPane scrollPane = new JScrollPane();
    EditArea editArea = new EditArea();
    boolean isLineWrap = Settings.getSettingB(Settings.LINE_WRAP);

    public Frame() {
        super("无标题" + HYPHEN + TITLE);
        init();
        initMenu();
        this.setSize(844, 606);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void init() {
        GridBagLayout layout = new GridBagLayout();
        this.setLayout(layout);
        this.setJMenuBar(menuBar);

        this.add(scrollPane);
        this.add(editArea);

        editArea.init();
        lineWrap.setSelected(isLineWrap);

        editArea.setLineWrap(lineWrap.isSelected());
        editArea.setWrapStyleWord(lineWrap.isSelected());

        scrollPane.setViewportView(editArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        if (!isLineWrap) {
            scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        }

        addListeners();

        // 定义一个GridBagConstraints，
        // 是用来控制添加进的组件的显示位置
        GridBagConstraints constraints = new GridBagConstraints();

        // 该方法是为了设置如果组件所在的区域比组件本身要大时的显示情况
        // NONE：不调整组件大小。
        // HORIZONTAL：加宽组件，使它在水平方向上填满其显示区域，但是不改变高度。
        // VERTICAL：加高组件，使它在垂直方向上填满其显示区域，但是不改变宽度。
        // BOTH：使组件完全填满其显示区域。
        constraints.fill = GridBagConstraints.BOTH;

        // 该方法是设置组件水平所占用的格子数，如果为0，就说明该组件是该行的最后一个
        constraints.gridwidth = 0;
        // 该方法设置组件水平的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        constraints.weightx = 1;
        // 该方法设置组件垂直的拉伸幅度，如果为0就说明不拉伸，不为0就随着窗口增大进行拉伸，0到1之间
        constraints.weighty = 1;
        layout.setConstraints(scrollPane, constraints);
    }

    private void addListeners() {
        editArea.addMouseListener(mouseListener);
        edit.addMouseListener(mouseListener);
        viewHelp.addActionListener(e -> Browse.browse("https://scope-tech.github.io/jpe/help.html"));
        about.addActionListener(e ->
                JOptionPane.showMessageDialog(this,
                        TITLE + "\nMIT License开源\n源码请访问" + "https://github.com/squid233/JavaProgrammerEditor\n使用Java 11开发",
                        "关于“Java Programmer Editor”", JOptionPane.PLAIN_MESSAGE));
        lineWrap.addActionListener(e -> {
            if (!lineWrap.isSelected()) {
                Settings.setSetting(Settings.LINE_WRAP, Settings.FALSE);
                editArea.setLineWrap(false);
                editArea.setWrapStyleWord(false);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
            } else if (lineWrap.isSelected()) {
                Settings.setSetting(Settings.LINE_WRAP, Settings.TRUE);
                editArea.setCaretPosition(0);
                editArea.setLineWrap(true);
                editArea.setWrapStyleWord(true);
                scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
            }
        });
        settings.addActionListener(e -> SettingsDialog.open(this));
        exit.addActionListener(e -> this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING)));
        cut.addActionListener(e -> editArea.cut());
        copy.addActionListener(e -> editArea.copy());
        paste.addActionListener(e -> editArea.paste());
        delete.addActionListener(e -> editArea.delete());
        insertIndent.addActionListener(e -> {
            try {
                int caretPosTemp = editArea.getCaretPosition();
                editArea.setText(editArea.getText(0, caretPosTemp) + "    " + editArea.getText(caretPosTemp, editArea.getText().length() - caretPosTemp));
                editArea.setCaretPosition(caretPosTemp + 4);
            } catch (BadLocationException ee) {
                ee.printStackTrace();
            }
        });
        font.addActionListener(e -> FontDialog.open(this));
        selectAll.addActionListener(e -> {
            editArea.setSelectionStart(0);
            editArea.setSelectionEnd(editArea.getText().length());
        });
        timeOrDate.addActionListener(e -> {
            if (!editArea.canCopy()) {
                editArea.append(time.getHour() + ":" + time.getMinute() +
                        " " + date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth() + " " + DateUtil.getDayOfWeek());
            } else {
                editArea.setText(time.getHour() + ":" + time.getMinute() +
                        " " + date.getYear() + "/" + date.getMonthValue() + "/" + date.getDayOfMonth() + " " + DateUtil.getDayOfWeek());
            }

        });
    }

    private void initMenu() {
        menuBar.add(files);
        menuBar.add(edit);
        menuBar.add(format);
        menuBar.add(help);
        files.setMnemonic(VK_F);
        files.addSeparator();
        files.add(settings);
        files.addSeparator();
        files.add(exit);
        settings.setMnemonic(VK_T);
        exit.setMnemonic(VK_X);
        settings.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_DOWN_MASK + InputEvent.ALT_DOWN_MASK));
        edit.setMnemonic(VK_E);
        edit.addSeparator();
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(delete);
        edit.addSeparator();
        edit.add(insertIndent);
        edit.addSeparator();
        edit.add(selectAll);
        edit.add(timeOrDate);
        cut.setMnemonic(VK_T);
        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_DOWN_MASK));
        copy.setMnemonic(VK_C);
        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_DOWN_MASK));
        paste.setMnemonic(VK_P);
        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_DOWN_MASK));
        selectAll.setMnemonic(VK_A);
        selectAll.setAccelerator(KeyStroke.getKeyStroke('A', InputEvent.CTRL_DOWN_MASK));
        timeOrDate.setMnemonic(VK_D);
        timeOrDate.setAccelerator(KeyStroke.getKeyStroke(VK_F5, 0));
        delete.setMnemonic(VK_L);
        delete.setAccelerator(KeyStroke.getKeyStroke(VK_DELETE, 0));
        insertIndent.setMnemonic(VK_I);
        insertIndent.setAccelerator(KeyStroke.getKeyStroke('T', InputEvent.CTRL_DOWN_MASK));
        format.setMnemonic(VK_O);
        format.add(lineWrap);
        format.add(font);
        font.setMnemonic(VK_F);
        lineWrap.setMnemonic(VK_W);
        help.setMnemonic(VK_H);
        help.add(viewHelp);
        viewHelp.setMnemonic(VK_H);
        help.addSeparator();
        help.add(about);
        about.setMnemonic(VK_A);
    }

    @SuppressWarnings("unused")
    public void restartToApply() {
        int choose = JOptionPane.showConfirmDialog(this, "重启后生效，是否重启？", "提示", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (choose == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "点击确定重启程序，将在10秒后重启", "警告", JOptionPane.WARNING_MESSAGE);
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.exit(0);
        }
    }
}
