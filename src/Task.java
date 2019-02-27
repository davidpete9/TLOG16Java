import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.regex.Pattern;

public class Task {

    private static final String REDMINE_TASKID_REGEX = "^\\d{4}";
    private static final String LT_TASKID_REGEX = "^LT-\\d{4}";


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

    public Task(String taskId) {
        this.taskId = taskId;
    }

    public long getMinPerTask() {
        return Duration.between(this.startTime, this.endTime).toMinutes();
    }

    public boolean isValidTaskId() {
        return this.isValidLTTaskId() || this.isValidRedmineTaskId();
    }

    private boolean isValidRedmineTaskId() {
       return  Pattern.compile(Task.REDMINE_TASKID_REGEX).matcher(this.taskId).matches();
    }

    private boolean isValidLTTaskId() {
        return  Pattern.compile(Task.LT_TASKID_REGEX).matcher(this.taskId).matches();
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

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public void setStartTime(int hour, int min) {
        this.startTime = LocalTime.of(hour, min);
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public void setEndTime(int hour, int min) {
        this.endTime = LocalTime.of(hour, min);
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
