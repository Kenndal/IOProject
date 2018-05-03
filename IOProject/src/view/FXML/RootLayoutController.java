package view.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import view.SystemApp;

import java.io.IOException;

public class RootLayoutController {

    @FXML
    private MenuItem close;

    @FXML
    private MenuItem informations;

    SystemApp systemApp;

    public void setSystemApp(SystemApp systemApp) {
        this.systemApp = systemApp;

    }

    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:resources/Top_Kek.png"));
        alert.setTitle("TopKek");
        alert.setHeaderText("Informacje");
        alert.setContentText("Aplikacja do pakowania kontenerów na statki. \n" +
                "Projekt na przedmiot inżynieria oprogramowania. \n" + "Autor: Jakub Przybyło." );

        alert.setHeight(200);
        alert.setWidth(400);
        alert.showAndWait();
    }


    public void handleExit(){
        try {
            systemApp.getSystem().getFileManagment().writeToFile(systemApp.getSystem().getContainers());
            System.exit(0);
        } catch (IOException e) {

        }
    }

}
