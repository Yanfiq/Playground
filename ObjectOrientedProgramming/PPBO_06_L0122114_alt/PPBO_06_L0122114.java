/* Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

package PPBO_06_L0122114_alt;

import java.util.ArrayList;
import java.util.Scanner;

class scanner{
    public static Scanner scanner = new Scanner(System.in);
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

class Laptop {
    //properties
    private String brand;
    private String model;
    private int year;
    private static ArrayList<Laptop> Laptops = new ArrayList<>();

    //constructor
    public Laptop(String brands, String model, int year){
        //dalam konstruksi objek, referencenya dimasukkan ke arraylist untuk disimpan
        this.brand = brands;
        this.model = model;
        this.year = year;
        Laptops.add(this);
    }

    //methods
    public String getBrand(){return this.brand;}
    public String getModel(){return this.model;}
    public int getYear(){return this.year;}

    //static method
    public static Laptop getObject(int index){
        return (index >= 0 && index < Laptops.size()) ? Laptops.get(index) : null;
    }

    public static void delObject(int num){
        //kalo indexnya diluar jangkauan, langsung ke early return
        if(num < 1 || num > Laptops.size()) return;
        Laptops.remove(num-1);
    }

    public static int getObjectCount(){
        return Laptops.size();
    }

    public static void printAllData(){
        System.out.println(String.format("|%-3s|%-10s|%-10s|%-5s|", "No.", "Brand", "Model", "Tahun"));
        for(int i=0; i<Laptops.size(); i++){
            Laptop it = Laptops.get(i);
            System.out.println(String.format("|%-3s|%-10s|%-10s|%-5d|", i+1, it.getBrand(), it.getModel(), it.getYear()));
        }
    }
}

public class PPBO_06_L0122114{
    public static void main(String[] args){
        boolean wishlisting = true;
        while(wishlisting){
            execute.clearScreen();
            System.out.println("PROGRAM WISHLIST LAPTOP");
            Laptop.printAllData();
            System.out.print("\n[1] Tambah data\n[2] Hapus data\nChoice : ");
            int choice = 0;

            //blok try...catch buat mengatasi java.util.InputMismatchException
            try {
                choice = scanner.scanner.nextInt();
            } catch (Exception e) {
                System.out.println(e.toString());
                execute.promptEnterKey();
                continue;
            }
            scanner.scanner = new Scanner(System.in);
            execute.clearScreen();
            System.out.println("PROGRAM WISHLIST LAPTOP");
            Laptop.printAllData();
            System.out.println("");
            switch(choice){
                case 1:
                {
                    //pilihan 1
                    System.out.println("Laptop #"+(Laptop.getObjectCount()+1));
                    System.out.print("Brand : "); String Brand = scanner.scanner.nextLine();
                    System.out.print("Model : "); String Model = scanner.scanner.nextLine();
                    System.out.print("Tahun : "); int Tahun = scanner.scanner.nextInt();

                    //setelah data dimasukkan, data dipake untuk bikin objek
                    Laptop laptop = new Laptop(Brand, Model, Tahun);
                    break;
                }
                case 2:
                {
                    //pilihan 2, untuk ngehapus wishlist
                    System.out.print("Data nomor berapa yg mw dihapus : ");
                    int num = scanner.scanner.nextInt();
                    Laptop.delObject(num);
                    break;
                }
                default:
                {
                    System.out.println("Pilihan tidak valid");
                    break;
                }
            }
            execute.promptEnterKey();
        }
        scanner.scanner.close();
    }
}