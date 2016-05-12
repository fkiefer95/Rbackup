package logic;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * Created by flo on 12.05.16.
 */
public class BackubJobManager {

    public ArrayList<BackubJob> getListJobs() {
        return listJobs;
    }

    private ArrayList<BackubJob> listJobs = null;

    public BackubJobManager(){
        if(!fileExists("~/.Rbackup/jobs")){

            listJobs = new ArrayList<BackubJob>();


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
                listJobs = (ArrayList<BackubJob>) ois.readObject();
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
        listJobs.add(new BackubJob(name,src,dst));
    }


}
