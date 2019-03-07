package precognox;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class Util {

    private static final int FIRST_WEEKDAY = DayOfWeek.MONDAY.getValue();
    private static final int LAST_WEEKDAY = DayOfWeek.FRIDAY.getValue();

    public static boolean isWeekday(LocalDate day) {
        int actualDayOfWeek = day.getDayOfWeek().getValue();
        return actualDayOfWeek >= Util.FIRST_WEEKDAY && actualDayOfWeek <= Util.LAST_WEEKDAY;
    }

    public static boolean isSeparatedTime(Task task, List<Task> taskList) {
        return taskList.stream().noneMatch(t -> hasCommonTimeWith(t, task));
    }

    private static  boolean hasCommonTimeWith(Task actualTask, Task testedTask) {
        return !actualTask.getEndTime().isBefore(testedTask.getStartTime()) && !testedTask.getEndTime().isBefore(actualTask.getStartTime());
    }

    public static boolean isMultipleQuarterHour(long minutes) {
        return minutes % 15 == 0;
    }
    
    public static LocalTime roundToMultipleQuarterHour(LocalTime startTime, LocalTime endTime) {
       long minutes = Duration.between(startTime, endTime).toMinutes();
       return startTime.plusMinutes(Math.round(minutes / 15.0f) * 15);
    }
}
