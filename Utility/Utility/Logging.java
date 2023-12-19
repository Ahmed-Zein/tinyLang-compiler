package Utility;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

public class Logging {
    private static Logger logger;

    private Logging() {
    }
    // private void init() {
    // logger.info("This is an info message");
    // logger.severe("This is an error message"); // == ERROR
    // logger.fine("Here is a debug message"); // == DEBUG
    // }

    public static Logger Instance() {
        if (logger == null) {
            logger = Logger.getLogger("Logging");
            FileHandler fileHandler;
            try {
                fileHandler = new FileHandler("status.log");
                logger.addHandler(fileHandler);
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return logger;
    }
}
