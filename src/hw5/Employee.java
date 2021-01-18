package hw5;

public class Employee {
    private String
            fullName,
            position,
            email,
            phoneNumber;
    private double
            salary;
    private int
            age;

    public Employee() {
        fullName = "unknown";
        position = "unknown";
        email = "unknown";
        phoneNumber = "unknown";
        salary = -1;
        age = -1;
    }

    public Employee(String fullName, String position, String email, String phoneNumber, double salary, int age) {
        this.fullName = fullName;
        this.position = position;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.salary = salary;
        this.age = age;
    }

    public void printInfo() {
        System.out.println("Full name: " + fullName + "\nPosition: " + position + "\nEmail: " + email + "\nPhone number: " + phoneNumber + "\nSalary: " + salary + "\nAge: " + age + "\n");
    }

    public void printInfo(boolean string) {
        if (string)
            System.out.println("Full name: " + fullName + " | Position: " + position + " | Email: " + email + " | Phone number: " + phoneNumber + " | Salary: " + salary + " | Age: " + age);
    }

    public int getAge() {  //геттеры можно прописать для всех полей. привыкаем к инкапсуляции
        return age;
    }
}
