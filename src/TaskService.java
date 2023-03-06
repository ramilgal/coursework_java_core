import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskService {
    public static final List<Task> actualTasks = new ArrayList<>();

    public static void addTask(Scanner scanner) {
        try {scanner.nextLine();
    System.out.println("Название задачи: ");
    String title = ValidateInput.checkInputString(scanner.nextLine());
    System.out.println("Описание задачи: ");
    String description = ValidateInput.checkInputString(scanner.nextLine());
    System.out.println("Тип задачи: WORK - рабочая, PRIVATE - личная : ");
    Type type = Type.values()[scanner.nextInt()];
    System.out.println("Повторяемость задачи: 1 - разовая, 2 - ежедневная, 3 - еженедельная, 4 - ежемесячная, 5 - ежегодная :");
    int repeat = scanner.nextInt();
    System.out.println("Дата в формате DD.MM.YYYY время HH:MM");
    scanner.nextLine();
    addEvent(scanner,title,description,type,repeat);
    System.out.println("Выход: ");
    scanner.nextLine();
} catch (IncorrectArgumentException e) {
    System.out.println(e.getMessage());
}
    }

    private static void addEvent(Scanner scanner, String title, String description, Type type, int repeat) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Task task = null;
            try {
                task = createTask(repeat, title, description, type, eventDate);
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
            catch (DateTimeParseException e) {
                System.out.println("Введите правильный формат");
                addEvent(scanner, title, description, type, repeat);
            }
        }
        private static Task createTask (int repeat, String title, String description, Type type,
                                       LocalDateTime dateTime ) throws IncorrectArgumentException {
            return switch (repeat) {
                case 1 ->{
                    OneTimeTask oneTimeTask = new OneTimeTask(title, type, description, dateTime);
                    actualTasks.add(oneTimeTask.getId(), oneTimeTask);
            }
            };
        }
    }

