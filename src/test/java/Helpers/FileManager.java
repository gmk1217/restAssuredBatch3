package Helpers;

public class FileManager {

    private static ConfigReader configReader = new ConfigReader();
    private static FileManager fileManager = new FileManager();

    private FileManager(){

    }

    public static FileManager getInstance(){
        return fileManager;
    }

    public static ConfigReader getConfigReader() {
        return configReader;
    }
}
