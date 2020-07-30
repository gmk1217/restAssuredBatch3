package Helpers;

import java.io.File;

public class Payload {

    public static File postUser(){
        String localDir = System.getProperty("user.dir");
        File postFile = new File(localDir+Constants.postFilePath);
        return postFile;
    }
}
