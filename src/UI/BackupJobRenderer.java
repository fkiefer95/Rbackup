package UI;

/**
 * Created by flo on 25.05.16.
 */

import logic.BackupJob;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import java.awt.*;

public class BackupJobRenderer extends BasicComboBoxRenderer {

    private static final Color HIGHLIGHT_COLOR = new Color(0, 0, 128);

    @Override
    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        JLabel label = (JLabel)super.getListCellRendererComponent(list,value,index,isSelected,cellHasFocus);

        if(value !=null && value instanceof BackupJob) {
            BackupJob backup = (BackupJob) value;
            System.out.println("bjobrenderer");
            label.setText(backup.getName());
        }

            if (isSelected) {
                label.setBackground(HIGHLIGHT_COLOR);
                label.setForeground(Color.white);
            } else {
                label.setBackground(Color.white);
                label.setForeground(Color.black);
            }
       // }
        return label;
    }
}

