package logic;

import java.io.Serializable;

/**
 * Created by flo on 11.05.16.
 */
public class BackupJob implements Serializable {

    private String strName;
    private String strSource;
    private String strDestination;

    public String getStrCustomArguments() {
        return strCustomArguments;
    }

    public void setStrCustomArguments(String strCustomArguments) {
        this.strCustomArguments = strCustomArguments;
    }

    private String strCustomArguments;


    public BackupJob(String name, String src, String dst, String args){
        this.strName = name;
        this.strSource = src;
        this.strDestination = dst;
        this.strCustomArguments = args;

    }

    public String getName() {
        return strName;
    }

    public void setName(String strName) {
        this.strName = strName;
    }

    public String getSource() {
        return strSource;
    }

    public void setSource(String strSource) {
        this.strSource = strSource;
    }

    public String getDestination() {
        return strDestination;
    }

    public void setDestination(String strDestination) {
        this.strDestination = strDestination;
    }


}
