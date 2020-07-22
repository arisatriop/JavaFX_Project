/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Plant;

/**
 * FXML Controller class
 *
 * @author satri
 */
public class InsertDataDialogController implements Initializable {
    
    private int arraySize;
    private Plant plant;
    Stage dialogStage;
    PlantOverviewController plantOverviewController;

    @FXML
    private TextField tfTanaman;
    @FXML
    private TextField tfModal;
    @FXML
    private TextField tfHasilPenjualan;
    @FXML
    private TextField tfHasilPanen;
    @FXML
    private TextField tfHargaSatuan;
    @FXML
    private TextField tfDurasi;
    
    
//    public Plant getPlant() {
//        return plant;
//    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    public void setPlant(Plant plant) {
        this.plant = plant;
    }
    
    public void setArraySize(int arraySize) {
        this.arraySize = arraySize;
    }
    
    public void setPlantOverviewController(PlantOverviewController plantOverviewController) {
        this.plantOverviewController = plantOverviewController;
    }
    
    public String idRandom() {
        String[] alphabet = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J",
        "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
        int n1, n2, n3;
        String c1, c2, c3;
        String conc;
        
        Random random = new Random();
        n1 = random.nextInt(25);
        n2 = random.nextInt(25);
        n3 = random.nextInt(25);

        c1 = alphabet[n1];
        c2 = alphabet[n2];
        c3 = alphabet[n3];
        conc = c1+c2+c3;
        
        return conc;
    }
    
    @FXML
    private void handleSubmit() {
        if(tfTanaman.getText().isEmpty() || tfModal.getText().isEmpty() ||
                tfHasilPanen.getText().isEmpty() || tfHargaSatuan.getText().isEmpty() ||
                tfHasilPenjualan.getText().isEmpty() || tfDurasi.getText().isEmpty()) {
            plantOverviewController.alertBox("Gagal menambahkan data", "Data tidak boleh kosong, semua kolom wajib diisi.");
        } else {
            DecimalFormat digit = new DecimalFormat("0.00");
            plant.setId("TNM-" + Integer.toString(arraySize+10001) + idRandom());
            plant.setTanaman(tfTanaman.getText());
            plant.setModal(Integer.parseInt(tfModal.getText()));
            plant.setHasilPanen(Integer.parseInt(tfHasilPanen.getText()));
            plant.setHargaSatuan(Integer.parseInt(tfHargaSatuan.getText()));
            plant.setHasilPenjualan(Integer.parseInt(tfHasilPenjualan.getText()));
            plant.setDurasi(Integer.parseInt(tfDurasi.getText()));
            plant.setProfitRupiah(Integer.parseInt(tfHasilPenjualan.getText()) - Integer.parseInt(tfModal.getText()));
            
            double profitPersen = (Double.parseDouble(tfHasilPenjualan.getText()) - Double.parseDouble(tfModal.getText()))
                    * 100 / Integer.parseInt(tfModal.getText());
            plant.setProfitPersen(Double.parseDouble(digit.format(profitPersen)));
           
            plantOverviewController.newData(this.plant);
            dialogStage.close();
        }
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
        
    }
    
}
