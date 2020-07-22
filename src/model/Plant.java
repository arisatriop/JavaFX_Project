package model;


import java.time.LocalDate;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author satri
 */
public class Plant {
//    private StringProperty tanaman;
//    private IntegerProperty mulai;
//    private IntegerProperty selesai;
//    private IntegerProperty durasi;
//    private DoubleProperty modal;
//    private DoubleProperty hargaJual;
//    private DoubleProperty estPanen;
//    private DoubleProperty estProfitRupiah;
//    private DoubleProperty estProfitPersen;
//    private DoubleProperty hasilPenjualan;
//    private DoubleProperty profitRupiah;
//    private DoubleProperty profitPersen;
//    //    private ObjectProperty<LocalDate> mulai;
//    //    private ObjectProperty<LocalDate> selesai;
//    private int mulai1, selesai2;
    
    private StringProperty id;
    private StringProperty tanaman;
    private IntegerProperty modal;
    private IntegerProperty hasilPanen;
    private IntegerProperty hargaSatuan;
    private IntegerProperty hasilPenjualan;
    private IntegerProperty profitRupiah;
    private DoubleProperty profitPersen;
    private IntegerProperty durasi;

    public Plant() {
        this(null, 0, 0, 0, 0, 0, 0, 0, null);
    }
    
    public Plant(String tanaman, int modal, int hasilPanen, 
            int hargaSatuan, int hasilPenjualan, int durasi, int profitRupiah, double profitPersen, String id) 
    {
        this.tanaman = new SimpleStringProperty(tanaman);
        this.modal = new SimpleIntegerProperty(modal);
        this.hasilPanen = new SimpleIntegerProperty(hasilPanen);
        this.hargaSatuan = new SimpleIntegerProperty(hargaSatuan);
        this.hasilPenjualan = new SimpleIntegerProperty(hasilPenjualan);
        this.durasi = new SimpleIntegerProperty(durasi);
        this.profitRupiah = new SimpleIntegerProperty(profitRupiah);
        this.profitPersen = new SimpleDoubleProperty(profitPersen);
        this.id = new SimpleStringProperty(id);
    }
    
    public Plant(String tanaman, int profitRupiah, double profitPersen) {
        this.tanaman = new SimpleStringProperty(tanaman);
        this.profitRupiah = new SimpleIntegerProperty(profitRupiah);
        this.profitPersen = new SimpleDoubleProperty(profitPersen);
    }
    
    public Plant(String tanaman, int modal, int hasilPenjualan, double profitPersen) {
        this.tanaman = new SimpleStringProperty(tanaman);
        this.modal = new SimpleIntegerProperty(modal);
        this.hasilPenjualan = new SimpleIntegerProperty(hasilPenjualan);
        this.profitPersen = new SimpleDoubleProperty(profitPersen);
    }
    
    
   
    public String getId() {
        return id.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public StringProperty id() {
        return id;
    }
    
    public String getTanaman() {
        return tanaman.get();
    }
    
    public void setTanaman(String tanaman) {
        this.tanaman.set(tanaman);
    }
    public StringProperty tanamanProperty() {
        return tanaman;
    }
    
    public int getModal() {
        return modal.get();
    }

    public void setModal(int modal) {
        this.modal.set(modal);
    }

    public IntegerProperty modal() {
        return modal;
    }
    
    public int getHasilPanen() {
        return hasilPanen.get();
    }

    public void setHasilPanen(int hasilPanen) {
        this.hasilPanen.set(hasilPanen);
    }

    public IntegerProperty hasilPanen() {
        return hasilPanen;
    }
    
    public int getHargaSatuan() {
        return hargaSatuan.get();
    }

    public void setHargaSatuan(int hargaSatuan) {
        this.hargaSatuan.set(hargaSatuan);
    }

    public IntegerProperty hargaSatuan() {
        return hargaSatuan;
    }
    
    public int getHasilPenjualan() {
        return hasilPenjualan.get();
    }

    public void setHasilPenjualan(int hasilPenjualan) {
        this.hasilPenjualan.set(hasilPenjualan);
    }

    public IntegerProperty hasilPenjualan() {
        return hasilPenjualan;
    }
    
    public int getProfitRupiah() {
        return profitRupiah.get();
    }

    public void setProfitRupiah(int profitRupiah) {
        this.profitRupiah.set(profitRupiah);
    }

    public IntegerProperty profitRupiah() {
        return profitRupiah;
    }
    
    public double getProfitPersen() {
        return profitPersen.get();
    }

    public void setProfitPersen(double profitPersen) {
        this.profitPersen.set(profitPersen);
    }

    public DoubleProperty profitPersen() {
        return profitPersen;
    }
    
    public int getDurasi() {
        return durasi.get();
    }

    public void setDurasi(int durasi) {
        this.durasi.set(durasi);
    }

    public IntegerProperty durasi() {
        return durasi;
    }
    
    
    
//    public LocalDate getMulai() {
//        return mulai.get();
//    }
//    
//    public void setBirthday(LocalDate mulai) {
//        this.mulai.set(mulai);
//    }
//    public ObjectProperty<LocalDate> mulai() {
//        return mulai;
//    }
//
//    public LocalDate getSelesai() {
//        return selesai.get();
//    }
//    
//    public void setSelesai(LocalDate selesai) {
//        this.selesai.set(selesai);
//    }
//    public ObjectProperty<LocalDate> selesai() {
//        return selesai;
//    }
    
    
    
    
}
