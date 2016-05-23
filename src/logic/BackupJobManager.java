package logic;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by flo on 12.05.16.
 */
public class BackupJobManager {
    /**Method getJob names
     * returns an array with the names of all the jobs
     * @return list of job names
     */
    public String[] getJobNames() {
        List<String> stringsJobNames = new ArrayList<String>();

        for (BackupJob temp : listJobs){
            stringsJobNames.add(temp.getName());
        }

        String[] arrayStringsJobNames = new String[stringsJobNames.size()];
        arrayStringsJobNames = stringsJobNames.toArray(arrayStringsJobNames);
        return  arrayStringsJobNames;
    }

    public BashAdapter getBashAdapter() {
        return bashAdapter;
    }

    private ArrayList<BackupJob> listJobs = null;
    private BashAdapter bashAdapter;

    private String strParams = "-auv";

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
    private boolean fileExists(String strPath){

        File fileToCheck = new File(strPath);

        if(fileToCheck.exists() && !fileToCheck.isDirectory()) {
            return true;
        }
        else return false;
    }

    public void createBackubJob(String name, String src, String dst){
        listJobs.add(new BackupJob(name,src,dst,""));
        saveJobList();
    }

    public void createBackubJob(String name, String src, String dst, String args){
        listJobs.add(new BackupJob(name,src,dst,args));
        saveJobList();
    }
    //TODO useless?
    public String runBackupJob(String jobName){
       return bashAdapter.executeBackubJob(findJobByName(jobName),strParams); }


    public String runBackubJob(int jobIndex){
        return bashAdapter.executeBackubJob(listJobs.get(jobIndex), strParams);
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

    //method to save joblist
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
