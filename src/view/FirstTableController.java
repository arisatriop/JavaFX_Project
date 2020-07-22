/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Plant;

/**
 * FXML Controller class
 *
 * @author satri
 */
public class FirstTableController implements Initializable {
    
    MainApp mainApp;
    PlantOverviewController plantOverviewController;
    

    @FXML
    public TableView<Plant> firstTableView;
    @FXML
    private TableColumn<Plant, String> colId;
    @FXML
    private TableColumn<Plant, String> colTanaman;
    @FXML
    private TableColumn<Plant, Integer> colProfitRupiah;
    @FXML
    private TableColumn<Plant, Double> colProfitPersen;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Set up the columns in the table
        colId.setCellValueFactory(new PropertyValueFactory<Plant, String>("id"));
        colTanaman.setCellValueFactory(new PropertyValueFactory<>("tanaman"));
        colProfitRupiah.setCellValueFactory(new PropertyValueFactory<Plant, Integer>("profitRupiah"));
        colProfitPersen.setCellValueFactory(new PropertyValueFactory<Plant, Double>("profitPersen"));
    }    
    
    /**
     * Get access controller for passing data
     */
    public void setPlantOverviewController(PlantOverviewController plantOverviewController) {
        this.plantOverviewController = plantOverviewController;
    }
    
    /**
     * Passing Data
     * Catch data from PlantOverview Class and set to table
     */
    public void sendPlantData(ObservableList<Plant> plantData) {
        firstTableView.setItems(plantData);
    }
    
}
