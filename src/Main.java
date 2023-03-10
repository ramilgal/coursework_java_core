import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            label:
            while (true) {
                System.out.println("Меню. Выберите пункт:");
                System.out.println();
                System.out.print("1 - Добавить задачу\n" +
                        "2 - Редактировать задачу\n" +
                        "3 - Удалить задачу по ID\n" +
                        "4 - Найти задачи на день\n" +
                        "0 - Выход\n");
                if (scanner.hasNextInt()) {
                    int input = scanner.nextInt();
                    switch (input) {
                        case 1:
                            TaskService.addTask(scanner);
                            break;
                        case 2:
                            TaskService.editTask(scanner);
                            break;
                        case 3:
                            TaskService.deleteTask(scanner);
                            break;
                        case 4:
                            TaskService.getTaskOnDay(scanner);
                            break;
                        case 0:
                            break label;
                    }
                } else {
                    scanner.next();
                System.out.println("Выберите верный пункт!");
            }
        }
        }
    }
}
