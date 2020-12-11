package hw6;

public class Dog extends Animal {
    public Dog() {
        super((int)Math.random()*201+400, Math.random()*0.5+0.25, (int)Math.random()*11+5);
    }

    public Dog(double runLimit, double jumpLimit, double swimLimit) {
        super(runLimit, jumpLimit, swimLimit);
        //если добавится еще какое-то ограничение, то будем его описывать тут + добавим поле для этого ограничения в этом классе
    }
}
