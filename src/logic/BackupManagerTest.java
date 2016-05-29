package logic;

/**
 * Created by flo on 28.05.16.
 */
public class BackupManagerTest {

    public static void main(String[] args){
        System.out.println("start");
        runTest();

    }

    public static void runTest(){
        System.out.println("gestartet");
        BackupJobManager backMan = new BackupJobManager();
        System.out.println("erzeugt");
        backMan.createBackubJob("Test","/home/flo/aaa", "/home/flo/bbb");
        System.out.println("erzeugt 2");
        System.out.println(backMan.runBackubJob(backMan.findJobByName("Test")));
        System.out.println("fertig");
    }
}
