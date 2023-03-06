import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Hello world!");
        validateInputOfUser(2023, 2123);


    }

    public static int validateInputOfUser(int min, int max) {
        while (true) {
            while (!scanner.hasNextInt()) {
                System.out.println("Input number!");
                scanner.nextLine();}

                int input = scanner.nextInt();
                if (input >= min && input <= max)
                    return input;
                System.out.println("Please input correct!");
            }
        }
        }
