package hw7;

public class Cat extends Animal {
    // По заданию сказано, что это свойства КОТОВ, поэтому не станем менять класс Animal
    private double eatLimit;
    private boolean satiety;

    public Cat() {
        super((int) Math.random() * 201 + 100, (int) Math.random() * 2 + 1, 0);
        eatLimit = 10;
        satiety = false;
    }

    public Cat(double runLimit, double jumpLimit, double swimLimit, double eatLimit) {
        super(runLimit, jumpLimit, swimLimit);
        if (eatLimit > 0) this.eatLimit = eatLimit;
        else this.eatLimit = 10;
        satiety = false;
    }

    @Override
    public void swim(double distance) {
        if (getSwimLimit() == 0) System.out.println("Этот кот не умеет плавать!");  // Обычные коты не умеют плавать
        else super.swim(distance);
    }

    // Создал сеттер чтобы можно было отдельно настроить свойство проглотства
    public void setEatLimit(double eatLimit) {
        this.eatLimit = eatLimit;
    }

    public void eatFrom(FoodContainer foodContainer) {
        if (foodContainer != null) {
            double foodVolume = foodContainer.getFoodVolume();

            if (foodVolume == 0)
                System.out.println("Коту нечего жрать! Он громко орет!");
            else if (foodVolume < eatLimit)
                System.out.println("Кот отказался кушать так мало. Он орет!");
            else if (foodVolume == eatLimit)
                System.out.println("Кот обожрался.");
            else
                System.out.println("Кот обожрался и еще осталось.");

            if (foodVolume >= eatLimit) {
                foodContainer.decreaseFood(eatLimit);
                satiety = true;
            }
        } else System.out.println("Ошибка.");
    }

    public double getEatLimit() {
        return eatLimit;
    }

    public boolean getSatiety() {
        return satiety;
    }

    public void setSatiety(boolean satiety) {
        this.satiety = satiety;
    }
}
