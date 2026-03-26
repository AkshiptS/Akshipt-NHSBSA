package utils;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class LoggerUtils {
    private LoggerUtils() {}

    public static Logger getLogger(Class<?> clazz) {
        return LoggerFactory.getLogger(clazz);
    }
}
