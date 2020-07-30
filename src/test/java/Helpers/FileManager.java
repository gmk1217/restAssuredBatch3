package Helpers;

public class FileManager {

    private static ConfigReader configReader;
    private static FileManager fileManager = new FileManager();

    private FileManager(){

    }

    public static FileManager getInstance(){
        return fileManager;
    }

    public ConfigReader getConfigReader() {
        return(configReader== null)? new ConfigReader(): configReader;
    }
}
