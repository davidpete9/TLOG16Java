import java.time.YearMonth;
import java.util.List;

public class WorkMonth {

    private List<WorkDay> days;
    private YearMonth date;
    private long sumPerMonth;
    private long requiredMinPerMonth;

    public WorkMonth(int year, int month) {
        this.date = YearMonth.of(year, month);
    }

    public long getExtraMinPerMonth() {
        return this.sumPerMonth-this.requiredMinPerMonth;
    }

    public boolean isNewDate(WorkDay day) {
        return this.days.stream().noneMatch(d -> d.getActualDay().equals(day.getActualDay()));
    }

    public boolean isSameMonth(WorkDay day) {
        return (day.getActualDay().getMonthValue() == this.date.getMonthValue())
                && (day.getActualDay().getYear() == this.date.getYear());
    }

    public void addWorkDay(WorkDay wd, boolean isWeekendEnabled) {
        if ((this.isSameMonth(wd) && this.isNewDate(wd)) && (isWeekendEnabled || Util.isWeekday(wd.getActualDay()))) {
               this.days.add(wd);
        }
    }

    public void addWorkDay(WorkDay wd) {
        this.addWorkDay(wd, false);
    }

    public List<WorkDay> getDays() {
        return days;
    }

    public YearMonth getDate() {
        return date;
    }

    public long getSumPerMonth() {
        return sumPerMonth;
    }

    public long getRequiredMinPerMonth() {
        return requiredMinPerMonth;
    }
}
