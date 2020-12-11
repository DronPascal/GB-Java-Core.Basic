package hw6;

public class hw6 {
    public static void main(String[] args) {

        System.out.println("\n<Default cat>");  //runLimit = 100-300; jumpLimit = 1-3, swimLimit = 0;
        Animal defaultCat = new Cat();
        defaultCat.printAnimalInfo();
        defaultCat.run(100);
        defaultCat.jump(1.5);
        defaultCat.swim(10);

        System.out.println("\n<Default dog>");  //runLimit = 400-600; jumpLimit = 0.25-0,75; swimLimit = 5-10;
        Animal defaultDog = new Dog();
        defaultDog.printAnimalInfo();
        defaultDog.run(600);
        defaultDog.jump(0.5);
        defaultDog.swim(7);

        System.out.println("\n<Custom cat>");
        Animal strongestCat = new Cat(999999, 1000, 999999);
        strongestCat.printAnimalInfo();
        strongestCat.jump(2000);
        strongestCat.swim(10000);

        System.out.println("\n<Custom dog1>");
        Animal customDog1 = new Dog(400, 2, 200);
        customDog1.printAnimalInfo();
        customDog1.run(600);
        customDog1.jump(2.5);
        customDog1.swim(300);

        System.out.println("\n<Custom dog2>");
        Animal customDog2 = new Dog(600, 3, 400);
        customDog2.printAnimalInfo();
        customDog2.run(600);
        customDog2.jump(2.5);
        customDog2.swim(300);
    }
}
