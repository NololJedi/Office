package by.office.message;

public interface MessageManager {

    void renderMessage(String key);

    void renderMessage(String key, String messagePart);

    void renderException(String key, Exception exception);
}
