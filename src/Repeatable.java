import java.time.LocalDateTime;

public interface Repeatable {

    boolean checkRecurrence(LocalDateTime localDateTime);

    LocalDateTime getDateTime();
}
