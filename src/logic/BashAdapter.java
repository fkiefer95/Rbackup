package logic;

import java.io.InputStreamReader;
import org.apache.commons.io.*;
/**
 * Created by flo on 11.05.16.
 */
public class BashAdapter {

    Runtime bash = Runtime.getRuntime();

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
}
