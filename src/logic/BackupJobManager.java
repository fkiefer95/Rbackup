package logic;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by flo on 12.05.16.
 */
public class BackupJobManager {



    //declare job list for use in constructor
    private ArrayList<BackupJob> listJobs = null;
    //declare instance of BashAdapter
    private BashAdapter bashAdapter;
    //initialize default Rsync parameter String
    private String strParams = "-auv";

    /**
     * Constructor
     */
    public BackupJobManager(){
        if(!fileExists(System.getProperty("user.home") + File.separator + ".Rbackup/jobFile")){

            listJobs = new ArrayList<BackupJob>();
            saveJobList();

            System.out.println("neue Datei angelegt");
        }

        else {
            try {
                FileInputStream fis = new FileInputStream(System.getProperty("user.home") + File.separator + ".Rbackup/jobFile");
                ObjectInputStream ois = new ObjectInputStream(fis);
                listJobs = (ArrayList<BackupJob>) ois.readObject();
                ois.close();
                fis.close();
            }
            catch(Exception excLoading){
                javax.swing.JOptionPane.showMessageDialog(null, "Fehler beim Laden", "Fehler", JOptionPane.ERROR_MESSAGE);
                excLoading.printStackTrace();
                System.exit(-1);
            }
        }

    }

    /**Method fileExists
     * checks whether a file with the given path exists
     * @param strPath path to file
     * @return true if it exists, false if it doesn't
     */
    private boolean fileExists(String strPath){

        File fileToCheck = new File(strPath);

        if(fileToCheck.exists() && !fileToCheck.isDirectory()) {
            return true;
        }
        else return false;
    }

    /**method createBackupJob
     * creates a new Backupjob in the listJobs
     * @param name job name
     * @param src backup source path
     * @param dst backup destination path
     */
    public void createBackubJob(String name, String src, String dst){
        listJobs.add(new BackupJob(name,src,dst,""));
        saveJobList();
    }

    /**method createBackupJob
     * creates a new Backupjob in the listJobs with custom params for Rsync
     * @param name job name
     * @param src backup source path
     * @param dst backup destination path
     * @param args custom rsync params
     */
    public void createBackubJob(String name, String src, String dst, String args){
        listJobs.add(new BackupJob(name,src,dst,args));
        saveJobList();
    }

    /**Method runBackubJob
     * runs the backupjob with the given name
     * @param jobName name of the job to run
     * @return Rsync Console output
     */
    public String runBackupJob(String jobName){
       return bashAdapter.executeBackubJob(findJobByName(jobName),strParams); }

    /**Method runBackubJob
     * runs the backupjob with the given Index
     * @param jobIndex Index of the job to run
     * @return Rsync Console output
     */
    public String runBackubJob(int jobIndex){
        return bashAdapter.executeBackubJob(listJobs.get(jobIndex), strParams);
    }

    /**Method findJobByName
     * returns a backupjob if the given name fits, returns null if no one fits
     * @param name the name of the desired job
     * @return  requested BackupJob
     */
    private BackupJob findJobByName(String name){
        for(BackupJob temp : listJobs){
            if (temp.getName().equals(name)){
                return temp;
            }
        }
        return null;
    }

    /**Method getStrParams
     *
     * @return custom Rsync Parameter String
     */
    public String getStrParams() {
        return strParams;
    }

    /**Method setStrParams
     *sets a custom parameter String
     * @param strParams String of Rsync Parameters
     */
    public void setStrParams(String strParams) {
        this.strParams = strParams;
    }

    /**Method getJobNames
     * returns an array with the names of all the jobs
     * @return list of job names
     */
    public ArrayList<BackupJob> getListJobs() {
        return listJobs;
    }

    /**method getBashAdapter
     * returns the backupjobmanager's bashAdapter instance
     * @return the currently used bash adapter
     */
    public BashAdapter getBashAdapter() {
        return bashAdapter;
    }

    /**Method saveJobList
     * save jobList to file
     */
    private void saveJobList(){
        try{
            String path = System.getProperty("user.home") + File.separator + ".Rbackup/";
            File filePrecursor = new File(path);
            filePrecursor.mkdirs();
            File jobFile = new File(filePrecursor, "jobFile");
            System.out.println(path);
            System.out.println("file created");
            FileOutputStream fos= new FileOutputStream(jobFile);
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            oos.writeObject(listJobs);
            System.out.println("file written");
            fos.close();
            oos.close();
        }
        catch(Exception excSaving){
            javax.swing.JOptionPane.showMessageDialog(null, "Fehler beim Speichern", "Fehler", JOptionPane.ERROR_MESSAGE);
            excSaving.printStackTrace();
            System.exit(-1);

        }
    }




}
