package PPBO_06_L0122114;

public class barang{
    private String namaBarang;
    private int hargaJual;
    private int hargaBeli;
    private int jumlahStok;

    public barang(String namaBarang, int harga, int jumlahStok){
        this.namaBarang = namaBarang;
        this.hargaBeli = harga;
        this.hargaJual = (int)(harga*toko.instance().getUntung()) + harga;
        this.jumlahStok = jumlahStok;
    }
    public void modifyProperties(String namaBarang, int harga, int jumlahStok){
        this.namaBarang = namaBarang;
        this.hargaJual = harga;
        this.jumlahStok = jumlahStok;
    }
    public String getName(){
        return namaBarang;
    }
    public int getBuyPrice(){
        return hargaBeli;
    }
    public int getPrice(){
        return hargaJual;
    }
    public int getStock(){
        return jumlahStok;
    }
}