package by.office.data;

import java.util.Objects;

public class StaffTimeWorkingPeriod implements Comparable<StaffTimeWorkingPeriod> {

    private int start;
    private  int end;

    public StaffTimeWorkingPeriod(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        StaffTimeWorkingPeriod that = (StaffTimeWorkingPeriod) object;
        return start == that.start &&
                end == that.end;
    }

    @Override
    public int hashCode() {
        return Objects.hash(start, end);
    }

    @Override
    public String toString() {
        return "StaffTimeWorkingPeriod{" +
                "start=" + start +
                ", end=" + end +
                '}';
    }

    @Override
    public int compareTo(StaffTimeWorkingPeriod object) {
        return this.start - object.start;
    }
}
