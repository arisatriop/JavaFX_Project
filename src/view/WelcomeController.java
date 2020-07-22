package view;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Plant;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author satri
 */
public class WelcomeController {
    
    Stage primaryStage;
    MainApp mainApp;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
         
    @FXML
    void handleMainApp(ActionEvent event) throws IOException {
        if(tfUsername.getText().equals("kelompok17") && tfPassword.getText().equals("kelompok17")) {
        
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("PlantOverview.fxml"));
            BorderPane page = (BorderPane) loader.load();
            Scene plantOverviewScene = new Scene(page);

            // Give access controller to PlantOverview
            PlantOverviewController controller = loader.getController();
            controller.setWelcomeController(this);
            // Send Plant data to PlantOverview Class
            controller.sendPlantData(mainApp.getPlantData());
            // Give access MainAppController to PlantOverview class
            controller.setMainAppController(mainApp);

            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(plantOverviewScene);
            window.show(); 

            controller.handleFirstTable();
        } else {
//            Stage window = new Stage();
//        
//            window.initModality(Modality.NONE);
//            window.setTitle("Login Failed!");
//            window.setMinWidth(250);
//
//            Label label = new Label();
//            label.setText("Incorrect username or password.");
//
//            VBox layout = new VBox(10);
//            layout.getChildren().addAll(label);
//            layout.setAlignment(Pos.CENTER);
//
//            Scene scene = new Scene(layout);
//            window.setScene(scene);
//            window.showAndWait();      
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Login Failed");
            alert.setContentText("Incorrect email or password.");

            alert.showAndWait();
        }
    }
    
    
    
    /**
     * Get access controller for passing data
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
    /**
     * Add data to list
     *  
     */
    public void giveData(Plant plant) {
        mainApp.getPlantData().add(plant);
    }
    
    
}
