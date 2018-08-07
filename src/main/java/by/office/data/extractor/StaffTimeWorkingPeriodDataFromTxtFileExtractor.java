package by.office.data.extractor;

import by.office.message.LoggerMessageManager;
import by.office.message.MessageManager;
import by.office.utils.FileNameValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;

import static by.office.message.LoggerMessageManager.GREETING_MESSAGE_KEY;
import static by.office.message.LoggerMessageManager.INSTRUCTION_MESSAGE_KEY;

public class StaffTimeWorkingPeriodDataFromTxtFileExtractor
    implements StaffTimeWorkingPeriodDataExtractor {

  private static final Logger LOGGER =
      LogManager.getLogger(StaffTimeWorkingPeriodDataFromTxtFileExtractor.class);
  private static final String EMPTY_FILE_NAME = "empty";
  private static final String EXIT_COMMAND = "exit";

  private MessageManager messageManager;

  public StaffTimeWorkingPeriodDataFromTxtFileExtractor(MessageManager messageManager) {
    this.messageManager = messageManager;
  }

  @Override
  public Optional<List<String>> extract() {
      messageManager.renderMessage(GREETING_MESSAGE_KEY);
    try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
        messageManager.renderMessage(INSTRUCTION_MESSAGE_KEY);
      String fileName = EMPTY_FILE_NAME;

      while (EMPTY_FILE_NAME.equals(fileName)) {
        fileName = bufferedReader.readLine();
        if (EXIT_COMMAND.equals(fileName)){
            System.exit(0);
        }

        boolean isFileNameValid = FileNameValidator.isFileNameValid(fileName);
        if (!isFileNameValid) {
          messageManager.renderMessage(LoggerMessageManager.NOT_VALID_FILE_NAME_MESSAGE_KEY);
          fileName = EMPTY_FILE_NAME;
        } else {
          File file = new File(fileName);
          if (!file.canRead()) {
            messageManager.renderMessage(
                LoggerMessageManager.ERROR_READING_FILE_MESSAGE_KEY, fileName);
            fileName = EMPTY_FILE_NAME;
          }
        }
      }

      StaffTimeWorkingPeriodFileReader staffTimeWorkingPeriodFileReader =
          new StaffTimeWorkingPeriodFileReader(messageManager);
      return staffTimeWorkingPeriodFileReader.readStaffsTimeFromFile(fileName);

    } catch (IOException exception) {
      LOGGER.error(exception);
      return Optional.empty();
    }
  }
}
