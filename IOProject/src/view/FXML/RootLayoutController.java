package view.FXML;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import view.SystemApp;

import java.io.File;
import java.io.IOException;

public class RootLayoutController {

    @FXML
    private MenuItem close;

    @FXML
    private MenuItem load;

    @FXML
    private MenuItem informations;

    @FXML
    private MenuItem topKek;

    private Pane layout;

    private SystemApp systemApp;
    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void setSystemApp(SystemApp systemApp) {
        this.systemApp = systemApp;

    }

    /**
     * Menu item function to load external files with containers
     */
    @FXML
    private void handleLoad(){
        String errors = "";
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "csv files (*.csv)", "*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(primaryStage);

        if(file != null){
            systemApp.getSystem().loadExternalData(file.getPath());
            if(systemApp.getSystem().getLoaderCSV().getErrors().size() != 0){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.initOwner(systemApp.getPrimaryStage());
                alert.setWidth(700);
                alert.setHeight(700);
                alert.setTitle("Uwaga!");
                alert.setHeaderText("Błąd formatu danych");
                for (String error:systemApp.getSystem().getLoaderCSV().getErrors()) {
                    errors += "\n";
                    errors += error;
                }
                alert.setContentText(errors);
                alert.showAndWait();
            }
        }
    }

    /**
     * Menu item function to reset system data in application and files
     */
    @FXML
    private void handleReset(){
        systemApp.getSystem().reset();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:resources/Top_Kek.png"));
        alert.setTitle("Reset");
        alert.setHeaderText("System został zresetowany");
        alert.showAndWait();
    }

    @FXML
    private void handleKEK(){
        boolean closeClicked = systemApp.showTopKekImage();
    }

    /**
     * Menu item function to display basic information about application
     */
    @FXML
    private void handleAbout(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        ((Stage)alert.getDialogPane().getScene().getWindow()).getIcons().add(new Image("file:resources/Top_Kek.png"));
        alert.setTitle("TopKek");
        alert.setHeaderText("Informacje");
        alert.setContentText("Aplikacja do pakowania kontenerów na statki. \n" +
                "Projekt na przedmiot Inżynieria Orogramowania. \n" + "Autor: Jakub Przybyło." );

        alert.setHeight(200);
        alert.setWidth(400);
        alert.showAndWait();
    }

    /**
     * Menu item function to exit the application with saving all data in files
     */
    @FXML
    private void handleExit(){
        try {
            systemApp.getSystem().getFileManagement().writeToFile(systemApp.getSystem().getContainers());
            System.exit(0);
        } catch (IOException e) {

        }
    }

}
