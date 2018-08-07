package by.office.data.analyze;

import by.office.data.StaffTimeWorkingPeriod;
import by.office.data.builder.StaffTimeWorkingPeriodCreator;
import by.office.data.builder.StaffTimeWorkingPeriodFromTxtFileCreator;
import by.office.message.MessageManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static by.office.message.LoggerMessageManager.*;

public class StaffTimeWorkingPeriodAnalyzer {

  private MessageManager messageManager;

  public StaffTimeWorkingPeriodAnalyzer(MessageManager messageManager) {
    this.messageManager = messageManager;
  }

  public void analyze(Optional<List<String>> data) {
    List<StaffTimeWorkingPeriod> periods = prepareData(data);

    if (periods.isEmpty()) {
      messageManager.renderMessage(NO_DATA_TO_ANALYZE_MESSAGE_KEY);
      System.exit(0);
    }

    List<StaffTimeWorkingPeriod> actualPeriods = Collections.emptyList();
    int count = 0;

    for (int index = 0; index < periods.size(); index++) {
      StaffTimeWorkingPeriod currentPeriod = periods.get(index);
      actualPeriods = new ArrayList<>();

      for (int innerIndex = 0; innerIndex < periods.size(); innerIndex++) {
        StaffTimeWorkingPeriod checkingPeriod = periods.get(index);
        if (innerIndex == index) {
          continue;
        }
        if (checkTwoPeriodsIsInOneRange(currentPeriod, checkingPeriod)) {
          if (actualPeriods.isEmpty()) {
            actualPeriods.add(checkingPeriod);
          }
          int currentCount = 0;

          for (StaffTimeWorkingPeriod actualPeriod : actualPeriods) {
            if (checkTwoPeriodsIsInOneRange(checkingPeriod, actualPeriod)) {
              currentCount++;
            }
          } // for №3
          if (currentCount == actualPeriods.size()) {
            actualPeriods.add(checkingPeriod);
          }
        }
      } // for №2
      actualPeriods.add(currentPeriod);
      if (count < actualPeriods.size()) {
        count = actualPeriods.size();
      }
    } // for №1

    if (actualPeriods.isEmpty()) {
      messageManager.renderMessage(NO_PERIODS_WERE_FOUND_MESSAGE_KEY);
    } else {
      messageManager.renderMessage(COUNT_MESSAGE_KEY, String.valueOf(count));
      messageManager.renderMessage(PERIODS_MESSAGE_KEY, periods.toString());
    }
  }

  private boolean checkTwoPeriodsIsInOneRange(
      StaffTimeWorkingPeriod first, StaffTimeWorkingPeriod second) {

    int firstStart = first.getStart();
    int firstEnd = first.getEnd();
    int secondStart = second.getStart();
    int secondEnd = second.getEnd();

    return (firstStart <= secondStart && secondStart <= firstEnd)
        || (firstStart <= secondEnd && secondEnd <= firstEnd)
        || (secondStart <= firstStart && firstStart <= secondEnd)
        || (secondStart <= firstEnd && firstEnd <= secondEnd);
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

    return periods;
  }
}
