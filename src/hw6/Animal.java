package hw6;

public abstract class Animal {
    protected double runLimit, jumpLimit, swimLimit;

    public Animal() {
        runLimit = 0;
        jumpLimit = 0;
        swimLimit = 0;
    }

    public Animal(double runLimit, double jumpLimit, double swimLimit) {
        this.runLimit = runLimit;
        this.jumpLimit = jumpLimit;
        this.swimLimit = swimLimit;
    }

    public void run(double distance) {
        System.out.println("run(" + distance + "): " + (distance >= 0 && distance <= runLimit ? "true" : "false"));
    }

    public void jump(double distance) {
        System.out.println("jump(" + distance + "): " + (distance >= 0 && distance <= jumpLimit ? "true" : "false"));
    }

    public void swim(double distance) {
        System.out.println("swim(" + distance + "): " + (distance >= 0 && distance <= swimLimit ? "true" : "false"));
    }

    public void printAnimalRJSInfo() {
        System.out.println("Animal " + this.toString() + " runLimit: " + runLimit + "; jumpLimit: " + jumpLimit + "; swimLimit: " + swimLimit);
    }
}
