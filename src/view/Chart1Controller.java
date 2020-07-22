 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import model.Plant;

/**
 * FXML Controller class
 *
 * @author satri
 */
public class Chart1Controller implements Initializable {   
    
    ObservableList<Plant> tempList = FXCollections.observableArrayList();
    ObservableList<Plant> listKeuntungan = FXCollections.observableArrayList();
    ObservableList<Plant> listKeuntunganItem = FXCollections.observableArrayList();
    
    @FXML
    private PieChart pieChart;
    @FXML
    private BarChart<String, Integer> barChart;
    @FXML
    private BarChart<String, Double> barChart2;

    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }    
    
    /**
     * Graph Keuntungan
     */
    public void ambilData(ObservableList<Plant> plantData) {

        // add data to the new observable list
        for(int i=0; i<plantData.size(); i++) {
            tempList.add(plantData.get(i));
        }
        
        processData();
    }
    
    /**
     * Data Processing
     * Create 1 for each category, add to list.
     * Add up all value for the same category.
     */
    public void processData() {
        int bolean = 0;
        for(int i=0; i<tempList.size(); i++) {
            if(i != (tempList.size()-1)) {
                for(int j=(i+1); j<tempList.size(); j++) {
                    if(tempList.get(i).getTanaman().equals(tempList.get(j).getTanaman())) {
                        bolean = 0;
                        break;
                    } else {
                        bolean = 1;
                    }
                }
                if(bolean == 1) {
                    // Create and insert object to array
                    listKeuntungan.add(new Plant(tempList.get(i).getTanaman(), 0, 0));
                    listKeuntunganItem.add(new Plant(tempList.get(i).getTanaman(), 0, 0, 0));
                } 
            } else {
                // Re-create last element of "tempList" and insert to array
                listKeuntungan.add(new Plant(tempList.get(i).getTanaman(), 0, 0));
                listKeuntunganItem.add(new Plant(tempList.get(i).getTanaman(), 0, 0, 0));
            }
        }
        
        countProfitItemRupiah();
        countProfitTotal();
        countProfitItemPercent();
        addChartData();
    }
    
    /**
     * Count profit each item (rupiah)
     */
    public void countProfitItemRupiah() {
        for(int i=0; i<listKeuntungan.size(); i++) {
            for(int j=0; j<tempList.size(); j++) {
                if(listKeuntungan.get(i).getTanaman().equals(tempList.get(j).getTanaman())) {
                    listKeuntungan.get(i).setProfitRupiah(listKeuntungan.get(i).getProfitRupiah() + 
                            tempList.get(j).getProfitRupiah());
                }
            }
        } 
    }
    
    /**
     * Count profit total (percent)
     */
    public void countProfitTotal() {
        double value = 0;
        for(int i=0; i<listKeuntungan.size(); i++) {
            value = value + listKeuntungan.get(i).getProfitRupiah();
        }
        for(int i=0; i<listKeuntungan.size(); i++) {
            listKeuntungan.get(i).setProfitPersen(listKeuntungan.get(i).getProfitRupiah() * 100 / value);
        }
    }
    
    /**
     * Count profit each item (percent)
     */
    public void countProfitItemPercent() {
        //int hasil = 0;
        for(int i=0; i<listKeuntunganItem.size(); i++) {
            for(int j=0; j<tempList.size(); j++) {
                if(listKeuntunganItem.get(i).getTanaman().equals(tempList.get(j).getTanaman())) {
                    listKeuntunganItem.get(i).setModal(listKeuntunganItem.get(i).getModal() +
                            tempList.get(j).getModal());
                    listKeuntunganItem.get(i).setHasilPenjualan(listKeuntunganItem.get(i).getHasilPenjualan() +
                            tempList.get(j).getHasilPenjualan());
                }
            }
            listKeuntunganItem.get(i).setProfitPersen( (listKeuntunganItem.get(i).getHasilPenjualan() -
                    listKeuntunganItem.get(i).getModal()) * 100 / listKeuntunganItem.get(i).getModal());
        } 
    }
    
    public void addChartData() {
        ObservableList<PieChart.Data> dataPieChart = FXCollections.observableArrayList();
        XYChart.Series<String, Integer> dataDua = new XYChart.Series();
        XYChart.Series<String, Double> dataTiga = new XYChart.Series();
        
        DecimalFormat digit = new DecimalFormat("0.00");
        
        
        // insert existing data to chart object
        for(int i=0; i<listKeuntungan.size(); i++) {
//            dataPieChart.add(new PieChart.Data(listKeuntungan.get(i).getTanaman()+
//                    " "+listKeuntungan.get(i).getProfitPersen()+"%", 
//                    listKeuntungan.get(i).getProfitPersen()));
            dataPieChart.add(new PieChart.Data(listKeuntungan.get(i).getTanaman()+
                    " "+ digit.format(listKeuntungan.get(i).getProfitPersen())+"%", 
                    listKeuntungan.get(i).getProfitPersen()));
            
            dataDua.getData().add(new XYChart.Data<>(listKeuntungan.get(i).getTanaman(), 
                    listKeuntungan.get(i).getProfitRupiah()));
        }  
        
        for(int i=0; i<listKeuntunganItem.size(); i++) {
            dataTiga.getData().add(new XYChart.Data<>(listKeuntunganItem.get(i).getTanaman(), 
                    listKeuntunganItem.get(i).getProfitPersen()));
        }
        
        pieChart.setData(dataPieChart);
        barChart.getData().add(dataDua);
        barChart2.getData().add(dataTiga);
    }
    
}

