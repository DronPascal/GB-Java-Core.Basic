package hw1;

public class hw1 {
    //1. Создать пустой проект в IntelliJ IDEA и прописать метод main();
    public static void main(String[] args) {
        //2. Создать переменные всех пройденных типов данных, и инициализировать их значения;
        byte b = 103;
        short s = 54;
        int i = 87956;
        long l = 870L;
        float f = 760.69f;
        double d = 46.456454;
        boolean bool = true;
        char c = 'A';
        String name = "Проверяющий";
        Object object = new Object();

        //3. Написать метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные параметры этого метода;
        System.out.println("task3: " + method3(32, 12.3, 2, 4.5));
        System.out.println("task3: " + method3(32, 12.3, 2, 0));
        System.out.println("task3: " + method3(0, 12.3, 2, 0) + "\n");

        //4. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
        System.out.println("task4: " + isSumIn10to20(-20, 30));
        System.out.println("task4: " + isSumIn10to20(0, 30) + "\n");

        //5. Написать метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
        System.out.print("task5: ");
        printNumSign(12.3);
        System.out.print("task5: ");
        printNumSign(-12.3);

        //6. Написать метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;
        System.out.println("\ntask6: " + isNegative(-12.3));
        System.out.println("task6: " + isNegative(12.3) + "\n");

        //7. Написать метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
        sayHello(name);
        System.out.println();

        //8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
        System.out.print("task5: "); isLeap(2020);
        System.out.print("task5: "); isLeap(2021);
    }

    //3. метод вычисляющий выражение a * (b + (c / d)) и возвращающий результат,где a, b, c, d – входные параметры этого метода;
    static double method3(double a, double b, double c, double d) {
        final double MAX_VALUE = 1.7976931348623157E308;
        if (d != 0) return a * (b + (c / d));
        else if (a != 0) return MAX_VALUE;
        else return 0;
    }

    //4. метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно), если да – вернуть true, в противном случае – false;
    private static boolean isSumIn10to20(double num1, double num2) {
        double sum = num1 + num2;
        return (sum >= 10 && sum <= 20);
    }

    //5. метод, которому в качестве параметра передается целое число, метод должен напечатать в консоль положительное ли число передали, или отрицательное; Замечание: ноль считаем положительным числом.
    private static void printNumSign(double num) {
        System.out.println(num >= 0 ? "Положительное" : "Отрицательное");
    }

    //6. метод, которому в качестве параметра передается целое число, метод должен вернуть true, если число отрицательное;
    private static boolean isNegative(double num) {
        return num < 0;
    }

    //7. метод, которому в качестве параметра передается строка, обозначающая имя, метод должен вывести в консоль сообщение «Привет, указанное_имя!»;
    private static void sayHello(String name) {
        System.out.println("Привет, " + name + "!");
    }

    //8. *метод, который определяет является ли год високосным, и выводит сообщение в консоль. Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.
    private static boolean isLeap(int year) {
        boolean isLeap = (((year % 4 == 0) && !(year % 100 == 0)) || (year % 400 == 0));
        System.out.println(year +" год "+ (isLeap ? "" : "не") + "високосный");
        return isLeap;
    }
}
