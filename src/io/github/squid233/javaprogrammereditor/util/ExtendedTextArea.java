package io.github.squid233.javaprogrammereditor.util;

import javax.swing.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

/**
 * @author squid233
 */
public class ExtendedTextArea extends JTextArea {

    public boolean isClipboardHasString() {
        boolean b = false;
        Clipboard clipboard = this.getToolkit().getSystemClipboard();
        Transferable content = clipboard.getContents(this);
        try {
            if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
                b = true;
            }
        } catch (UnsupportedFlavorException | IOException e) {
            System.err.println("Throws exception cause by: " + e);
        }
        return b;
    }

    public boolean canCopy() {
        boolean b = false;
        int start = this.getSelectionStart();
        int end = this.getSelectionEnd();
        if (start != end) {
            b = true;
        }
        return b;
    }

    public void delete() {
        this.replaceRange(null, this.getSelectionStart(), this.getSelectionEnd());
    }

}
