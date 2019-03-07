package precognox;

import java.util.List;

public class TimeLogger {
    List<WorkMonth> months;

    public List<WorkMonth> getMonths() {
        return months;
    }

    public boolean isNewMonth(WorkMonth month) {
        return this.months.stream().noneMatch(m -> m.getDate().equals(month.getDate()));
    }

    public void addMonth(WorkMonth m) {
        if (this.isNewMonth(m)) {
            this.months.add(m);
        }
    }

}
