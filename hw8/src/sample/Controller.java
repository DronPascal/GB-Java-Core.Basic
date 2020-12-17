package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {

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
    public void initialize(){
        playAgainBtn.setVisible(false);
        infoLabelStatic.setVisible(false);
        infoLabelDynamic.setVisible(false);
    }

    @FXML
    void onCheckBtnClicked() {
        
    }

    @FXML
    void onPlayAgainBtnClicked() {

    }

}
