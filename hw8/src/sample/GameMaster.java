package sample;

public class GameMaster {
    public static final int MAX_TRY_COUNT = 3;
    public static final int MAX_NUMBER = 9;
    private int attemptNumber = 1;
    int hiddenNumber = (int) (Math.random() * (MAX_NUMBER + 1));

    public boolean isNumberCorrect(int number) {
        return number == hiddenNumber;
    }

    public void increaseAttemptNumber(){
        attemptNumber++;
    }

    public boolean isGameOver(int inputNumber){
        return attemptNumber>MAX_TRY_COUNT || inputNumber == hiddenNumber;
    }

    public void wipeGameData(){
        attemptNumber=1;
        hiddenNumber = (int) (Math.random() * (MAX_NUMBER + 1));
    }

    public int getAttemptRemaining(){
        return MAX_TRY_COUNT-attemptNumber+1;
    }

    public int getHiddenNumber() {
        return hiddenNumber;
    }

    public int getAttemptNumber() {
        return attemptNumber;
    }

    public void setAttemptNumber(int attemptNumber) {
        this.attemptNumber = attemptNumber;
    }
}

