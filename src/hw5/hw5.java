package hw5;
/*
 * Создать класс "Сотрудник" с полями: ФИО, должность, email, телефон, зарплата, возраст;
 * Конструктор класса должен заполнять эти поля при создании объекта;
 * Внутри класса «Сотрудник» написать метод, который выводит информацию об объекте в консоль;
 * Создать массив из 5 сотрудников
 Пример:
 Person[] persArray = new Person[5]; // Вначале объявляем массив объектов
 persArray[0] = new Person("Ivanov Ivan", "Engineer", "ivivan@mailbox.com", "892312312", 30000, 30); // потом для каждой ячейки массива задаем объект
 persArray[1] = new Person(...);
 ...
 persArray[4] = new Person(...);

 * С помощью цикла вывести информацию только о сотрудниках старше 40 лет;
 */
public class hw5 {
    public static void main(String[] args) {
        Employee[] employees = new Employee[5]; //вначале объявляем массив объектов
        employees[0] = new Employee("Kyle Luis Reed", "Engineer", "kyle@mailbox.com", "84952211289", 102000, 30);
        employees[1] = new Employee("Brandon Joseph Butler", "Barman", "brandon@mailbox.com", "89999071667", 40916, 41);
        employees[2] = new Employee("Thomas Kyle King", "Coach", "thomas@mailbox.com", "84950777283", 62383, 26);
        employees[3] = new Employee("Kimberly Lily Morris", "Dairymaid", "kimberly@mailbox.com", "89264953036", 119629, 68);
        employees[4] = new Employee("Aaliyah Natalie Carter", "Driver", " aaliyah@mailbox.com", "89662211289", 81057, 47);
        for (Employee employee : employees)
            if (employee.getAge() >= 40) employee.printInfo(true);
    }
}
