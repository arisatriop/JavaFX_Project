/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import model.Plant;
import model.PlantListWrapper;

/**
 *
 * @author satri
 */
public class MainApp extends Application {
    
    AnchorPane rootLayout;
    Stage primaryStage;
    private ObservableList<Plant> plantData = FXCollections.observableArrayList();
    
    public MainApp() {
    }
    
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    
    public ObservableList<Plant> getPlantData() {
        return plantData;
    }
    
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Plant App");

        initRootLayout();
    }
    
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("Welcome.fxml"));
            rootLayout = (AnchorPane) loader.load();
            
            // Show the scene containing the root layout.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            
            // Give access controller to Welcome page
            WelcomeController controller = loader.getController();
            controller.setMainApp(this);
            
            primaryStage.show();
        
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        // Try to load last opened plant file.
        File file = getPlantFilePath();
        if (file != null) {
            loadPlantDataFromFile(file);
        }
    }
     
    
    /**
    * Returns the plant file preference, i.e. the file that was last opened.
    * The preference is read from the OS specific registry. If no such
    * preference can be found, null is returned.
    * 
    * @return
    */
    public File getPlantFilePath() {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefs.get("filePath", null);
        if (filePath != null) {
            return new File(filePath);
        } else {
            return null;
        }
    }
    
    /**
    * Sets the file path of the currently loaded file. The path is persisted in
    * the OS specific registry.
    * 
    * @param file the file or null to remove the path
    */
    public void setPlantFilePath(File file) {
        Preferences prefs = Preferences.userNodeForPackage(MainApp.class);
        if (file != null) {
            prefs.put("filePath", file.getPath());

            // Update the stage title.
            primaryStage.setTitle("PlantApp - " + file.getName());
        } else {
            prefs.remove("filePath");

            // Update the stage title.
            primaryStage.setTitle("PlantApp");
        }
    }
    
    /**
    * Loads plant data from the specified file. The current person data will
    * be replaced.
    * 
    * @param file
    */
    public void loadPlantDataFromFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PlantListWrapper.class);
            Unmarshaller um = context.createUnmarshaller();

            // Reading XML from the file and unmarshalling.
            PlantListWrapper wrapper = (PlantListWrapper) um.unmarshal(file);

            plantData.clear();
            plantData.addAll(wrapper.getPlants());

            // Save the file path to the registry.
            setPlantFilePath(file);

        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not load data");
            alert.setContentText("Could not load data from file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    /**
    * Saves the current plant data to the specified file.
    * 
    * @param file
    */
    public void savePlantDataToFile(File file) {
        try {
            JAXBContext context = JAXBContext.newInstance(PlantListWrapper.class);
            Marshaller m = context.createMarshaller();
            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            // Wrapping our person data.
            PlantListWrapper wrapper = new PlantListWrapper();
            wrapper.setPlants(plantData);

            // Marshalling and saving XML to the file.
            m.marshal(wrapper, file);

            // Save the file path to the registry.
            setPlantFilePath(file);
        } catch (Exception e) { // catches ANY exception
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Could not save data");
            alert.setContentText("Could not save data to file:\n" + file.getPath());

            alert.showAndWait();
        }
    }
    
    
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
