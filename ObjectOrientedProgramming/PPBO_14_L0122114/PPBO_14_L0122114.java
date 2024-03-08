/**
 * Nama : Muhammad Ryan Fikri Fakhrezi
 * Kelas: C
 * NIM  : L0122114
 */

package PPBO_14_L0122114;

import java.util.Scanner;
import java.util.ArrayList;

/**
 * Class untuk membersihkan console
 */
class clear {
    /**
     * Method untuk membersihkan console menggunakan ANSI Escape Sequence.
     */
    public static void screen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

/**
 * Kelas untuk merepresentasikan objek Laptop dalam wishlist.
 */
public class PPBO_14_L0122114 {
    private String brand;
    private String model;
    private int year;
    private static ArrayList<PPBO_14_L0122114> Laptops = new ArrayList<>();

    /**
     * Konstruktor untuk objek Laptop.
     * @param brand Merek dari laptop.
     * @param model Model dari laptop.
     * @param year Tahun perilisan laptop.
     */
    public PPBO_14_L0122114(String brand, String model, int year){
        this.brand = brand;
        this.model = model;
        this.year = year;
        Laptops.add(this);
    }

    /**
     * Method untuk mencetak data laptop.
     */
    public void printData(){
        System.out.println("Brand : " + this.brand);
        System.out.println("Model : " + this.model);
        System.out.println("Tahun : " + this.year);
        System.out.println();
    }

    /**
     * Method untuk mendapatkan objek Laptop berdasarkan indeks.
     * @param index Indeks objek Laptop yang ingin diambil.
     * @return Objek Laptop pada indeks yang diberikan.
     */
    public static PPBO_14_L0122114 getObject(int index){
        return (index >= 0 && index < Laptops.size()) ? Laptops.get(index) : null;
    }

    /**
     * Method untuk mencetak seluruh data laptop yang ada.
     */
    public static void printAllData(){
        int index = 0;
        while(PPBO_14_L0122114.getObject(index) != null){
            System.out.println("laptop #" + (index+1));
            PPBO_14_L0122114.getObject(index++).printData();
        }
    }

    /**
     * Method untuk input data beberapa laptop sekaligus.
     */
    public static void bulkInput(){
        Scanner input = new Scanner(System.in);
        System.out.print("Berapa laptop yang mau di wishlist : "); int num = input.nextInt();
        clear.screen();
        for(int i = 0; i < num;){
            input.nextLine();
            System.out.println("Laptop #" + ++i);
            System.out.print("Brand : "); String Brand = input.nextLine();
            System.out.print("Model : "); String Model = input.nextLine();
            System.out.print("Tahun : "); int Tahun = input.nextInt();
            PPBO_14_L0122114 laptop = new PPBO_14_L0122114(Brand, Model, Tahun);
            clear.screen();
        }
        input.close();
    }

    public static void main(String[] args) {
        System.out.println("PROGRAM WISHLIST LAPTOP");
        System.out.println("semoga kesampaian beli");       
        PPBO_14_L0122114.bulkInput();
        clear.screen();
        PPBO_14_L0122114.printAllData();
    }
}