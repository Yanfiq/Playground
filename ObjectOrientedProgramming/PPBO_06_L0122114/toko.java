package PPBO_06_L0122114;

import java.util.HashMap;

public class toko{
    private static toko Instance = null;
    private HashMap<String, barang> rak = new HashMap<String, barang>();
    private int totalTransaksi = 0;
    private int uangKas = 3108000; //modal awal
    private float keuntungan = 0.00f;
    private final float pajak = 0.11f;

    private toko(){
        boolean validInput = false;
        float persen = 0.0f;
        while(validInput == false){
            validInput = true;
            try{
                System.out.print("\rPengen ngambil keuntungan seberapa (dalam %) : "); persen = scanner.scanner.nextInt();
            }catch(java.util.InputMismatchException ex){
                System.out.print("\u001b[2K"); // Clear the line
                System.out.print("\rCuma nerima input berbentuk float");
                validInput = false;
                scanner.scanner.nextLine();
            }finally{
                System.out.print("\u001b[1A"); // Move up one line
                System.out.print("\u001b[2K"); // Clear the line
            }
        }
        this.keuntungan = persen/100;
    };
    public static toko instance(){
        if(Instance == null){
            Instance = new toko();
        }
        return Instance;
    }

    public barang getBarang(String kdBarang){
        return rak.get(kdBarang);
    }

    public float getPajak(){
        return pajak;
    }
    public float getUntung(){
        return keuntungan;
    }
    public int getKas(){
        return uangKas;
    }
    public int getTotalTransaksi(){
        return totalTransaksi;
    }
    public void incrementTransaksi(){
        totalTransaksi++;
    }

    public void displayBarang(){
        System.out.println(String.format("|%-11s|%-20s|%-15s|%-13s|", "Kode barang", "Nama barang", "Harga barang", "jumlah barang"));
        for(String kdBarang : rak.keySet()){
            barang it = rak.get(kdBarang);
            System.out.println(String.format("|%-11s|%-20s|Rp %9d,00|%13d|", kdBarang, it.getName(), it.getPrice(), it.getStock()));
        }
    }

    public void jualBarang(String kdBarang, int qty){
        if(getBarang(kdBarang) == null){
            System.out.println("barangnya ndak ada ygy");
            return;
        }
        barang Barang = getBarang(kdBarang);
        if(Barang.getStock() <= 0){
            System.out.println("Barangnya habis ygy");
            return;
        }
        Barang.modifyProperties(Barang.getName(), Barang.getPrice(), Barang.getStock()-qty);

        int hargaTotal = Barang.getPrice() * qty;
        uangKas += hargaTotal*pajak+hargaTotal;
    }

    public void restokBarang(String kdBarang, int qty){
        kdBarang = kdBarang.toLowerCase();
        if(getBarang(kdBarang) == null){
            System.out.println("barangnya ndak ada ygy");
        }
        barang Barang = getBarang(kdBarang);
        Barang.modifyProperties(Barang.getName(), Barang.getPrice(), Barang.getStock()+qty);

        int hargaTotal = Barang.getBuyPrice() * qty;
        uangKas -= hargaTotal*pajak+hargaTotal;
    }

    public void barangBaru(String kdBarang, String namaBarang, int hargaBeli, int qty){
        barang Barang = new barang(namaBarang, hargaBeli, qty);
        rak.put(kdBarang, Barang);

        int hargaTotal = Barang.getBuyPrice() * qty;
        uangKas -= hargaTotal*pajak+hargaTotal;
    }

    public void initialize(){
        toko.instance().barangBaru("S01E01", "Indomie", 3500, 100);
        toko.instance().barangBaru("S01E02", "Sarimie", 3500, 100);
        toko.instance().barangBaru("S01E03", "Mie Sedaap", 3500, 100);
        toko.instance().barangBaru("S01E04", "Supermi", 3500, 100);
        toko.instance().barangBaru("S01E05", "Mie Sukses", 3500, 100);
        toko.instance().barangBaru("S01E06", "Mie Gepeng", 3500, 100);
        toko.instance().barangBaru("S01E07", "Gaga 100", 3500, 100);
        toko.instance().barangBaru("S01E08", "Mi ABC", 3500, 100);
    }
}