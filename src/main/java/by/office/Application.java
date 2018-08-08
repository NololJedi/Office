package by.office;

import by.office.data.StaffTimeWorkingPeriodDataProcessor;
import by.office.data.extractor.StaffTimeWorkingPeriodDataExtractor;
import by.office.data.extractor.StaffTimeWorkingPeriodDataFromTxtFileExtractor;
import by.office.message.LoggerMessageManager;
import by.office.message.MessageManager;

public class Application {

  public static void main(String[] args) {
    MessageManager messageManager = new LoggerMessageManager();
    StaffTimeWorkingPeriodDataExtractor extractor =
        new StaffTimeWorkingPeriodDataFromTxtFileExtractor(messageManager);
    StaffTimeWorkingPeriodDataProcessor analyzer = new StaffTimeWorkingPeriodDataProcessor(messageManager);

    analyzer.process(extractor.extract());
  }
}
