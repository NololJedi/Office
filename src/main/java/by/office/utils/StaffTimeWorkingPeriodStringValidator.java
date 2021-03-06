package by.office.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StaffTimeWorkingPeriodStringValidator {

    private static final String TIME_VALIDATION_PATTERN = "(([0-2][0-3])|([0-1]?[0-9])):[0-5][0-9]";

    private StaffTimeWorkingPeriodStringValidator(){}

    public static boolean isDataValid(String data){
        if (data == null || data.isEmpty()){
            return false;
        }

        Pattern pattern = Pattern.compile(TIME_VALIDATION_PATTERN + " " + TIME_VALIDATION_PATTERN);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }
}
