import java.time.LocalDateTime;

public class DailyTask extends Task{

    public DailyTask(String title, Type type, String description, LocalDateTime dateTime) throws IncorrectArgumentException {
        super(title, type, description, dateTime);
    }

    @Override
    public boolean checkRecurrence(LocalDateTime requestDate) {
        return getDateTime().toLocalDate().equals(requestDate.toLocalDate());
    }
}
