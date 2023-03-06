import java.time.LocalDateTime;

public class MonthlyTask extends Task{
    public MonthlyTask(String title, Type type, String description, Integer id, LocalDateTime dateTime, boolean deleted)
            throws IncorrectArgumentException {
        super(title, type, description, id, dateTime, deleted);
    }
    @Override
    public boolean checkRecurrence(LocalDateTime requestDate) {
        return getDateTime().getDayOfMonth() == (requestDate.getDayOfMonth());
    }
}
