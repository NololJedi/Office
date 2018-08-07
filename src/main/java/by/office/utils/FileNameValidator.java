package by.office.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileNameValidator {

    private static final String FILE_NAME_PATTERN = "^.+\\.txt$";

    private FileNameValidator() {}

    public static boolean isFileNameValid(String fileName){
        if (fileName == null || fileName.isEmpty()){
            return false;
        }

        Pattern pattern = Pattern.compile(FILE_NAME_PATTERN);
        Matcher matcher = pattern.matcher(fileName);

        return matcher.matches();
    }
}
