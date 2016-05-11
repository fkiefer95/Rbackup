package logic;

import java.io.InputStreamReader;
import java.text.MessageFormat;

import org.apache.commons.io.*;
/**
 * Created by flo on 11.05.16.
 */
public class BashAdapter {

    private Runtime bash = Runtime.getRuntime();
    private String strTemplateCommand = "rsync {0} {1} rbackup@{2}/home/backups/{3}";
    private MessageFormat mf = new MessageFormat(strTemplateCommand);

    public String runCommand(String strCommand){
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

    public String executeBackubJob(BackubJob job, String params){
        //build command String from Template
        String strCommand = mf.format(params,job.getSource(),job.getDestination(), job.getName());
        //execute Command and return the cmdline output
        return runCommand(strCommand);

    }
}
