package precognox;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class WorkDay {

    private static final long DEFAULT_MIN_PER_DAY = 450;

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

    public void addTask(Task task) {
        if (Util.isMultipleQuarterHour(this.requiredMinPerDay) && Util.isSeparatedTime(task, this.tasks)) {
            this.tasks.add(task);
        }
        else {
            //TODO - later
        }
    }

    public List<Task> getTasks() {
        return tasks;
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

    public void setActualDay(int year, int month, int day) {
        this.actualDay = LocalDate.of(year, month, day);
    }

    public void setRequiredMinPerDay(long requiredMinPerDay) {
        this.requiredMinPerDay = requiredMinPerDay;
    }
}
