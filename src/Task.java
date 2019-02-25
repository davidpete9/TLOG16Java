import java.time.Duration;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Task {

    private static final String TASKID_REGEX = "^(\\d{4})|^(?i:LT-\\d{4})";

    private String taskId;
    private LocalTime startTime;
    private LocalTime endTime;
    private String comment;

    public Task(String taskId, String comment, int startHour, int startMin, int endHour, int endMin) {
        this.taskId = taskId;
        this.comment = comment;
        this.startTime = LocalTime.of(startHour, startMin);
        this.endTime = LocalTime.of(endHour, endMin);
    }

    public Task(String taskId, String comment, String startTimeAsString, String endTimeAsString) {
        this.taskId = taskId;
        this.comment = comment;
        this.startTime = LocalTime.parse(startTimeAsString);
        this.endTime = LocalTime.parse(endTimeAsString);
    }

    public long getMinPerTask() {
        return Duration.between(this.startTime, this.endTime).toMinutes();
    }

    public boolean isValidTaskId() {
        return Pattern.compile(Task.TASKID_REGEX).matcher(this.taskId).matches();
    }

    public boolean isMultipleQuarterHour() {
        return this.getMinPerTask() % 15 == 0;
    }

    public String getTaskId() {
        return taskId;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public String getComment() {
        return comment;
    }
}
