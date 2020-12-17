package sample;

public class GameMaster {
    private static final int MAX_TRY_COUNT = 3;
    private static final int MAX_NUMBER = 9;
    private int attemptNumber = 1;
    int randomNumber = (int) (Math.random() * (MAX_NUMBER + 1));

    public boolean isNumberCorrect(int number) {
        return number == randomNumber;
    }

    public void increaseAttemptNumber(){
        attemptNumber++;
    }

    public boolean isGameOver(){
        return attemptNumber==MAX_TRY_COUNT;
    }

    public void wipeGameData(){
        attemptNumber=1;
        randomNumber = (int) (Math.random() * (MAX_NUMBER + 1));
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }

    public static String fixNumerical(int num, String... arr) {
        int preLastDigit = num % 100 / 10;
        if (preLastDigit == 1) {
            return String.format("%d %s", num, arr[2]);
        }

        int lastDigit = num % 10;
        switch (lastDigit) {
            case 1:
                return String.format("%d %s", num, arr[0]);
            case 2:
            case 3:
            case 4:
                return String.format("%d %s", num, arr[1]);
            default:
                return String.format("%d %s", num, arr[2]);
        }
    }
}

