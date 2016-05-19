package UI;

import logic.BackupJobManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by flo on 19.05.16.
 */
public class MainWindow {
    private JPanel panelMainWindowPanel;
    private JComboBox comboBoxJobs;
    private JTextField textFieldJobDestination;
    private JTextField textFieldJobName;
    private JTextField textFieldJobSource;
    private JButton buttonRemove;
    private JButton buttonAdd;
    private JButton buttonExecute;

    BackupJobManager backMan = new BackupJobManager();

    public MainWindow() {
        //set visibility

        //populate Combo Bux with Elements
        comboBoxJobs = new JComboBox(backMan.getListJobs().toArray());

        //Action Listeners for the execute Button
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backMan.runBackubJob(comboBoxJobs.getSelectedIndex());
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backMan.createBackubJob(textFieldJobName.getText(),textFieldJobSource.getText(),textFieldJobDestination.getText());
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        frame.setContentPane(new MainWindow().panelMainWindowPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
