package se.liu.hanba478henan555;

import java.io.IOException;
import java.util.logging.*;

/**
 * Logging manager
 * With every try-catch statement the logger LOGR can be used to log the error
 *
 * for TDDD78 at LIU 2022-03-25
 * 	hanba478@student.liu.se
 * 	henan555@student.liu.se
 */
public class LoggingManager
{
    private final static Logger LOGR = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

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
            System.exit(1);
        }
    }

    public static Logger getLogr(){
        return LOGR;
    }


}
