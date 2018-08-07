package by.office.utils;

import org.junit.Assert;
import org.junit.Test;

public class FileNameValidatorTest {

    @Test
    public void isFileNameValid_FileNameValid_Success(){
        String validName = "input.txt";

        Assert.assertTrue(FileNameValidator.isFileNameValid(validName));
    }

    @Test
    public void isFileNameValid_FileNameWithDigit_Success(){
        String validName = "input2.txt";

        Assert.assertTrue(FileNameValidator.isFileNameValid(validName));
    }

    @Test
    public void isFileNameValid_FileNameXML_Fail(){
        String validName = "input.xml";

        Assert.assertFalse(FileNameValidator.isFileNameValid(validName));
    }

    @Test
    public void isFileNameValid_FileNameNull_Fail(){
        String nullName = null;

        Assert.assertFalse(FileNameValidator.isFileNameValid(nullName));
    }

    @Test
    public void isFileNameValid_FileNameEmpty_Fail(){
        String emptyFileName = "";

        Assert.assertFalse(FileNameValidator.isFileNameValid(emptyFileName));
    }

    @Test
    public void isFileNameValid_FileNameTrash_Fail(){
        String validName = "asd82217adas7d73";

        Assert.assertFalse(FileNameValidator.isFileNameValid(validName));
    }
}
