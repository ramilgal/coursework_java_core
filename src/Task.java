import java.time.LocalDateTime;
import java.util.Objects;


public abstract class Task {
    private String title;
    private Type type;
    private String description;
    private final Integer id;
    private static Integer counter = 1;
    private LocalDateTime dateTime;
    private boolean deleted;

    public boolean checkRecurrence(LocalDateTime requestDate) {

        return true;
    }

    public Task(String title, Type type, String description, LocalDateTime dateTime)
            throws IncorrectArgumentException {
        this.title = ValidateInput.checkInputString(title);
        this.type = type;
        this.description = ValidateInput.checkInputString(description);
        this.id = counter++;
        this.dateTime = dateTime;
        this.deleted = false;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return deleted == task.deleted && Objects.equals(title, task.title) && type == task.type && Objects.equals(description, task.description) && Objects.equals(id, task.id) && Objects.equals(dateTime, task.dateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, type, description, id, dateTime, deleted);
    }
}
