package by.office.data.analyze;

import by.office.data.StaffTimeWorkingPeriod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StaffTimeWorkingPeriodAnalyzer {

  public AnalyzeResult analyze(List<StaffTimeWorkingPeriod> periods) {
    List<StaffTimeWorkingPeriod> actualPeriods = Collections.emptyList();
    List<StaffTimeWorkingPeriod> checkingPeriods;
    int count = 0;

    for (int index = 0; index < periods.size(); index++) {
      StaffTimeWorkingPeriod currentPeriod = periods.get(index);
      checkingPeriods = new ArrayList<>();

      for (int innerIndex = 0; innerIndex < periods.size(); innerIndex++) {
        StaffTimeWorkingPeriod checkingPeriod = periods.get(innerIndex);
        if (innerIndex == index) {
          continue;
        }
        if (checkTwoPeriodsIsInOneRange(currentPeriod, checkingPeriod)) {
          if (checkingPeriods.isEmpty()) {
            checkingPeriods.add(checkingPeriod);
          } else {
            int currentCount = 0;

            for (StaffTimeWorkingPeriod actualPeriod : checkingPeriods) {

              if (checkTwoPeriodsIsInOneRange(checkingPeriod, actualPeriod)) {
                currentCount++;
              }
            } // for №3
            if (currentCount == checkingPeriods.size()) {
              checkingPeriods.add(checkingPeriod);
            }
          }
        }
      } // for №2
      checkingPeriods.add(currentPeriod);
      if (count < checkingPeriods.size()) {
        count = checkingPeriods.size();
        actualPeriods = new ArrayList<>(checkingPeriods);
      }
    } // for №1

    Collections.sort(actualPeriods);
    return new AnalyzeResult(count, actualPeriods);
  }

  private boolean checkTwoPeriodsIsInOneRange(
      StaffTimeWorkingPeriod first, StaffTimeWorkingPeriod second) {

    int firstStart = first.getStart();
    int firstEnd = first.getEnd();
    int secondStart = second.getStart();
    int secondEnd = second.getEnd();

    if (firstStart <= secondStart && secondStart <= firstEnd) {
      return true;
    }

    if (firstStart <= secondEnd && secondEnd <= firstEnd) {
      return true;
    }

    if (secondStart <= firstStart && firstStart <= secondEnd) {
      return true;
    }

    return secondStart <= firstEnd && firstEnd <= secondEnd;
  }
}
