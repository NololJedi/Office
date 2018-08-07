package by.office.message;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Locale;
import java.util.ResourceBundle;

public class LoggerMessageManager implements MessageManager {

    private static final Logger LOGGER = LogManager.getLogger(LoggerMessageManager.class);

    public static final String GREETING_MESSAGE_KEY = "greeting";
    public static final String INSTRUCTION_MESSAGE_KEY = "instruction";
    public static final String NOT_VALID_FILE_NAME_MESSAGE_KEY = "notValidFileName";
    public static final String ERROR_READING_FILE_MESSAGE_KEY = "fileReadFail";
    public static final String EXCEPTION_FILE_READING_MESSAGE_KEY = "exceptionFileReading";
    public static final String NO_DATA_TO_ANALYZE_MESSAGE_KEY = "noDataToAnalyze";
    public static final String NOT_VALID_DATA_MESSAGE_KEY = "notValidData";
    public static final String NO_PERIODS_WERE_FOUND_MESSAGE_KEY = "noPeriodsWereFound";
    public static final String COUNT_MESSAGE_KEY = "count";
    public static final String PERIODS_MESSAGE_KEY = "periods";

    private static final String RESOURCE_FILE_NAME = "messages";

    private ResourceBundle resourceBundle;

    public LoggerMessageManager() {
       resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE_NAME);
    }

    public LoggerMessageManager(Locale locale){
        resourceBundle = ResourceBundle.getBundle(RESOURCE_FILE_NAME, locale);
    }

    @Override
    public  void renderMessage(String key){
        LOGGER.info(resourceBundle.getString(key));
    }

    @Override
    public void renderMessage(String key, String messagePart){
        LOGGER.info(String.format(resourceBundle.getString(key), messagePart));
    }

    @Override
    public void renderException(String key, Exception exception) {
        LOGGER.info(resourceBundle.getString(key));
        LOGGER.error(exception);
    }

}
