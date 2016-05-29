package logic;

import org.apache.commons.io.IOUtils;

import java.text.MessageFormat;
/**
 * Created by flo on 11.05.16.
 */
public class BashAdapter {

    //initialize Runtime instance
    private Runtime bash = null;
    //initialize template Terminal command String
    private String strTemplateCommand = "rsync -{0} {1} rbackup@{2}/home/backups{3}";
    //initialize MessageFormat instance for building the cmd String to run
    private MessageFormat mf = new MessageFormat(strTemplateCommand);

    public BashAdapter(){
        bash = Runtime.getRuntime();
    }

    /**Method runCommand
     * runs the given
     * @param strCommand Command to run
     * @return Console output
     */
    private String runCommand(String strCommand){
        //declare new Process
        Process procCmd = null;
        //declare Return String
        String strReturn = "";


        //execute Command in Process procCmd
        try {
            procCmd = bash.exec(strCommand);
            procCmd.waitFor();
            strReturn = IOUtils.toString(procCmd.getInputStream(), "UTF-8");
            }
        //if something goes wrong, return the errormessage
        catch(Exception excProcess){
            try {
                strReturn = IOUtils.toString(procCmd.getErrorStream(), "UTF-8");
            }
            catch(Exception excRead){
                strReturn= "aua...";
            }
        }

        return strReturn;
    }

    /**Method executeBackupJob
     *executes a given BackupJob using the runCommand Method
     * @param job BackupJob to run
     *
     * @return Console output
     */
    public String executeBackubJob(BackupJob job){
        System.out.println("Ausfuehren: " + job.getName());
        //build command String from Template
        String strCommand = "rsync "+job.getStrCustomArguments()+" "+ job.getSource()+" "+job.getDestination() ;
        System.out.println(strCommand);
        //execute Command and return the cmdline output
        return runCommand(strCommand);

    }


}
