package Coursework;

import java.time.LocalDateTime;

public class YearlyTask extends Task{

    public YearlyTask(String title, Type type, String description, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, type, description, dateTime);
    }

    @Override
    public boolean checkRecurrence(LocalDateTime requestDate) {
        return getDateTime().getDayOfYear() == (requestDate.getDayOfYear());
    }
}
