package hw7;

// Кто знает сколько у нас будет разных контейнеров в будущем..
public abstract class FoodContainer {
    protected double
            capacity,
            foodVolume;

    public FoodContainer() {
        capacity = 0;
        foodVolume = 0;
    }

    public FoodContainer(double capacity) {
        this.capacity = capacity;
    }

    public void setCapacity(double capacity) {
        this.capacity = capacity;
    }

    public double getFoodVolume() {
        return foodVolume;
    }

    public void setFoodVolume(double foodVolume) {
        if (foodVolume <= capacity) {
            this.foodVolume = foodVolume;
        }
    }

    public boolean addFood(double foodVolume) {
        if (foodVolume <= capacity - this.foodVolume) {
            this.foodVolume += foodVolume;
            return true;
        }
        return false;
    }

    public boolean decreaseFood(double foodVolume) {
        if (foodVolume <= this.foodVolume) {
            this.foodVolume -= foodVolume;
            return true;
        }
        return false;
    }
}