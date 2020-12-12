package hw6;

public class Cat extends Animal {
    public Cat() {  // Обычные коты не умеют плавать
        super((int) Math.random() * 201 + 100, (int) Math.random() * 2 + 1, 0);
    }

    public Cat(double runLimit, double jumpLimit, double swimLimit) {
        super(runLimit, jumpLimit, swimLimit);
        //если добавится еще какое-то ограничение, то будем его описывать тут + добавим поле для этого ограничения в этом классе
    }

    @Override
    public void swim(double distance) {
        if (getSwimLimit() == 0) System.out.println("This cat can't swim!");  // Обычные коты не умеют плавать
        else super.swim(distance);
    }
}
