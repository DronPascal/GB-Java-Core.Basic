package hw6;

public abstract class Animal {
    private double runLimit, jumpLimit, swimLimit;

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

    public double getRunLimit() {
        return runLimit;
    }

    public double getJumpLimit() {
        return jumpLimit;
    }

    public double getSwimLimit() {
        return swimLimit;
    }

    public void setRunLimit(double runLimit) {
        this.runLimit = runLimit;
    }

    public void setJumpLimit(double jumpLimit) {
        this.jumpLimit = jumpLimit;
    }

    public void setSwimLimit(double swimLimit) {
        this.swimLimit = swimLimit;
    }
}
