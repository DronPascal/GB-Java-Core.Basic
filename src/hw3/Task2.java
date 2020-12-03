package hw3;

import java.util.Scanner;

public class Task2 {
    /*
     * Создать массив из слов String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry",
     * "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
     * При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя,
     * сравнивает его с загаданным словом и сообщает правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы которые стоят на своих местах.
     * apple – загаданное
     * apricot - ответ игрока
     * ap############# (15 символов, чтобы пользователь не мог узнать длину слова)
     * Для сравнения двух слов посимвольно, можно пользоваться:
     * String str = "apple";
     * str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
     * Играем до тех пор, пока игрок не отгадает слово
     * Используем только маленькие буквы
     */
    public static void main(String[] args) {
        // Инициализация
        String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic", "grape", "melon",
                "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};

        String hiddenWord = words[(int) (Math.random() * words.length)];
        StringBuilder codedWord = new StringBuilder("###############");
        int shift = 0;  // or ((codedWord.length() - hiddenWord.length()) / 2) for #####apple#####
        Scanner scanner = new Scanner(System.in);

        // Начало игры
        System.out.print("Сыграем в игру! Здесь закодировано слово: " + codedWord + "\nВозможные слова: ");
        for (String word : words) System.out.print(word + " ");
        System.out.println("\nУгадай какое из этих слов я загадал. Совпавшие буквы откроются на поле.");

        while (true) {
            System.out.print("\nВведите слово: ");
            String input = scanner.nextLine();
            if (hiddenWord.equals(input)) {
                System.out.println("\nВы угадали! Это было слово " + hiddenWord + "!");
                break;
            } else {  // Вскрываем совпавшие буквы
                int len = Math.min(input.length(), hiddenWord.length());
                for (int i = 0; i < len; i++) {
                    if (input.charAt(i) == hiddenWord.charAt(i)) {
                        codedWord.setCharAt(i + shift, input.charAt(i));
                    }
                }
            }
            System.out.println("Закодированное слово: " + codedWord);
        }

        System.out.println("Игра завершена!");
        scanner.close();
    }
}
