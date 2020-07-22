/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Plant;

/**
 * FXML Controller class
 *
 * @author satri
 */
public class UpdateDataDialogController implements Initializable {
    
    Stage dialogStage;
    Plant plant;

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
        
        tfTanaman.setText(plant.getTanaman());
        tfModal.setText(Integer.toString(plant.getModal()));
        tfHasilPanen.setText(Integer.toString(plant.getHasilPanen()));
        tfHargaSatuan.setText(Integer.toString(plant.getHargaSatuan()));
        tfHasilPenjualan.setText(Integer.toString(plant.getHasilPenjualan()));
        tfDurasi.setText(Integer.toString(plant.getDurasi()));
    }

    @FXML
    private void handleSubmit() {
        if(tfTanaman.getText().isEmpty() || tfModal.getText().isEmpty() ||
                tfHasilPanen.getText().isEmpty() || tfHargaSatuan.getText().isEmpty() ||
                tfHasilPenjualan.getText().isEmpty() || tfDurasi.getText().isEmpty()) {
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Gagal mengubah data");
            alert.setContentText("Data tidak boleh kosong, semua kolom wajib diisi.");

            alert.showAndWait();
            
        } else {
            DecimalFormat digit = new DecimalFormat("0.00");
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

            dialogStage.close();
            
            
        }
    }
    
    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
    
}
