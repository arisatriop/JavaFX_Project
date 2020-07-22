/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.Plant;

/**
 * FXML Controller class
 *
 * @author satri
 */
public class PlantOverviewController implements Initializable {

    ObservableList<Plant> plantData;
    WelcomeController welcomeController;
    Stage primaryStage;
    FirstTableController firstTableController;
    SecondTableController secondTableController;
    boolean selectedData;
    private MainApp mainApp;
    
    public void setMainAppController(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    @FXML
    private BorderPane rightSide;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    public void alertBox(String title, String message) {
//        Stage window = new Stage();
//        
//        window.initModality(Modality.NONE);
//        window.setTitle(title);
//        window.setMinWidth(250);
//        
//        Label label = new Label();
//        label.setText(message);
//        
//        VBox layout = new VBox(10);
//        layout.getChildren().addAll(label);
//        layout.setAlignment(Pos.CENTER);
//        
//        Scene scene = new Scene(layout);
//        window.setScene(scene);
//        window.showAndWait();        
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);

        alert.showAndWait();
    }
    
    /**
     * Get access controller for passing data
     */
    public void setWelcomeController(WelcomeController welcomeController) {
        this.welcomeController = welcomeController;
    }
    
    /**
     * Passing Data
     * This method catch list plantData data from MainApp class by WelcomController class.
     */
    public void sendPlantData(ObservableList<Plant> plantData) {
        this.plantData = plantData;
    }
    
    /**
     * Capture data form InsertDataDialog class, send data to WelcomeController class
     * and then data will be add to list
     *  
     */
    public void newData(Plant data) {
        welcomeController.giveData(data);
    }
    
    public void showInsertDataDialog(Plant tempPlant) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("InsertDataDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Insert Data");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Give acsess controller to InserDataDialog class.
            InsertDataDialogController controller = loader.getController();
            controller.setPlantOverviewController(this);
            controller.setDialogStage(dialogStage);
            controller.setPlant(tempPlant);
            controller.setArraySize(plantData.size());
            
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showUpdateDataDialog(Plant plant) {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("UpdateDataDialog.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Update Data");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            // Give acsess controller to InserDataDialog class.
            UpdateDataDialogController controller = loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPlant(plant);
            
            dialogStage.showAndWait();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void handleInsert() {
        Plant tempPlant = new Plant();
        showInsertDataDialog(tempPlant);   
    }
    
    @FXML
    private void handleUpdate() throws IOException {
        if(selectedData) {
            Plant selectedPlant = firstTableController.firstTableView.getSelectionModel().getSelectedItem();
            if(selectedPlant == null) {
                alertBox("Edit data gagal.", "Pilih data yang akan diedit");
            } else {
                showUpdateDataDialog(selectedPlant);
                
                // Refresh table
                handleFirstTable();
            }
        } else {
            Plant selectedPlant = secondTableController.secondTableView.getSelectionModel().getSelectedItem();
            if(selectedPlant == null) {
                alertBox("Edit data gagal", "Pilih data yang akan diedit");
            } else {
                showUpdateDataDialog(selectedPlant);
            
                // Refresh table
                handleSecondTable();
            }
            
        }   
    }
    
    @FXML
    private void handleDelete() {
        if(selectedData) {
            int selectedIndex = firstTableController.firstTableView.getSelectionModel().getSelectedIndex();
            if(selectedIndex < 0) {
                alertBox("Gagal menghapus data", "Pilih data yang akan dihapus");
            } else {
                firstTableController.firstTableView.getItems().remove(selectedIndex);
            }
        } else {
            int selectedIndex = secondTableController.secondTableView.getSelectionModel().getSelectedIndex();
            if(selectedIndex < 0) {
                alertBox("Gagal menghapus data", "Pilih data yang akan dihapus");
            } else {
                secondTableController.secondTableView.getItems().remove(selectedIndex);
            }
        } 
    }
    
    @FXML
    public void handleFirstTable() throws IOException {
        // Load new FXML file and put on scene
        FXMLLoader loader = new FXMLLoader  ();
        loader.setLocation(PlantOverviewController.class.getResource("FirstTable.fxml"));
        AnchorPane table = (AnchorPane) loader.load();

        rightSide.setCenter(table);   
        
        // Give access controller to FirstTable
        firstTableController = loader.getController();
        firstTableController.setPlantOverviewController(this);
        firstTableController.sendPlantData(plantData);
        
        // This code as refrence for delete data
        selectedData = true;
    }
    
    @FXML
    private void handleSecondTable() throws IOException {
        // Load new FXML file and put on scene
        FXMLLoader loader = new FXMLLoader  ();
        loader.setLocation(PlantOverviewController.class.getResource("SecondTable.fxml"));
        AnchorPane table = (AnchorPane) loader.load();

        rightSide.setCenter(table); 
        
        // Give access controller to SecondTable
        secondTableController = loader.getController();
        secondTableController.setPlantOverviewController(this);
        secondTableController.sendPlantData(plantData);
        
        // This code as refrence for delete data
        selectedData = false;
    }
    
    @FXML
    private void handleStatistik() {
        try {
            // Load the fxml file and create a new stage for the popup dialog.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(PlantOverviewController.class.getResource("Chart1.fxml"));
            AnchorPane page = (AnchorPane) loader.load();

            // Create the dialog Stage.
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Statistik");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            
            Chart1Controller controller = loader.getController();
            //controller.passingData(plantData);
            controller.ambilData(plantData);
            
            dialogStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    @FXML
    void handleLogout(ActionEvent event) throws IOException {
        mainApp.initRootLayout();
    }
    
    
    
    
    
    @FXML
    private void handleNew() {
        mainApp.getPlantData().clear();
        mainApp.setPlantFilePath(null);
    }
    
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());

        if (file != null) {
            mainApp.loadPlantDataFromFile(file);
        }
    }
    
    @FXML
    private void handleSave() {
        File plantFile = mainApp.getPlantFilePath();
        if (plantFile != null) {
            mainApp.savePlantDataToFile(plantFile);
        } else {
            handleSaveAs();
        }
    }
    
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();

        // Set extension filter
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                        "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);

        // Show save file dialog
        File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());

        if (file != null) {
            // Make sure it has the correct extension
            if (!file.getPath().endsWith(".xml")) {
                    file = new File(file.getPath() + ".xml");
            }
            mainApp.savePlantDataToFile(file);
        }
    }
    
    @FXML
    private void handleClose() {
        System.exit(0);
    }

    
}
