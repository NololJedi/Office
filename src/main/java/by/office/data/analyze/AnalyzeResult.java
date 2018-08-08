package by.office.data.analyze;

import by.office.data.StaffTimeWorkingPeriod;

import java.util.List;
import java.util.Objects;

public class AnalyzeResult {

    private int count;
    private List<StaffTimeWorkingPeriod> periods;

    public AnalyzeResult(int count, List<StaffTimeWorkingPeriod> periods) {
        this.count = count;
        this.periods = periods;
    }

    public int getCount() {
        return count;
    }

    public List<StaffTimeWorkingPeriod> getPeriods() {
        return periods;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object){
            return true;
        }
        if (object == null || getClass() != object.getClass()){
            return false;
        }
        AnalyzeResult that = (AnalyzeResult) object;
        return count == that.count &&
                Objects.equals(periods, that.periods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, periods);
    }

    @Override
    public String toString() {
        return "AnalyzeResult{" +
                "count=" + count +
                ", periods=" + periods +
                '}';
    }
}
