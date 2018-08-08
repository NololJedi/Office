package by.office.data;

import by.office.data.analyze.AnalyzeResult;
import by.office.data.analyze.StaffTimeWorkingPeriodAnalyzer;
import by.office.data.builder.StaffTimeWorkingPeriodCreator;
import by.office.data.builder.StaffTimeWorkingPeriodFromTxtFileCreator;
import by.office.message.MessageManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.office.message.LoggerMessageManager.*;

public class StaffTimeWorkingPeriodDataProcessor {

  private MessageManager messageManager;

  public StaffTimeWorkingPeriodDataProcessor(MessageManager messageManager) {
    this.messageManager = messageManager;
  }

  public void process(Optional<List<String>> data) {
    List<StaffTimeWorkingPeriod> periods = prepareData(data);

    if (periods.isEmpty()) {
      messageManager.renderMessage(NO_DATA_TO_ANALYZE_MESSAGE_KEY);
      System.exit(0);
    }

    StaffTimeWorkingPeriodAnalyzer analyzer = new StaffTimeWorkingPeriodAnalyzer();
    AnalyzeResult result = analyzer.analyze(periods);

    if (result.getPeriods().isEmpty()) {
      messageManager.renderMessage(NO_PERIODS_WERE_FOUND_MESSAGE_KEY);
    } else {
      messageManager.renderMessage(COUNT_MESSAGE_KEY, String.valueOf(result.getCount()));
      messageManager.renderMessage(PERIODS_MESSAGE_KEY, result.getPeriods().toString());
    }
  }

  private List<StaffTimeWorkingPeriod> prepareData(Optional<List<String>> data) {
    if (!data.isPresent()) {
      return Collections.emptyList();
    }

    List<String> dataToPrepare = data.get();
    List<String> notValidData = new ArrayList<>();
    List<StaffTimeWorkingPeriod> periods = new ArrayList<>();
    StaffTimeWorkingPeriodCreator<String> creator = new StaffTimeWorkingPeriodFromTxtFileCreator();

    for (String value : dataToPrepare) {
      Optional<StaffTimeWorkingPeriod> currentPeriod = creator.create(value);

      if (currentPeriod.isPresent()) {
        periods.add(currentPeriod.get());
      } else {
        notValidData.add(value);
      }
    }

    if (!notValidData.isEmpty()) {
      messageManager.renderMessage(NOT_VALID_DATA_MESSAGE_KEY, notValidData.toString());
    }

    if (notValidData.size() == dataToPrepare.size()) {
      messageManager.renderMessage(NOT_VALID_DATA_MESSAGE_KEY, notValidData.toString());
      System.exit(0);
    }

    return periods;
  }
}
