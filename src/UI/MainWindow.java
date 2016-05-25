package UI;

import logic.BackupJob;
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
    private JList jListJobSelector;
    private DefaultListModel modelJobSelector = new DefaultListModel();


    //initialization of the Backupjobmanager instance to use
    BackupJobManager backMan = new BackupJobManager();

    /**
     * Constructor
     */
    public MainWindow() {

        //initialize and populate listModel


        //initialite Jlist
        populateJlist();

        //Action Listeners for the execute Button
        buttonExecute.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backMan.runBackubJob(jListJobSelector.getSelectedIndex());
            }
        });
        buttonAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backMan.createBackubJob(textFieldJobName.getText(), textFieldJobSource.getText(), textFieldJobDestination.getText());
                //update ComboBox
                populateJlist();
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

    /**
     * Method populateListModel
     * populates the UIs Combobox with the names of the currently saved backubjobs
     */
    private void populateJlist() {
        //wew lad
        //BackupJob[] arrayJobs = backMan.getListJobs().toArray(new BackupJob[backMan.getListJobs().size()]);

        for (BackupJob temp : backMan.getListJobs()){
            modelJobSelector.addElement(temp);
        }

        jListJobSelector = new JList(modelJobSelector);
        jListJobSelector.setCellRenderer(new BackupJobCellRenderer());


    }
}
