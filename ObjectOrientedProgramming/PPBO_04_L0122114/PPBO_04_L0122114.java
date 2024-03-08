/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C
*/

package PPBO_04_L0122114;

import java.util.ArrayList;
import java.util.Scanner;

class input{
    public static Scanner scanner = new Scanner(System.in);
}

class asserts{
    public static boolean Enabled = false;
}

class barang{
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

class toko{
    private static toko Instance = null;
    private ArrayList<barang> rak = new ArrayList<>();
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
                System.out.print("\rPengen ngambil keuntungan seberapa (dalam %) : "); persen = input.scanner.nextInt();
            }catch(java.util.InputMismatchException ex){
                System.out.print("\u001b[2K"); // Clear the line
                System.out.print("\rCuma nerima input berbentuk float");
                validInput = false;
                input.scanner.nextLine();
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

    public barang getBarang(String namaBarang){
        for(barang it : rak){
            if(it != null && namaBarang.toLowerCase().equals(it.getName().toLowerCase())){
                return it;
            }
        }
        return null;
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
        System.out.println(String.format("%-20s|%-15s|%-13s|", "Nama barang", "Harga barang", "jumlah barang"));
        for(barang it : rak){
            System.out.println(String.format("%-20s|Rp %9d,00|%13d|", it.getName(), it.getPrice(), it.getStock()));
        }
    }

    public void jualBarang(String namaBarang, int qty){
        namaBarang = namaBarang.toLowerCase();
        if(getBarang(namaBarang) == null){
            System.out.println("barangnya ndak ada ygy");
            return;
        }
        barang Barang = getBarang(namaBarang);
        if(Barang.getStock() <= 0){
            System.out.println("Barangnya habis ygy");
            return;
        }
        Barang.modifyProperties(namaBarang, Barang.getPrice(), Barang.getStock()-qty);

        int hargaTotal = Barang.getPrice() * qty;
        uangKas += hargaTotal*pajak+hargaTotal;
    }

    public void restokBarang(String namaBarang, int qty){
        namaBarang = namaBarang.toLowerCase();
        if(getBarang(namaBarang) == null){
            System.out.println("barangnya ndak ada ygy");
        }
        barang Barang = getBarang(namaBarang);
        Barang.modifyProperties(namaBarang, Barang.getPrice(), Barang.getStock()+qty);

        int hargaTotal = Barang.getBuyPrice() * qty;
        uangKas -= hargaTotal*pajak+hargaTotal;
    }

    public void barangBaru(String namaBarang, int hargaBeli, int qty){
        barang Barang = new barang(namaBarang, hargaBeli, qty);
        rak.add(Barang);

        int hargaTotal = Barang.getBuyPrice() * qty;
        uangKas -= hargaTotal*pajak+hargaTotal;
    }

    public void initialize(){
        toko.instance().barangBaru("Indomie", 3500, 100);
        toko.instance().barangBaru("Sarimie", 3500, 100);
        toko.instance().barangBaru("Mie Sedaap", 3500, 100);
        toko.instance().barangBaru("Supermi", 3500, 100);
        toko.instance().barangBaru("Mie Sukses", 3500, 100);
        toko.instance().barangBaru("Mie Gepeng", 3500, 100);
        toko.instance().barangBaru("Gaga 100", 3500, 100);
        toko.instance().barangBaru("Mi ABC", 3500, 100);
    }
}

class execute{
    public static void clearScreen(){
        System.out.flush();
        try{
            String operatingSystem = System.getProperty("os.name"); //Check the current operating system
            
            if(operatingSystem.contains("Windows")){        
                ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } else {
                ProcessBuilder pb = new ProcessBuilder("clear");
                Process startProcess = pb.inheritIO().start();
                startProcess.waitFor();
            } 
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void promptEnterKey(){
        System.out.print("\u001b[2K");
        System.out.println("Press \"ENTER\" to continue...");
        input.scanner = new Scanner(System.in); //reinitialize scanner buat bersihin buffer
        input.scanner.nextLine();
    }
}

public class PPBO_04_L0122114{
    public static void main(String[] args){
        toko.instance().initialize();
        boolean buka = true;
        assert asserts.Enabled = true;
        while(buka){
            execute.clearScreen();
            System.out.println("Daftar Barang");
            toko.instance().displayBarang();
            System.out.println("Uang kas : Rp."+ toko.instance().getKas() + ",00");
            System.out.println("\n[1] Jual barang\n[2] Restok barang\n[3] Udh");
            int choice = 0;
            boolean validChoice = false;
            while(!validChoice){
                validChoice = true;
                try{
                    System.out.print("\rKm mw ngapain : "); choice = input.scanner.nextInt();
                    if(asserts.Enabled == false && (choice<1 || choice>3)){
                        throw new Exception("Integer harus diantara 1-3 (exception)");
                    }
                    assert (choice>=1 && choice<=3) : "Integer harus diantara 1-3 (assertion)";
                } catch(java.util.InputMismatchException e){
                    System.out.print("\u001b[2K"); // Clear the line
                    System.out.print(e.toString() + " : Cuma nerima input berupa integer");
                    //System.out.print("\rCuma nerima input berupa integer");
                    validChoice = false;
                } catch(AssertionError | Exception it){
                    System.out.print("\u001b[2K"); // Clear the line
                    System.out.print('\r' + it.toString());
                    validChoice = false;
                } finally{
                    System.out.print("\u001b[1A"); // Move up one line
                    System.out.print("\u001b[2K"); // Clear the line
                    System.out.print("");
                }
                input.scanner = new Scanner(System.in);
            }
            switch(choice){
                case 1:
                {
                    ArrayList<barang> keranjang = new ArrayList<>();
                    while(true){
                        execute.clearScreen();
                        System.out.println("Daftar Barang");
                        toko.instance().displayBarang();

                        System.out.println("\nIsi keranjang");
                        System.out.println(String.format("%-20s|%-15s|%-13s|", "Nama barang", "Harga barang", "jumlah barang"));
                        for(barang it : keranjang){
                            System.out.println(String.format("%-20s|Rp %9d,00|%13d|", it.getName(), it.getPrice(), it.getStock()));
                        }

                        System.out.print("\nApa yang mau dijual (masukkan kode \"udh\" kalo udh): "); String namaBarang = input.scanner.nextLine();
                        if(namaBarang.equals("udh")){
                            break;
                        }

                        barang it = null;
                        if(toko.instance().getBarang(namaBarang) == null){
                            System.out.println("Barangnya gak ada ygy");
                            execute.promptEnterKey();
                            continue;
                        }else{
                            it = new barang(namaBarang, toko.instance().getBarang(namaBarang).getBuyPrice(), 0);
                            boolean match = false;
                            for(barang item_keranjang : keranjang){
                                if(item_keranjang.getName().toLowerCase().equals(namaBarang)){
                                    it = item_keranjang;
                                    match = true;
                                    break;
                                }
                            }
                            if(!match){
                                keranjang.add(it);
                            }
                        }

                        int qty = 0;
                        boolean qtyValid = false;
                        while(qtyValid == false){
                            qtyValid = true;
                            try{
                                System.out.print("\rBerapa jumlahnya (ketik '0' kalo gajadi): "); qty = input.scanner.nextInt();
                                if(!asserts.Enabled && it.getStock()+qty > toko.instance().getBarang(namaBarang).getStock()){
                                    throw new IllegalArgumentException("Stoknya gak cukup ygy");
                                }
                                assert it.getStock()+qty <= toko.instance().getBarang(namaBarang).getStock() : "Stoknya gak cokup ygy";
                            }catch(java.util.InputMismatchException ex){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print("\rCuma nerima input berbentuk integer");
                                qtyValid = false;
                                input.scanner.nextLine();
                            }catch(AssertionError | java.lang.IllegalArgumentException a){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print('\r' + a.getMessage());
                                qtyValid = false;
                            }finally{
                                System.out.print("\u001b[1A"); // Move up one line
                                System.out.print("\u001b[2K"); // Clear the line
                            }
                        }
                        it.modifyProperties(toko.instance().getBarang(namaBarang).getName(), it.getPrice(), it.getStock()+qty);
                        input.scanner = new Scanner(System.in);
                    }

                    if(keranjang.size() != 0){
                        execute.clearScreen();
                        System.out.println("TOTAL PEMBELIAN");
                        int totalHarga = 0, duit = 0;
                        System.out.println(String.format("%-20s|%-15s|%-15s|%-15s|", "Nama barang", "Harga satuan", "jumlah barang", "harga total"));
                        for(barang it : keranjang){
                            int harga = (int)(it.getPrice() * toko.instance().getPajak() + it.getPrice()) * it.getStock();
                            System.out.println(String.format("%-20s|Rp %9d,00|%15d|Rp %9d,00|", it.getName(), it.getPrice(), it.getStock(), harga));
                            totalHarga += harga;
                        }
                        System.out.println("Total belanjaan : Rp." + totalHarga + ",00 (Sudah termasuk PPN)");

                        boolean duitValid = false;
                        while(duitValid == false){
                            duitValid = true;
                            try{
                                System.out.print("\rBerapa duitnya : "); duit = input.scanner.nextInt();
                                if(!asserts.Enabled && duit < totalHarga){
                                    throw new IllegalArgumentException("Duitnya gak cukup");
                                }
                                assert duit>=totalHarga : "Duitnya gak cukup";
                            }catch(java.util.InputMismatchException ex){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print("\rCuma nerima input berbentuk integer");
                                duitValid = false;
                                input.scanner.nextLine();
                            }catch(AssertionError | java.lang.IllegalArgumentException a){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print('\r' + a.getMessage());
                                duitValid = false;
                            }finally{
                                System.out.print("\u001b[1A"); // Move up one line
                                System.out.print("\u001b[2K"); // Clear the line
                            }
                        }

                        toko.instance().incrementTransaksi();
                        System.out.println("Kembaliannya Rp." + (duit-totalHarga) + ",00");
                        for(barang it : keranjang){
                            toko.instance().jualBarang(it.getName(), it.getStock());
                        }
                    }
                    break;
                }
                case 2:
                {
                    execute.clearScreen();
                    String namaBarang;
                    System.out.println("Daftar Barang");
                    toko.instance().displayBarang();
                    System.out.println("Duit kas : Rp."+toko.instance().getKas()+",00");
                    System.out.print("\nApa yang mau direstok: "); namaBarang = input.scanner.nextLine();
                    if(toko.instance().getBarang(namaBarang) != null){
                        int qty = 0;
                        float hargatotal = 0;
                        boolean qtyValid = false;
                        while(qtyValid == false){
                            qtyValid = true;
                            try{
                                System.out.print("\rBerapa jumlahnya: "); qty = input.scanner.nextInt();
                                hargatotal = (toko.instance().getBarang(namaBarang).getBuyPrice() * toko.instance().getPajak() + toko.instance().getBarang(namaBarang).getBuyPrice()) * qty;
                                if(hargatotal > toko.instance().getKas() && !asserts.Enabled){
                                    throw new IllegalArgumentException("duit kas gak cukup");
                                }
                                assert hargatotal <= toko.instance().getKas() : "duit kas gak cukup";
                            }catch(java.util.InputMismatchException e){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print("\rCuma nerima input berbentuk integer");
                                qtyValid = false;
                            }catch(java.lang.IllegalArgumentException | AssertionError e){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print(e.getMessage());
                                qtyValid = false;
                            }finally{
                                System.out.print("\u001b[1A"); // Move up one line
                                System.out.print("\u001b[2K"); // Clear the line
                                input.scanner = new Scanner(System.in);
                            }
                        }
                        System.out.println("Habisnya segini ygy : Rp." + (int)hargatotal + ",00");
                        toko.instance().restokBarang(namaBarang, qty);
                    }else{
                        boolean invalidInput = true;
                        int hargaBarang = 0, stokBarang = 0;

                        //harga barang
                        while(invalidInput == true){
                            invalidInput = false;
                            try{
                                System.out.print("Berapa harga belinya : "); hargaBarang = input.scanner.nextInt(); input.scanner = new Scanner(System.in);
                            }catch(java.util.InputMismatchException ex){
                                System.out.println("Inputnya gk valid");
                                input.scanner.nextLine();
                                invalidInput = true;
                            }
                        }

                        invalidInput = true;
                        float hargaTotal = 0;

                        //qty
                        while(invalidInput){
                            invalidInput = false;
                             try{
                                System.out.print("Berapa jumlahnya: "); stokBarang = input.scanner.nextInt();
                                hargaTotal = (hargaBarang * toko.instance().getPajak() + hargaBarang) * stokBarang;
                                assert hargaTotal <= toko.instance().getKas() : "AssertionError : Duit kas gak cukup";
                                if(hargaTotal > toko.instance().getKas()){
                                    if(!asserts.Enabled) throw new IllegalArgumentException("Exception : duit kas gak cukup");
                                }
                            }catch(java.util.InputMismatchException e){
                                System.out.println("Inputnya gk valid");
                                invalidInput = false;
                                input.scanner.nextLine();
                            }catch(java.lang.IllegalArgumentException | AssertionError e){
                                System.out.println(e.getMessage());
                                invalidInput = false;
                                input.scanner.nextLine();
                            }
                        }
                        System.out.println("Habisnya segini : " + hargaTotal);
                        toko.instance().barangBaru(namaBarang, hargaBarang, stokBarang);
                    }
                    break;
                }
                case 3: 
                {
                    buka = false;
                    break;
                }
            }
            execute.promptEnterKey();
        }
        System.out.println("Total transaksi hari ini : " + toko.instance().getTotalTransaksi());
        System.out.println("Uang kas : Rp." + toko.instance().getKas() + ",00");
        input.scanner.close();
    }
}