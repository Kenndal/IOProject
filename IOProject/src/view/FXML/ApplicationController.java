package view.FXML;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import logic.Statistics;
import view.SystemApp;

import java.util.Arrays;
import java.util.Objects;

public class ApplicationController {

    @FXML
    private Button generateButton;

    @FXML
    private TextField sentConteinersTextField;

    @FXML
    private TextField conteinresTextField;

    @FXML
    private BarChart<String, Float> chart;

    @FXML
    private TextField numberOfTextField;

    @FXML
    private Button sendButton;

    @FXML
    private CategoryAxis xAxis;

    private SystemApp systemApp;
    private Statistics statistics;

    private ObservableList<String> shipsName = FXCollections.observableArrayList();
    @FXML
    private void initialize() {

        String[] ships = {"Ship1","Ship2","Ship3","Ship4","Ship5"};
        shipsName.addAll(Arrays.asList(ships));
        xAxis.getCategories().addAll(shipsName);
    }


    public void setSystemApp(SystemApp systemApp){
        this.systemApp = systemApp;
        numberOfTextField.setText(String.valueOf(systemApp.getSystem().getContainers().size()));
    }

    public void handleGenerate(){
        if(conteinresTextField.getText() != null && conteinresTextField.getText().length() != 0 ) {
            systemApp.getSystem().genarateConteiners(Integer.valueOf(conteinresTextField.getText()));
            numberOfTextField.setText(String.valueOf(systemApp.getSystem().getContainers().size()));

        }else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(systemApp.getPrimaryStage());
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Żle wypełnione pole!");
            alert.setContentText("Pole nie może pozostać puste przy generowaniu kontenerów!");
            alert.showAndWait();
        }
    }

    public void handleSend(){
        if(systemApp.getSystem().getContainers().size() != 0) {
            statistics = systemApp.getSystem().sendConteiners();
            numberOfTextField.setText(String.valueOf(systemApp.getSystem().getContainers().size()));

            xAxis.getCategories().clear();


            XYChart.Series<String, Float> series = new XYChart.Series<>();
            chart.getData().clear();
            for (int i = 0; i < systemApp.getSystem().getShips().size(); i++) {
                series.getData().add(new XYChart.Data<>(shipsName.get(i), statistics.getPercentOfLoad().get(i)));
            }

            chart.getData().add(series);

            sentConteinersTextField.setText(String.valueOf(statistics.getNumberOfConteiners()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(systemApp.getPrimaryStage());
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Brak kontenerów!");
            alert.setContentText("Wygeneruj wiecej kontenerów.");
            alert.showAndWait();
        }

    }

}
