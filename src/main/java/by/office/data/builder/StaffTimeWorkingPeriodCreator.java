package by.office.data.builder;

import by.office.data.StaffTimeWorkingPeriod;

import java.util.Optional;

public interface StaffTimeWorkingPeriodCreator<T> {

    Optional<StaffTimeWorkingPeriod> create(T value);

}
