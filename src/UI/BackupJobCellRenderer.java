package UI;

/**
 * Created by flo on 25.05.16.
 */

import logic.BackupJob;

import javax.swing.*;
import java.awt.*;

class BackupJobCellRenderer extends JLabel implements ListCellRenderer {
    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    public BackupJobCellRenderer() {
        setOpaque(true);
        setIconTextGap(12);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        BackupJob bjob = (BackupJob) value;
        setText(bjob.getName());

        if (isSelected) {
            setBackground(HIGHLIGHT_COLOR);
            setForeground(Color.white);
        } else {
            setBackground(Color.white);
            setForeground(Color.black);
        }
        return this;
    }
}

