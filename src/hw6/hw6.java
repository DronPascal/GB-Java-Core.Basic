package hw6;

public class hw6 {
    public static void main(String[] args) {
        /*
            1. Создать классы Собака и Кот с наследованием от класса Животное.
            2. Животные могут выполнять действия: бежать, плыть, перепрыгивать препятствие. В качестве параметра каждому методу передается величина, означающая или длину препятствия (для бега и плавания), или высоту (для прыжков).
            3. У каждого животного есть ограничения на действия (бег: кот 200 м., собака 500 м.; прыжок: кот 2 м., собака 0.5 м.; плавание: кот не умеет плавать, собака 10 м.).
            4. При попытке животного выполнить одно из этих действий, оно должно сообщить результат в консоль. (Например, dog1.run(150); -> результат: run: true)
            5. *Добавить животным разброс в ограничениях. То есть у одной собаки ограничение на бег может быть 400 м., у другой 600 м.
         */
        System.out.println("\n<Default cat>");  //runLimit = 100-300; jumpLimit = 1-3, swimLimit = 0;
        Animal defaultCat = new Cat();
        defaultCat.printAnimalRJSInfo();
        defaultCat.run(100);
        defaultCat.jump(1.5);
        defaultCat.swim(10);

        System.out.println("\n<Default dog>");  //runLimit = 400-600; jumpLimit = 0.25-0,75; swimLimit = 5-10;
        Animal defaultDog = new Dog();
        defaultDog.printAnimalRJSInfo();
        defaultDog.run(600);
        defaultDog.jump(0.5);
        defaultDog.swim(7);

        System.out.println("\n<Custom cat>");
        Animal strongestCat = new Cat(999999, 1000, 999999);
        strongestCat.printAnimalRJSInfo();
        strongestCat.jump(2000);
        strongestCat.swim(10000);

        System.out.println("\n<Custom dog1>");
        Animal customDog1 = new Dog(400, 2, 200);
        customDog1.printAnimalRJSInfo();
        customDog1.run(600);
        customDog1.jump(2.5);
        customDog1.swim(300);

        System.out.println("\n<Custom dog2>");
        Animal customDog2 = new Dog(600, 3, 400);
        customDog2.printAnimalRJSInfo();
        customDog2.run(600);
        customDog2.jump(2.5);
        customDog2.swim(300);
    }
}
