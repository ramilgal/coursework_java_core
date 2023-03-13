package Coursework;

import java.time.LocalDateTime;

public class WeeklyTask extends Task{

    public WeeklyTask(String title, Type type, String description, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, type, description, dateTime);
    }
    @Override
    public boolean checkRecurrence(LocalDateTime requestDate) {
        return getDateTime().getDayOfWeek().equals(requestDate.getDayOfWeek());
    }
}
