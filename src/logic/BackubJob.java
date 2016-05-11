package logic;

import java.io.Serializable;

/**
 * Created by flo on 11.05.16.
 */
public class BackubJob implements Serializable {

    private String strName;
    private String strSource;
    private String strDestination;


    public BackubJob(String name, String src, String dst){
        this.strName = name;
        this.strSource = src;
        this.strDestination = dst;

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
