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

    public Employee(String _fullName, String _position, String _email, String _phoneNumber, double _salary, int _age) {
        fullName = _fullName;
        position = _position;
        email = _email;
        phoneNumber = _phoneNumber;
        salary = _salary;
        age = _age;
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
