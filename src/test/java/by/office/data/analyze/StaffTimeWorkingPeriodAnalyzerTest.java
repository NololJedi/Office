package by.office.data.analyze;

import by.office.data.StaffTimeWorkingPeriod;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class StaffTimeWorkingPeriodAnalyzerTest {

  private final StaffTimeWorkingPeriodAnalyzer analyzer = new StaffTimeWorkingPeriodAnalyzer();

  @Test
  public void analyze_FourPeriodsInOneRange_Success() {
    // given
    StaffTimeWorkingPeriod first = new StaffTimeWorkingPeriod(800, 907);
    StaffTimeWorkingPeriod second = new StaffTimeWorkingPeriod(920, 1035);
    StaffTimeWorkingPeriod third = new StaffTimeWorkingPeriod(1100, 1600);
    StaffTimeWorkingPeriod fourth = new StaffTimeWorkingPeriod(1000, 1030);
    StaffTimeWorkingPeriod fifth = new StaffTimeWorkingPeriod(1020, 1130);
    StaffTimeWorkingPeriod six = new StaffTimeWorkingPeriod(1030, 1715);
    List<StaffTimeWorkingPeriod> periods = Arrays.asList(first, second, third, fourth, fifth, six);

    // when
    int expectedCount = 4;
    List<StaffTimeWorkingPeriod> expectedPeriods = Arrays.asList(second, fourth, fifth, six);
    AnalyzeResult expectedResult = new AnalyzeResult(expectedCount, expectedPeriods);
    AnalyzeResult actualResult = analyzer.analyze(periods);

    // then
    Assert.assertEquals(expectedResult.getCount(), actualResult.getCount());

  }

  @Test
  public void analyze_ThreePeriodsInOneRange_Success() {
    // given
    StaffTimeWorkingPeriod first = new StaffTimeWorkingPeriod(800, 907);
    StaffTimeWorkingPeriod second = new StaffTimeWorkingPeriod(900, 1000);
    StaffTimeWorkingPeriod third = new StaffTimeWorkingPeriod(1100, 1600);
    StaffTimeWorkingPeriod fourth = new StaffTimeWorkingPeriod(1000, 1030);
    StaffTimeWorkingPeriod fifth = new StaffTimeWorkingPeriod(2020, 2130);
    StaffTimeWorkingPeriod six = new StaffTimeWorkingPeriod(709, 900);
    List<StaffTimeWorkingPeriod> periods = Arrays.asList(first, second, third, fourth, fifth, six);

    // when
    int expectedCount = 3;
    List<StaffTimeWorkingPeriod> expectedPeriods = Arrays.asList(first, second, six);
    AnalyzeResult expectedResult = new AnalyzeResult(expectedCount, expectedPeriods);
    AnalyzeResult actualResult = analyzer.analyze(periods);

    // then
    Assert.assertEquals(expectedResult.getCount(), actualResult.getCount());

  }
}
