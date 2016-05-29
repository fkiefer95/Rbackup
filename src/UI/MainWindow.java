package UI;

import logic.BackupJobManager;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by flo on 19.05.16.
 */
public class MainWindow {

    //declaration of UI-Elements
    private JPanel panelMainWindowPanel;
    private JTextField textFieldJobDestination;
    private JTextField textFieldJobName;
    private JTextField textFieldJobSource;
    private JButton buttonRemove;
    private JButton buttonAdd;
    private JButton buttonExecute;
    private JList jlistJobs;


    //initialization of the Backupjobmanager instance to use
    BackupJobManager backMan = new BackupJobManager();


    /**
     * Constructor
     */
    public MainWindow() {





        //Action Listeners for the execute Button
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            //    backMan.runBackubJob(jlistJobs.getSelectedIndex());
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backMan.createBackubJob(textFieldJobName.getText(), textFieldJobSource.getText(), textFieldJobDestination.getText());
                //update ComboBox
                populateComboBox();
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("MainWindow");
        MainWindow windowMain = new MainWindow();
        frame.setContentPane(windowMain.panelMainWindowPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Method populateComboBox
     * populates the UIs Combobox with the names of the currently saved backubjobs
     */
    private void populateComboBox() {


    }



}
