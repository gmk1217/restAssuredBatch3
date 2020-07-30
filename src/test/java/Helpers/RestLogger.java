package Helpers;

import java.io.*;
import java.util.Properties;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class RestLogger {

   private static Logger logger= null;
   private static RestLogger restLog = null;

    public static RestLogger getLogger(Class<?> T) {
        if (logger == null) {
            Properties props = new Properties();
            String propPath = System.getProperty("user.dir") + File.separator + "src/test/java/logger.properties";
            try {
                InputStream iS = new FileInputStream(propPath);
                props.load(iS);
            } catch (IOException e) {
                e.printStackTrace();
            }
            PropertyConfigurator.configure(props);
            logger = Logger.getLogger(T);
            restLog = new RestLogger();

        }
        return restLog;
    }

    public void info(Object msg){
        logger.info(msg);
    }
    public void debug(Object msg){
        logger.debug(msg);
    }
    public void warn(Object msg){
        logger.warn(msg);
    }
    public void error(Object msg){
        logger.error(msg);
    }
}
