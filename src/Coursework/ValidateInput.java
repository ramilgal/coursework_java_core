package Coursework;

public class ValidateInput {
    public static String checkInputString(String s) throws IncorrectArgumentException {
        if (s.isEmpty() || s.isBlank() || s == null) {
            throw new IncorrectArgumentException("Введите корректное значение");
        } else return s;
    }
}
