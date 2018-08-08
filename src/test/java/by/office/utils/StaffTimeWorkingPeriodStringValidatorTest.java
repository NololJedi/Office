package by.office.utils;

import org.junit.Assert;
import org.junit.Test;

public class StaffTimeWorkingPeriodStringValidatorTest {

    @Test
    public void isDataValid_ValidData_Success() {
        String data = "10:30 17:15";

    Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataStartHoursOneSymbol_Success() {
        String data = "3:22 11:11";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataEndHoursOneSymbol_Success() {
        String data = "08:00 9:07";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMaxRangeStartHours_Success() {
        String data = "23:22 11:11";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMaxRangeEndHours_Success() {
        String data = "22:59 23:11";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMaxRangeStartMinutes_Success() {
        String data = "23:59 11:11";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMaxRangeEndMinutes_Success() {
        String data = "23:22 11:59";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMinRangeStartHours_Success() {
        String data = "00:22 11:11";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMinRangeEndHours_Success() {
        String data = "22:22 00:11";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMinRangeStartMinutes_Success() {
        String data = "23:00 11:59";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_ValidDataMinRangeEndMinutes_Success() {
        String data = "23:22 11:00";

        Assert.assertTrue(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_FullEmptyDataFail() {
        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(""));
    }

    @Test
    public void isDataValid_NullDataFail() {
        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(null));
    }

    @Test
    public void isDataValid_NotValidDataStartHoursTooMuchValue_Fail() {
        String data = "44:00 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndHoursTooMuchValue_Fail() {
        String data = "22:00 233:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartHoursTooMuchSymbols_Fail() {
        String data = "111:00 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndHoursTooMuchSymbols_Fail() {
        String data = "11:00 111:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartMinutesTooMuchValue_Fail() {
        String data = "09:77 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndMinutesTooMuchValue_Fail() {
        String data = "09:22 11:67";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartMinutesTooMuchSymbols_Fail() {
        String data = "11:002 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndMinutesTooMuchSymbols_Fail() {
        String data = "11:00 11:112";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartHoursEmpty_Fail() {
        String data = ":00 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndHoursEmpty_Fail() {
        String data = "11:00 :11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartMinutesEmpty_Fail() {
        String data = "11: 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndMinutesEmpty_Fail() {
        String data = "11:11 11:";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartHoursNotDigitsValue_Fail() {
        String data = "!-:00 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndHoursNotDigitsValue_Fail() {
        String data = "11:00 -):11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartMinutesNotDigitsValue_Fail() {
        String data = "22:() 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndMinutesNotDigitsValue_Fail() {
        String data = "22:11 11:-)";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataStartMinutesNotEnoughSymbolsValue_Fail() {
        String data = "22:1 11:11";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

    @Test
    public void isDataValid_NotValidDataEndMinutesNotEnoughSymbolsValue_Fail() {
        String data = "22:11 11:1";

        Assert.assertFalse(StaffTimeWorkingPeriodStringValidator.isDataValid(data));
    }

}
