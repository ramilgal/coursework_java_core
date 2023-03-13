package Coursework;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class TaskService {
    public static final Map<Integer, Task> currentTasks = new HashMap<>();

    public static void addTask(Scanner scanner) {
        try {
            scanner.nextLine();
            System.out.println("Название задачи: ");
            String title = ValidateInput.checkInputString(scanner.nextLine());
            System.out.println("Описание задачи: ");
            String description = ValidateInput.checkInputString(scanner.nextLine());
            System.out.println("Тип задачи: 0 - рабочая, 1 - личная : ");
            Type type = Type.values()[scanner.nextInt()];
            System.out.println("Повторяемость задачи: 1 - разовая, 2 - ежедневная, 3 - еженедельная, 4 - ежемесячная, 5 - ежегодная :");
            int repeat = scanner.nextInt();
            System.out.println("Дата и время в формате dd.MM.yyyy HH:mm");
            scanner.nextLine();
            addEvent(scanner, title, description, type, repeat);
            System.out.println("Выход - enter: ");
            scanner.nextLine();
        } catch (IncorrectArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void addEvent(Scanner scanner, String title, String description, Type type, int repeat) {
        try {
            LocalDateTime eventDate = LocalDateTime.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
            Task task;
            try {
                task = createTask(repeat, title, description, type, eventDate);
                System.out.println(task + " - задача создана");
            } catch (IncorrectArgumentException e) {
                System.out.println(e.getMessage());
            }
        } catch (DateTimeParseException e) {
            System.out.println("Введите правильный формат даты и нажмите enter");
            addEvent(scanner, title, description, type, repeat);
        }
    }

    private static Task createTask(int repeat, String title, String description, Type type,
                                   LocalDateTime dateTime) throws IncorrectArgumentException {
        return switch (repeat) {
            case 1 -> {
                OneTimeTask oneTimeTask = new OneTimeTask(title, type, description, dateTime);
                currentTasks.put(oneTimeTask.getId(), oneTimeTask);
                yield oneTimeTask;
            }
            case 2 -> {
                DailyTask dailyTask = new DailyTask(title, type, description, dateTime);
                currentTasks.put(dailyTask.getId(), dailyTask);
                yield dailyTask;
            }
            case 3 -> {
                WeeklyTask weeklyTask = new WeeklyTask(title, type, description, dateTime);
                currentTasks.put(weeklyTask.getId(), weeklyTask);
                yield weeklyTask;
            }
            case 4 -> {
                MonthlyTask monthlyTask = new MonthlyTask(title, type, description, dateTime);
                currentTasks.put(monthlyTask.getId(), monthlyTask);
                yield monthlyTask;
            }
            case 5 -> {
                YearlyTask yearlyTask = new YearlyTask(title, type, description, dateTime);
                currentTasks.put(yearlyTask.getId(), yearlyTask);
                yield yearlyTask;
            }
            default -> null;
        };
    }

    public static void editTask(Scanner scanner) {

        try {
            System.out.println("Редактирование задачи по ID из списка (введите ID):");
            printCurrentTasks();
            int id = scanner.nextInt();
            if (!currentTasks.containsKey(id)) {
                throw new TaskNotFoundException("Указанная задача не найдена");
            }
            System.out.println("Редактировать: 0 - Название, 1 - Описание, 2 - Тип задачи, 3 - Дата");
            int choice = scanner.nextInt();
            switch (choice) {
                case 0->{
                    scanner.nextLine();
                    System.out.println("Введите название: (или Enter -для выхода в главное меню)");
                    String title = scanner.nextLine();
                    Task task = currentTasks.get(id);
                    task.setTitle(title);}
                case 1->{
                    scanner.nextLine();
                    System.out.println("Введите описание: (или Enter -для выхода в главное меню)");
                    String description = scanner.nextLine();
                    Task task1 = currentTasks.get(id);
                    task1.setDescription(description);}
                case 2->{
                    scanner.nextLine();
                    System.out.println("Введите тип задачи WORK или PRIVATE: (или Enter -для выхода в главное меню)");
                    Type type = Type.valueOf(scanner.nextLine());
                    Task task2 = currentTasks.get(id);
                    task2.setType(type);}
                case 3->{
                    scanner.nextLine();
                    System.out.println("Введите дату в формате dd.MM.yyyy время HH:mm (или Enter -для выхода в главное меню)");
                    LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine(),
                            DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm"));
                    Task task3 = currentTasks.get(id);
                    task3.setDateTime(dateTime);}
            }


        } catch (TaskNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void deleteTask(Scanner scanner)  {
        System.out.println("Список всех задач: ");
        printCurrentTasks();
        try {
            System.out.println("Введите ID задачи для удаления: ");
            int id = scanner.nextInt();
            if (currentTasks.containsKey(id)) {
                currentTasks.remove(id);
                System.out.println("Задача ID: " + id + " удалена");
            } else throw new TaskNotFoundException();

        } catch (TaskNotFoundException e) {
            e.printStackTrace();
            System.out.println("Указанная задача не найдена");
        }
    }
    public static void getTaskOnDay(Scanner scanner) {
        System.out.println("Введите дату в формате dd.MM.yyyy :");
        try {
            String date = scanner.next();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
            LocalDate requestDate = LocalDate.parse(date, dateTimeFormatter);
            List<Task> tasksOnDay = findTasksOnDay(requestDate);
            System.out.println("Найденные события: ");
            for (Task task : tasksOnDay) {
                System.out.println(task);
            }
        } catch (DateTimeParseException e) {
            System.out.println("Введите правильный формат dd.MM.yyyy");
            getTaskOnDay(scanner);
        } scanner.nextLine();
        System.out.println("enter - выход");
    }

    private static List<Task> findTasksOnDay(LocalDate date) {
        List<Task> tasks = new ArrayList<>();
        for (Task task : currentTasks.values()) {
            if (task.checkRecurrence(date.atStartOfDay())) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public static void printCurrentTasks() {
        for (Task task: currentTasks.values())
        {System.out.println(task);}
    }
}

