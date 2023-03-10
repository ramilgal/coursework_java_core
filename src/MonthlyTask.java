import java.time.LocalDateTime;

public class MonthlyTask extends Task{

    public MonthlyTask(String title, Type type, String description, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, type, description, dateTime);
    }

    @Override
    public boolean checkRecurrence(LocalDateTime requestDate) {
        return getDateTime().getDayOfMonth() == (requestDate.getDayOfMonth());
    }
}
