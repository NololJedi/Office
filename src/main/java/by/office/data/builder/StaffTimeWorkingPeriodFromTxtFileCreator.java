package by.office.data.builder;

import by.office.data.StaffTimeWorkingPeriod;
import by.office.utils.StaffTimeWorkingPeriodStringValidator;

import java.util.Optional;

public class StaffTimeWorkingPeriodFromTxtFileCreator implements StaffTimeWorkingPeriodCreator<String> {

    @Override
    public Optional<StaffTimeWorkingPeriod> create(String data){
        if (data == null || data.isEmpty()){
            return Optional.empty();
        }
        data = clearData(data);

        if (!StaffTimeWorkingPeriodStringValidator.isDataValid(data)){
            return Optional.empty();
        }

        String[] parsedData = data.split(" ");
        String startValue = parsedData[0].replace(":", "");
        String endValue = parsedData[1].replace(":", "");

        int start = Integer.parseInt(startValue);
        int end = Integer.parseInt(endValue);

        StaffTimeWorkingPeriod period = new StaffTimeWorkingPeriod(start, end);

        return Optional.of(period);
    }

    private String clearData(String data){
        data = data.trim();
        data = data.replaceAll("\r", "");
        data = data.replaceAll("\n", "");

        return data;
    }

}
