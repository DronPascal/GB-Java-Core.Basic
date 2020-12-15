package hw7;

// Миска
public class Dish extends FoodContainer {
    public Dish(double capacity) {
        super(capacity);
    }

    @Override
    public double getFoodVolume() {
        System.out.println("Миска содержит " + foodVolume + " единиц еды");
        return foodVolume;
    }

    @Override
    public void setFoodVolume(double foodVolume) {
        if (capacity > foodVolume) {
            this.foodVolume = foodVolume;
            System.out.println("В миске теперь " + foodVolume + " единиц еды");
        } else
            System.out.println("В миску столько не поместится");
    }
}
