package sample;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    GameMaster gameMaster;

    @FXML
    private TextField numberTfld;

    @FXML
    private Button checkBtn;

    @FXML
    private Label tryCountLabel;

    @FXML
    private Button playAgainBtn;

    @FXML
    private Label infoLabelStatic;

    @FXML
    private Label infoLabelDynamic;

    @FXML
    public void initialize() {
        gameMaster = new GameMaster();
        playAgainBtn.setVisible(false);
        infoLabelStatic.setVisible(false);
        infoLabelDynamic.setVisible(false);
        checkBtn.setDisable(false);
        numberTfld.setDisable(false);
        tryCountLabel.setVisible(true);
        tryCountLabel.setText("Осталось " + fixNumerical(gameMaster.MAX_TRY_COUNT, "попытка", "попытки", "попыток"));
        numberTfld.clear();
    }

    @FXML
    void onCheckBtnClicked() {
        String inputNumber = numberTfld.getText();
        if (!inputNumber.isBlank()) {
            try {
                int number = Integer.parseInt(inputNumber);
                if (number < 0 || number > 9) {
                    new Alert(Alert.AlertType.INFORMATION,
                            "Введенное значение не входит в интервал от 0 до 9 (включительно).\nПовторите ввод.").show();
                } else {
                    gameMaster.increaseAttemptNumber();
                    if (gameMaster.isGameOver(number)) {
                        playAgainBtn.setVisible(true);
                        tryCountLabel.setVisible(true);
                        infoLabelStatic.setVisible(false);
                        infoLabelDynamic.setVisible(false);
                        tryCountLabel.setVisible(false);
                        checkBtn.setDisable(true);
                        numberTfld.setDisable(true);
                        if (gameMaster.isNumberCorrect(number)){
                            new Alert(Alert.AlertType.INFORMATION,
                                    "Поздравляем!\nВы угадали, это было число "+ number).show();
                        }
                        else if(gameMaster.getAttemptRemaining()==0) {
                            new Alert(Alert.AlertType.INFORMATION,
                                    "Вы не успели угадать число!\nЯ загадывал " + gameMaster.getHiddenNumber()).show();
                        }
                    }
                    else {
                        infoLabelStatic.setVisible(true);
                        infoLabelDynamic.setVisible(true);
                        if (number<gameMaster.getHiddenNumber())
                            infoLabelDynamic.setText("Меньше");
                        else
                            infoLabelDynamic.setText("Больше");
                        tryCountLabel.setText("Осталось " + fixNumerical(gameMaster.getAttemptRemaining(), "попытка", "попытки", "попыток"));
                        numberTfld.clear();
                    }
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                new Alert(Alert.AlertType.INFORMATION,
                        "Введенное значение не является целым числом.\nПовторите ввод.").show();
            }
        }
    }

    @FXML
    void onPlayAgainBtnClicked() {
        initialize();
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


