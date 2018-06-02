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
import logic.Observer;
import logic.Statistics;
import view.SystemApp;

import java.util.Arrays;

public class ApplicationController implements Observer {

    @FXML
    private Button generateButton;

    @FXML
    private TextField sentContainersTextField;

    @FXML
    private TextField containersTextField;

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
        this.systemApp.getSystem().subscribe(this); // start observe the System class to update information
    }

    public void handleGenerate(){
        if(containersTextField.getText() != null ) {
            try {
                int tempNumberOfContainers = Integer.valueOf(containersTextField.getText());
                if(tempNumberOfContainers >= 0)
                    systemApp.getSystem().generateContainers(tempNumberOfContainers);
                else{
                    alertUp("Wartość nie może być mniejsza lub równa 0!");
                }
            }catch (RuntimeException e){
                alertUp("Pole nie może pozostać puste przy generowaniu kontenerów, albo zawierać inne znaki niż liczby!");
            }
            numberOfTextField.setText(String.valueOf(systemApp.getSystem().getContainers().size()));

        }else {
            alertUp("Pole nie może pozostać puste przy generowaniu kontenerów, albo zawierać inne znaki niż liczby!");
        }
    }

    public void handleSend(){
        if(systemApp.getSystem().getContainers().size() != 0) {
            statistics = systemApp.getSystem().sendContainers();
            numberOfTextField.setText(String.valueOf(systemApp.getSystem().getContainers().size()));

            xAxis.getCategories().clear();


            XYChart.Series<String, Float> series = new XYChart.Series<>();
            chart.getData().clear();
            for (int i = 0; i < systemApp.getSystem().getShips().size(); i++) {
                series.getData().add(new XYChart.Data<>(shipsName.get(i), statistics.getPercentOfLoad().get(i)));
            }

            chart.getData().add(series);

            sentContainersTextField.setText(String.valueOf(statistics.getNumberOfContainers()));
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(systemApp.getPrimaryStage());
            alert.setTitle("Uwaga!");
            alert.setHeaderText("Brak kontenerów!");
            alert.setContentText("Wygeneruj wiecej kontenerów.");
            alert.showAndWait();
        }

    }


    private void alertUp(String alertMessage){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initOwner(systemApp.getPrimaryStage());
        alert.setWidth(400);
        alert.setHeight(400);
        alert.setTitle("Uwaga!");
        alert.setHeaderText("Żle wypełnione pole!");
        alert.setContentText(alertMessage);
        alert.showAndWait();
    }

    /**
     * Refresh text field every time when it's changing
     */
    @Override
    public void inform() {
        numberOfTextField.setText(String.valueOf(systemApp.getSystem().getContainers().size()));
    }
}
