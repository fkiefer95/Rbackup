package logic;

import java.io.Serializable;

/**
 * Created by flo on 11.05.16.
 */
public class BackupJob implements Serializable {
    //declaration of Job properties
    private String strName;
    private String strSource;
    private String strDestination;
    private String strCustomArguments;


    /**
     * Constructor
     * @param name job name
     * @param src backup source directory
     * @param dst backup destination directory
     * @param args custom rsync parameters
     */
    public BackupJob(String name, String src, String dst, String args){
        this.strName = name;
        this.strSource = src;
        this.strDestination = dst;
        this.strCustomArguments = args;

    }

    /**Method getName
     *
     * @return the jobb Name
     */
    public String getName() {
        return strName;
    }

    /**Method setName
     * changes the job's name
     * @param strName the new name
     */
    public void setName(String strName) {
        this.strName = strName;
    }

    /**Method getSource
     *
     * @return the backup source path
     */
    public String getSource() {
        return strSource;
    }

    /**Method setSource
     * sets the source path
     * @param strSource the new sourcePath
     */
    public void setSource(String strSource) {
        this.strSource = strSource;
    }

    /**Method getDestination
     *
     * @return the backup destination path
     */
    public String getDestination() {
        return strDestination;
    }

    /**Method setDestination
     * sets the backup destination
     * @param strDestination backup destination path
     */
    public void setDestination(String strDestination) {
        this.strDestination = strDestination;
    }

    /**method getStrCustomArguments
     *
     * @return the custom rsync params
     */
    public String getStrCustomArguments() {
        return strCustomArguments;
    }

    /**Method setStrCustomArguments
     * sets the custom rsync params
     * @param strCustomArguments the custom params
     */
    public void setStrCustomArguments(String strCustomArguments) {
        this.strCustomArguments = strCustomArguments;
    }

}
