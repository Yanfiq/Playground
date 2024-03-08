/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C
*/

package PPBO_02_L0122114;

import java.util.Scanner;

class barang{
    private String namaBarang;
    private int hargaJual;
    private int hargaBeli;
    private int jumlahStok;

    public barang(String namaBarang, int harga, int jumlahStok){
        this.namaBarang = namaBarang;
        this.hargaBeli = harga;
        this.hargaJual = Math.round(harga * toko.getUntung() + harga);
        this.jumlahStok = jumlahStok;
    }
    void modifyProperties(String namaBarang, int harga, int jumlahStok){
        this.namaBarang = namaBarang;
        this.hargaJual = harga;
        this.jumlahStok = jumlahStok;
    }
    String getName(){
        return namaBarang;
    }
    int getBuyPrice(){
        return hargaBeli;
    }
    int getPrice(){
        return hargaJual;
    }
    int getStock(){
        return jumlahStok;
    }
}

class toko{
    private static barang[] rak = new barang[0];
    private static int jumlahBarang = 0;
    private static int totalTransaksi = 0;
    private static int uangKas = 3108000; //modal awal
    private static final float keuntungan = 0.03f;
    private static final float pajak = 0.11f;

    public static barang getBarang(String namaBarang){
        for(barang it : rak){
            if(it != null && namaBarang.toLowerCase().equals(it.getName().toLowerCase())){
                return it;
            }
        }
        return null;
    }

    public static float getPajak(){
        return pajak;
    }
    public static float getUntung(){
        return keuntungan;
    }
    public static int getKas(){
        return uangKas;
    }
    public static int getTotalTransaksi(){
        return totalTransaksi;
    }
    public static void incrementTransaksi(){
        totalTransaksi++;
    }

    public static void displayBarang(){
        System.out.println(String.format("%-20s|%-15s|%-15s", "Nama barang", "Harga barang", "jumlah barang"));
        for(int i = 0; i < jumlahBarang; i++){
            barang it = rak[i];
            System.out.println(String.format("%-20s|Rp %9d,00|%15d", it.getName(), it.getPrice(), it.getStock()));
        }
    }

    public static void jualBarang(String namaBarang, int qty){
        namaBarang = namaBarang.toLowerCase();
        if(getBarang(namaBarang) == null){
            System.out.println("barangnya ndak ada coy");
            return;
        }
        barang Barang = getBarang(namaBarang);
        if(Barang.getStock() <= 0){
            System.out.println("Barangnya habis coy");
            return;
        }
        Barang.modifyProperties(namaBarang, Barang.getPrice(), Barang.getStock()-qty);

        uangKas += (Barang.getPrice() * pajak + Barang.getPrice()) * qty;
    }

    public static void restokBarang(String namaBarang, int qty){
        namaBarang = namaBarang.toLowerCase();
        if(getBarang(namaBarang) == null){
            System.out.println("barangnya ndak ada coy");
        }
        barang Barang = getBarang(namaBarang);
        Barang.modifyProperties(namaBarang, Barang.getPrice(), Barang.getStock()+qty);

        uangKas -= (Barang.getBuyPrice() * pajak + Barang.getBuyPrice())* qty;
    }

    public static void barangBaru(String namaBarang, int hargaBeli, int qty){
        barang[] oldRak = new barang[rak.length];
        System.arraycopy(rak, 0, oldRak, 0, rak.length);
        rak = new barang[oldRak.length+1];
        System.arraycopy(oldRak, 0, rak, 0, oldRak.length);

        barang Barang = new barang(namaBarang, hargaBeli, qty);
        rak[jumlahBarang++] = Barang;

        uangKas -= (Barang.getBuyPrice() * pajak + Barang.getBuyPrice())* qty;
    }

    public static void initialize(){
        toko.barangBaru("Indomie", 3500, 100);
        toko.barangBaru("Sarimie", 3500, 100);
        toko.barangBaru("Mie Sedaap", 3500, 100);
        toko.barangBaru("Supermi", 3500, 100);
        toko.barangBaru("Mie Sukses", 3500, 100);
        toko.barangBaru("Mie Gepeng", 3500, 100);
        toko.barangBaru("Gaga 100", 3500, 100);
        toko.barangBaru("Mi ABC", 3500, 100);
    }
}

