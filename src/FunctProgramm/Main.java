package FunctProgramm;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        System.out.println("Задача на функциональное программирование\n" +
                "-------------------------\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст: ");
        String text = scanner.nextLine();
        int count = text.split(" ").length;
        System.out.println("Количество слов: " + count);
        System.out.println("TOP10: ");
        List<String> list = List.of(text);
        list.stream()
                .flatMap(line -> Stream.of(text.split(" ")))
                .collect(Collectors.toMap(key -> key, val -> 1, Integer::sum))
                .entrySet().stream()
                .sorted((e1, e2) -> {
                    int value = e1.getValue().compareTo(e2.getValue()) * -1;
                    if (value == 0) {
                        value = e1.getKey().compareTo(e2.getKey());
                    }return value;})
                .forEach(e -> System.out.println(e.getValue() + " - " + e.getKey()));
    }
}
