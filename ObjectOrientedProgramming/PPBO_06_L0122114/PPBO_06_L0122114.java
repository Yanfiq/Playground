/* Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

package PPBO_06_L0122114;

import java.util.HashMap;
import java.util.Scanner;

class scanner{
    public static Scanner scanner = new Scanner(System.in);
}

class inputAlwaysValid{
    public static int number(String label, int min, int max){
        boolean validInput = false; int num = 0;
        while(!validInput){
            validInput = true;
            try{
                System.out.print("\r"+label+" : "); num = scanner.scanner.nextInt();
                if(num<min || num>max){
                    throw new Exception("Integer harus diantara "+min+"-"+max);
                }
            } catch(java.util.InputMismatchException e){
                System.out.print("\u001b[2K"); // Clear the line
                System.out.print(e.toString() + " : Cuma nerima input berupa integer");
                validInput = false;
            } catch(AssertionError | Exception it){
                System.out.print("\u001b[2K"); // Clear the line
                System.out.print('\r' + it.toString());
                validInput = false;
            } finally{
                System.out.print("\u001b[1A"); // Move up one line
                System.out.print("\u001b[2K"); // Clear the line
                scanner.scanner = new Scanner(System.in);
            }
        }
        return num;
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
        scanner.scanner = new Scanner(System.in); //reinitialize scanner buat bersihin buffer
        scanner.scanner.nextLine();
    }
}

public class PPBO_06_L0122114{
    public static void main(String[] args){
        toko.instance().initialize();
        boolean buka = true;
        while(buka){
            execute.clearScreen();
            System.out.println("Daftar Barang");
            toko.instance().displayBarang();
            System.out.println("Uang kas : Rp."+ toko.instance().getKas() + ",00");
            System.out.println("\n[1] Jual barang\n[2] Restok barang\n[3] Udh");
            int choice = inputAlwaysValid.number("Km mw ngapain", 1, 2);
            switch(choice){
                case 1:
                {
                    HashMap<String, barang> keranjang = new HashMap<String, barang>();
                    while(true){
                        execute.clearScreen();
                        System.out.println("Daftar Barang");
                        toko.instance().displayBarang();

                        System.out.println("\nIsi keranjang");
                        System.out.println(String.format("|%-11s|%-20s|%-15s|%-13s|", "Kode Barang", "Nama barang", "Harga barang", "jumlah barang"));
                        for(String kdBarang : keranjang.keySet()){
                            barang it = keranjang.get(kdBarang);
                            System.out.println(String.format("|%-11s|%-20s|Rp %9d,00|%13d|", kdBarang, it.getName(), it.getPrice(), it.getStock()));
                        }

                        System.out.print("\nApa yang mau dijual (masukkan kode \"udh\" kalo udh): "); String KdBarang = scanner.scanner.nextLine();
                        if(KdBarang.equals("udh")){
                            break;
                        }

                        barang it = null;
                        if(toko.instance().getBarang(KdBarang) == null){
                            System.out.println("Barangnya gak ada ygy");
                            execute.promptEnterKey();
                            continue;
                        }else{
                            it = new barang(KdBarang, toko.instance().getBarang(KdBarang).getBuyPrice(), 0);
                            if(keranjang.get(KdBarang) == null){
                                keranjang.put(KdBarang, it);
                            }else{
                                it = keranjang.get(KdBarang);
                            }
                        }

                        int qty = inputAlwaysValid.number("QTY", 0, toko.instance().getBarang(KdBarang).getStock()-it.getStock());
                        it.modifyProperties(toko.instance().getBarang(KdBarang).getName(), it.getPrice(), it.getStock()+qty);
                        scanner.scanner = new Scanner(System.in);
                    }

                    if(keranjang.size() != 0){
                        execute.clearScreen();
                        System.out.println("TOTAL PEMBELIAN");
                        int totalHarga = 0;
                        System.out.println(String.format("%-20s|%-15s|%-15s|%-15s|", "Nama barang", "Harga satuan", "jumlah barang", "harga total"));
                        for(String KdBarang : keranjang.keySet()){
                            barang it = keranjang.get(KdBarang);
                            int harga = (int)(it.getPrice() * toko.instance().getPajak() + it.getPrice()) * it.getStock();
                            System.out.println(String.format("%-20s|Rp %9d,00|%15d|Rp %9d,00|", it.getName(), it.getPrice(), it.getStock(), harga));
                            totalHarga += harga;
                        }
                        System.out.println("Total belanjaan : Rp." + totalHarga + ",00 (Sudah termasuk PPN)");
                        int duit = inputAlwaysValid.number("Berapa duitnya", totalHarga, Integer.MAX_VALUE);
                        toko.instance().incrementTransaksi();
                        System.out.println("Kembaliannya Rp." + (duit-totalHarga) + ",00");
                        for(String KdBarang : keranjang.keySet()){
                            barang it = keranjang.get(KdBarang);
                            toko.instance().jualBarang(KdBarang, it.getStock());
                        }
                    }
                    break;
                }
                case 2:
                {
                    execute.clearScreen();
                    String kdBarang;
                    System.out.println("Daftar Barang");
                    toko.instance().displayBarang();
                    System.out.println("Duit kas : Rp."+toko.instance().getKas()+",00");
                    System.out.print("\nApa yang mau direstok: "); kdBarang = scanner.scanner.nextLine();
                    if(toko.instance().getBarang(kdBarang) != null){
                        int qty = 0;
                        float hargatotal = 0;
                        boolean qtyValid = false;
                        while(qtyValid == false){
                            qtyValid = true;
                            try{
                                System.out.print("\rBerapa jumlahnya: "); qty = scanner.scanner.nextInt();
                                hargatotal = (toko.instance().getBarang(kdBarang).getBuyPrice() * toko.instance().getPajak() + toko.instance().getBarang(kdBarang).getBuyPrice()) * qty;
                                if(hargatotal > toko.instance().getKas()){
                                    throw new IllegalArgumentException("duit kas gak cukup");
                                }
                            }catch(java.util.InputMismatchException e){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print("\rCuma nerima scanner berbentuk integer");
                                qtyValid = false;
                            }catch(java.lang.IllegalArgumentException e){
                                System.out.print("\u001b[2K"); // Clear the line
                                System.out.print(e.getMessage());
                                qtyValid = false;
                            }finally{
                                System.out.print("\u001b[1A"); // Move up one line
                                System.out.print("\u001b[2K"); // Clear the line
                                scanner.scanner = new Scanner(System.in);
                            }
                        }
                        System.out.println("Habisnya segini ygy : Rp." + (int)hargatotal + ",00");
                        toko.instance().restokBarang(kdBarang, qty);
                    }else{
                        boolean invalidInput = true;
                        String namaBarang;
                        int hargaBarang = 0, stokBarang = 0;

                        //nama barang
                        namaBarang = scanner.scanner.nextLine();

                        //harga barang
                        while(invalidInput == true){
                            invalidInput = false;
                            try{
                                System.out.print("Berapa harga belinya : "); hargaBarang = scanner.scanner.nextInt(); scanner.scanner = new Scanner(System.in);
                            }catch(java.util.InputMismatchException ex){
                                System.out.println("Inputnya gk valid");
                                scanner.scanner.nextLine();
                                invalidInput = true;
                            }
                        }

                        invalidInput = true;
                        float hargaTotal = 0;

                        //qty
                        while(invalidInput){
                            invalidInput = false;
                             try{
                                System.out.print("Berapa jumlahnya: "); stokBarang = scanner.scanner.nextInt();
                                hargaTotal = (hargaBarang * toko.instance().getPajak() + hargaBarang) * stokBarang;
                                assert hargaTotal <= toko.instance().getKas() : "AssertionError : Duit kas gak cukup";
                                if(hargaTotal > toko.instance().getKas()){
                                    throw new IllegalArgumentException("Exception : duit kas gak cukup");
                                }
                            }catch(java.util.InputMismatchException e){
                                System.out.println("Inputnya gk valid");
                                invalidInput = false;
                                scanner.scanner.nextLine();
                            }catch(java.lang.IllegalArgumentException | AssertionError e){
                                System.out.println(e.getMessage());
                                invalidInput = false;
                                scanner.scanner.nextLine();
                            }
                        }
                        System.out.println("Habisnya segini : " + hargaTotal);
                        toko.instance().barangBaru(kdBarang, namaBarang, hargaBarang, stokBarang);
                    }
                    break;
                }
                case 3: 
                {
                    buka = false;
                    break;
                }
                default:
                {
                    System.out.println("uh");
                }
            }
            execute.promptEnterKey();
        }
        System.out.println("Total transaksi hari ini : " + toko.instance().getTotalTransaksi());
        System.out.println("Uang kas : Rp." + toko.instance().getKas() + ",00");
        scanner.scanner.close();
    }
}