package view.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TopKekController {

    @FXML
    private Button topKek;

    @FXML
    private ImageView image;


    Stage dialogStage;
    private Boolean isOkClicked = false;

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    @FXML
    private void initialize() {
        image.setImage(new Image("file:resources/GraphicDesign.jpg"));
    }


    @FXML
    public Boolean isOkClicked() {
        dialogStage.close();
        return isOkClicked;
    }
}
