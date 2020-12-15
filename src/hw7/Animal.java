package hw7;

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

    public boolean setRunLimit(double runLimit) {
        if (runLimit >= 0) {
            this.runLimit = runLimit;
            return true;
        }
        return false;
    }

    public boolean setJumpLimit(double jumpLimit) {
        if (jumpLimit >= 0) {
            this.jumpLimit = jumpLimit;
            return true;
        }
        return false;
    }

    public boolean setSwimLimit(double swimLimit) {
        if (swimLimit >= 0) {
            this.swimLimit = swimLimit;
            return true;
        }
        return false;
    }
}