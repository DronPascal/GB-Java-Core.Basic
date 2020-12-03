package hw3;

import java.util.Scanner;

public class Task1 {
    /*
     * 1. Написать программу, которая загадывает случайное число от 0 до 9,
     * и пользователю дается 3 попытки угадать это число. При каждой попытке
     * компьютер должен сообщить больше ли указанное пользователем число чем
     * загаданное, или меньше. После победы или проигрыша выводится запрос –
     * «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).
     */
    private static final int MAX_NUMBER = 9;
    private static final int MAX_TRY_COUNT = 3;

    public static void main(String[] args) {
        int randomNumber = (int) (Math.random() * (MAX_NUMBER + 1));
        int attemptNumber = 1;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Попробуйте угадать число от " + 0 + " до " + MAX_NUMBER +
                "\nУ Вас " + fixNumerical(MAX_TRY_COUNT, "попытка", "попытки", "попыток") + "!");

        // Основной цикл игры
        while (attemptNumber <= MAX_TRY_COUNT) {
            System.out.print("\nПопытка №" + attemptNumber + "\nВведите число: ");
            // Ввода числа
            int input = getUserInput(scanner);
            // Реакция на приемлемое введенное число
            if (input != randomNumber) {
                System.out.println("Введенное вами число " + input + " " + ((input > randomNumber) ? "больше" : "меньше") + " загаданного.");
            } else {
                System.out.println("\nВы угадали!");
                break;
            }
            // Реакция на кол-во оставшихся попыток
            if (attemptNumber == MAX_TRY_COUNT) {
                System.out.println("\nВы не успели угадать число.");
            }
            attemptNumber++;
        }

        System.out.println("Игра завершена!");
        scanner.close();
    }
    
    public static int getUserInput(Scanner scanner){
        int input = 0;
        while (scanner.hasNext()) {  // Считываем строку
            if (scanner.hasNextInt()) {  // Если в строке число int
                input = scanner.nextInt();  // Сохраняем введенное число
                if (input >= 0 && input <= MAX_NUMBER) {
                    break;
                } else {
                    System.out.print("Введеное число не входит в интервал от " + 0 + " до " + MAX_NUMBER + " Повторите ввод: ");
                }
            } else {
                System.out.print(scanner.next() + " не является целым числом! Повторите ввод: ");  //выводим последюю введенную строку
            }
        }
        return input;
    }

    public static String fixNumerical(int num, String... arr) {
        int preLastDigit = num % 100 / 10;
        if (preLastDigit == 1) {
            return String.format("%d %s", num, arr[2]);
        }

        int lastDigit = num % 10;
        switch (lastDigit) {
            case 1:
                return String.format("%d %s", num, arr[0]);
            case 2:
            case 3:
            case 4:
                return String.format("%d %s", num, arr[1]);
            default:
                return String.format("%d %s", num, arr[2]);
        }
    }
}
