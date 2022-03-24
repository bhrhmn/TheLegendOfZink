package se.liu.hanba478henan555;

import java.io.IOException;
import java.util.logging.*;

/**
 * Logging manager
 * Used to log errors
 */
public class LoggingManager
{
    public final static Logger LOGR = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public static void setUpLogger() {
        LogManager.getLogManager().reset();
        LOGR.setLevel(Level.ALL);

        ConsoleHandler ch = new ConsoleHandler();
        ch.setLevel(Level.SEVERE);
        LOGR.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("myLogger.log", true);
            fh.setLevel(Level.FINE);
            LOGR.addHandler(fh);
        } catch (IOException e) {
            LOGR.log(Level.SEVERE, "Kan ej logga", e);
        }
    }


}