class execute{
    public static void clearScreen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush(); 
    }
    public static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
}

public class PPBO_02_L0122114{
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        toko.initialize();
        boolean buka = true;
        while(buka){
            execute.clearScreen();
            System.out.println("Daftar Barang");
            toko.displayBarang();
            System.out.println("Uang kas : Rp."+ toko.getKas() + ",00");
            System.out.print("\n[1] Jual barang\n[2] Restok barang\n[3] Udh\nKm mw ngapain : ");
            int choice = 0;
            try{
                choice = input.nextInt();
                if(choice < 1 || choice > 3){
                    throw new IllegalArgumentException();
                }
            } catch(java.util.InputMismatchException | java.lang.IllegalArgumentException e){
                System.out.println("Inputnya gk valid coy");
                choice = 0;
            }
            input = new Scanner(System.in);
            switch(choice){
                case 1:
                {
                    barang[] keranjang = new barang[1]; int counter = 0;
                    while(true){
                        execute.clearScreen();
                        System.out.println("Daftar Barang");
                        toko.displayBarang();

                        System.out.println("\nIsi keranjang");
                        System.out.println(String.format("%-20s|%-15s|%-15s", "Nama barang", "Harga barang", "jumlah barang"));
                        for(int i = 0; i<counter; i++){
                            barang it = keranjang[i];
                            System.out.println(String.format("%-20s|Rp %9d,00|%15d", it.getName(), it.getPrice(), it.getStock()));
                        }

                        System.out.print("\nApa yang mau dijual (ketik \"udh\" kalo udh): "); String namaBarang = input.nextLine();
                        if(namaBarang.equals("udh")){
                            break;
                        }

                        barang it = keranjang[0];
                        if(toko.getBarang(namaBarang) == null){
                            System.out.println("Barangnya gak ada coy");
                            execute.promptEnterKey();
                            continue;
                        }else{
                            for(int i = 0; i <= counter; i++){
                                it = keranjang[i];
                                if(it == null){
                                    barang[] oldKeranjang = new barang[keranjang.length];
                                    System.arraycopy(keranjang, 0, oldKeranjang, 0, keranjang.length);
                                    keranjang = new barang[oldKeranjang.length+1];
                                    System.arraycopy(oldKeranjang, 0, keranjang, 0, oldKeranjang.length);

                                    it = new barang(namaBarang, toko.getBarang(namaBarang).getPrice(), 0);
                                    keranjang[counter++] = it;
                                    break;
                                }else if(it.getName().toLowerCase().equals(namaBarang)){
                                    break;
                                }
                            }
                        }

                        int qty = 0;
                        boolean qtyValid = false;
                        while(qtyValid == false){
                            qtyValid = true;
                            try{
                                System.out.print("Berapa jumlahnya (ketik '0' kalo gajadi): "); qty = input.nextInt();
                                if(it.getStock()+qty > toko.getBarang(namaBarang).getStock()){
                                    throw new IllegalArgumentException("Stoknya gak cukup coy");
                                }
                            }catch(java.util.InputMismatchException | java.lang.IllegalArgumentException ex){
                                System.out.println((ex.getMessage()==null) ? "Inputnya gk valid" : ex.getMessage());
                                qtyValid = false;
                                input.nextLine();
                            }
                        }
                        it.modifyProperties(toko.getBarang(namaBarang).getName(), it.getPrice(), it.getStock()+qty);
                        if(it.getStock() == 0){
                            keranjang[--counter] = null;
                        }
                        input.nextLine();
                    }
                    execute.clearScreen();
                    System.out.println("TOTAL PEMBELIAN");
                    int totalHarga = 0, duit = 0;
                    System.out.println(String.format("%-20s|%-15s|%-15s", "Nama barang", "Harga barang", "jumlah barang"));
                    for(int i = 0; i < counter; i++){
                        barang it = keranjang[i];
                        System.out.println(String.format("%-20s|Rp %9d,00|%15d", it.getName(), it.getPrice(), it.getStock()));
                        totalHarga += (it.getPrice() * toko.getPajak() + it.getPrice()) * it.getStock();
                    }
                    System.out.println("Total belanjaan : Rp." + totalHarga + ",00");
                    System.out.print("Berapa duitnya : "); duit = input.nextInt();
                    while(duit < totalHarga){
                        System.out.println("Duitnya kurang coy :v");
                        System.out.print("Berapa duitnya : "); duit = input.nextInt();
                    }

                    toko.incrementTransaksi();
                    System.out.println("Kembaliannya Rp." + (duit-totalHarga) + ",00");
                    for(int i = 0; i < counter; i++){
                        barang it = keranjang[i];
                        toko.jualBarang(it.getName(), it.getStock());
                    }
                    break;
                }
                case 2:
                {
                    execute.clearScreen();
                    String namaBarang;
                    System.out.println("Daftar Barang");
                    toko.displayBarang();
                    System.out.println("Duit kas : Rp."+toko.getKas()+",00");
                    System.out.print("\nApa yang mau direstok: "); namaBarang = input.nextLine();
                    if(toko.getBarang(namaBarang) != null){
                        int qty = 0;
                        float hargatotal = 0;
                        boolean qtyValid = false;
                        while(qtyValid == false){
                            qtyValid = true;
                            try{
                                System.out.print("Berapa jumlahnya: "); qty = input.nextInt();
                                hargatotal = (toko.getBarang(namaBarang).getBuyPrice() * toko.getPajak() + toko.getBarang(namaBarang).getBuyPrice()) * qty;
                                if(hargatotal > toko.getKas()){
                                    throw new IllegalArgumentException("duit kas gak cukup");
                                }
                            }catch(java.util.InputMismatchException e){
                                System.out.println("Inputnya gk valid");
                                qtyValid = false;
                                input.nextLine();
                            }catch(java.lang.IllegalArgumentException e){
                                System.out.println("duit kas gak cukup");
                                qtyValid = false;
                                input.nextLine();
                            }
                        }
                        System.out.println("Habisnya segini coy : Rp." + Math.round(hargatotal) + ",00");
                        toko.restokBarang(namaBarang, qty);
                    }else{
                        boolean invalidInput = true;
                        int hargaBarang = 0, stokBarang = 0;

                        //harga barang
                        while(invalidInput == true){
                            invalidInput = false;
                            try{
                                System.out.print("Berapa harga belinya : "); hargaBarang = input.nextInt(); input = new Scanner(System.in);
                            }catch(java.util.InputMismatchException ex){
                                System.out.println("Inputnya gk valid");
                                input.nextLine();
                                invalidInput = true;
                            }
                        }

                        invalidInput = true;
                        float hargaTotal = 0;

                        //qty
                        while(invalidInput){
                            invalidInput = false;
                             try{
                                System.out.print("Berapa jumlahnya: "); stokBarang = input.nextInt();
                                hargaTotal = (hargaBarang * toko.getPajak() + hargaBarang) * stokBarang;
                                if(hargaTotal > toko.getKas()){
                                    throw new IllegalArgumentException("duit kas gak cukup");
                                }
                            }catch(java.util.InputMismatchException e){
                                System.out.println("Inputnya gk valid");
                                invalidInput = false;
                                input.nextLine();
                            }catch(java.lang.IllegalArgumentException e){
                                System.out.println("duit kas gak cukup");
                                invalidInput = false;
                                input.nextLine();
                            }
                        }
                        System.out.println("Habisnya segini : " + hargaTotal);
                        toko.barangBaru(namaBarang, hargaBarang, stokBarang);
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
        System.out.println("Total transaksi hari ini : " + toko.getTotalTransaksi());
        System.out.println("Uang kas : Rp." + toko.getKas() + ",00");
        input.close();
    }
}