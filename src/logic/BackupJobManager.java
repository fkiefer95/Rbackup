package logic;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by flo on 12.05.16.
 */
public class BackupJobManager {

    public ArrayList<BackupJob> getListJobs() {
        return listJobs;
    }

    private ArrayList<BackupJob> listJobs = null;
    private BashAdapter bashAdapter;

    private String strParams = "-auv";

    public BackupJobManager(){
        if(!fileExists("~/.Rbackup/jobs")){

            listJobs = new ArrayList<BackupJob>();


            try{
                FileOutputStream fos= new FileOutputStream("jobs");
                ObjectOutputStream oos= new ObjectOutputStream(fos);
                oos.writeObject(listJobs);
                fos.close();
                oos.close();
            }
            catch(Exception excSaving){
                javax.swing.JOptionPane.showMessageDialog(null, "Fehler beim Speichern", "Fehler", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }


        }

        else {
            try {
                FileInputStream fis = new FileInputStream("jobs");
                ObjectInputStream ois = new ObjectInputStream(fis);
                listJobs = (ArrayList<BackupJob>) ois.readObject();
                ois.close();
                fis.close();
            }
            catch(Exception excLoading){
                javax.swing.JOptionPane.showMessageDialog(null, "Fehler beim Laden", "Fehler", JOptionPane.ERROR_MESSAGE);
                System.exit(-1);
            }
        }

    }
    private boolean fileExists(String strPath){

        File fileToCheck = new File(strPath);

        if(fileToCheck.exists() && !fileToCheck.isDirectory()) {
            return true;
        }
        else return false;
    }

    public void createBackubJob(String name, String src, String dst){
        listJobs.add(new BackupJob(name,src,dst,""));
    }

    public void createBackubJob(String name, String src, String dst, String args){
        listJobs.add(new BackupJob(name,src,dst,args));
    }

    public String runBackupJob(String jobName){
       return bashAdapter.executeBackubJob(findJobByName(jobName),strParams);

    }

    private BackupJob findJobByName(String name){
        for(BackupJob temp : listJobs){
            if (temp.getName().equals(name)){
                return temp;
            }
        }
        return null;
    }

    public String getStrParams() {
        return strParams;
    }

    public void setStrParams(String strParams) {
        this.strParams = strParams;
    }

}
