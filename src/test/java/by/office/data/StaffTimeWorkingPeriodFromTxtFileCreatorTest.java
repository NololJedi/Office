package by.office.data;

import by.office.data.builder.StaffTimeWorkingPeriodFromTxtFileCreator;
import org.junit.Assert;
import org.junit.Test;

public class StaffTimeWorkingPeriodFromTxtFileCreatorTest {

    private final StaffTimeWorkingPeriodFromTxtFileCreator creator = new StaffTimeWorkingPeriodFromTxtFileCreator();

    @Test
    public void create_ValidData_Success(){
        //given
        String data = "22:00 22:00";

        //when
        StaffTimeWorkingPeriod expected = new StaffTimeWorkingPeriod(2200, 2200);
        StaffTimeWorkingPeriod actual = creator.create(data).get();

    // then
    Assert.assertEquals(expected, actual);
    }

    @Test
    public void create_EmptyData_Fail(){
        String data = "";

        Assert.assertFalse(creator.create(data).isPresent());
    }

    @Test
    public void create_NullData_Fail(){
        String data = null;

        Assert.assertFalse(creator.create(data).isPresent());
    }

    @Test
    public void create_NotValidData_Fail(){
        String data = "0:asd";

        Assert.assertFalse(creator.create(data).isPresent());
    }

}
