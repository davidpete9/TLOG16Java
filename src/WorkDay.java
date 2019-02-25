import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class WorkDay {

    private static final long DEFAULT_MIN_PER_DAY = 450;
    private static final int FIRST_WEEKDAY = DayOfWeek.MONDAY.getValue();
    private static final int LAST_WEEKDAY = DayOfWeek.FRIDAY.getValue();

    private List<Task> tasks;
    private LocalDate actualDay;
    private long requiredMinPerDay;
    private long sumPerDay;

    public WorkDay() {
        this(WorkDay.DEFAULT_MIN_PER_DAY, LocalDate.now());
    }

    public WorkDay(long requiredMinPerDay, int year, int month, int day) {
        this(requiredMinPerDay, LocalDate.of(year, month, day));
    }

    public WorkDay(long requiredMinPerDay) {
        this(requiredMinPerDay, LocalDate.now());
    }

    public WorkDay(int year, int month, int day) {
        this(WorkDay.DEFAULT_MIN_PER_DAY, LocalDate.of(year, month, day));
    }

    public WorkDay(long requiredMinPerDay, LocalDate actualDay) {
        this.requiredMinPerDay = requiredMinPerDay;
        this.actualDay = actualDay;
    }

    public long getExtraMinPerDay() {
        return this.requiredMinPerDay-this.sumPerDay;
    }

    public boolean isSeparatedTime(Task task) {
        return tasks.stream().noneMatch(t -> hasCommonTimeWith(t, task));
    }

    private boolean hasCommonTimeWith(Task actualTask, Task testedTask) {
        return !actualTask.getEndTime().isBefore(testedTask.getStartTime()) && !testedTask.getEndTime().isBefore(actualTask.getStartTime());
    }

    public void addTask(Task t) {
        if (t.isMultipleQuarterHour() && this.isSeparatedTime(t)) {
            this.tasks.add(t);
        }
        else {
            //TODO - later
        }
    }

    public boolean isWeekday() {
        int actualDayOfWeek = this.actualDay.getDayOfWeek().getValue();
        return actualDayOfWeek >= WorkDay.FIRST_WEEKDAY && actualDayOfWeek <= WorkDay.LAST_WEEKDAY;
    }

    public LocalDate getActualDay() {
        return actualDay;
    }

    public long getRequiredMinPerDay() {
        return requiredMinPerDay;
    }

    public long getSumPerDay() {
        return sumPerDay;
    }

}
