package by.office.data.extractor;

import by.office.message.MessageManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static by.office.message.LoggerMessageManager.EXCEPTION_FILE_READING_MESSAGE_KEY;

public class StaffTimeWorkingPeriodFileReader {
  private MessageManager messageManager;

  public StaffTimeWorkingPeriodFileReader(MessageManager messageManager) {
    this.messageManager = messageManager;
  }

  public Optional<List<String>> readStaffsTimeFromFile(String fileName) {
    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
      List<String> staffsTime = new ArrayList<>();
      while (bufferedReader.ready()) {
        staffsTime.add(bufferedReader.readLine());
      }
      return Optional.of(staffsTime);
    } catch (IOException exception) {
      messageManager.renderException(EXCEPTION_FILE_READING_MESSAGE_KEY, exception);
      return Optional.empty();
    }
  }
}
