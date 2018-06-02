package view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import logic.System;
import view.FXML.ApplicationController;
import view.FXML.RootLayoutController;
import view.FXML.TopKekController;

import java.io.IOException;

public class SystemApp extends Application {

    private Stage primaryStage;
    private BorderPane rootLayout;
    private System system = new System();

    public System getSystem() {
        return system;
    }

    @Override
    public void start(Stage primaryStage) {

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("TopKek"); // set Title
        this.primaryStage.getIcons().add(new Image("file:resources/Top_Kek.png")); // set application icon

        initRootLayout();

        showSystemOverview();
        this.primaryStage.setResizable(false);

        this.primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                try {
                    system.getFileManagement().writeToFile(system.getContainers());
                } catch (IOException e) {
                }
            }
        });
    }

    public SystemApp() {
        try {
            system.getContainers().addAll(system.getFileManagement().loadFile());
        } catch (IOException | ClassNotFoundException ignored) {
        }
    }

    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SystemApp.class.getResource("FXML/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.show();

            // Set controller
            RootLayoutController controller = loader.getController();
            controller.setSystemApp(this);
            controller.setPrimaryStage(primaryStage);


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showSystemOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SystemApp.class.getResource("FXML/ApplicationView.fxml"));
            AnchorPane applicationView = (AnchorPane) loader.load();

            rootLayout.setCenter(applicationView);

            ApplicationController applicationController = loader.getController();
            applicationController.setSystemApp(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Boolean showTopKekImage(){
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(SystemApp.class.getResource("fxml/TopKek.fxml"));
            AnchorPane page = null;
            page = (AnchorPane) loader.load();

            Stage dialogStage = new Stage();
            dialogStage.setTitle("TopKek");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            dialogStage.setResizable(false);
            dialogStage.getIcons().add(new Image("file:resources/Top_Kek.png"));


            TopKekController controller = loader.getController();
            controller.setDialogStage(dialogStage);

            dialogStage.showAndWait();

            return controller.isOkClicked();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
