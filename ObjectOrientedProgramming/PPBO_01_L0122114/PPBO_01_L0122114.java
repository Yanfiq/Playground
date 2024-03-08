/* Nama : Muhammad Ryan Fikri Fakhrezi
 * Kelas: C
 * NIM  : L0122114
 */

package PPBO_01_L0122114;

import java.util.Scanner;
import java.util.ArrayList;

class clear{
    public static void screen(){
        System.out.print("\033[H\033[2J");  
        System.out.flush();  
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
        this.brand = brands;
        this.model = model;
        this.year = year;
        Laptops.add(this);
    }

    //methods
    public void printData(){
        System.out.println("Brand : " + this.brand);
        System.out.println("Model : " + this.model);
        System.out.println("Tahun : " + this.year);
        System.out.println();
    }

    //static method
    public static Laptop getObject(int index){
        return (index >= 0 && index < Laptops.size()) ? Laptops.get(index) : null;
    }

    public static void printAllData(){
        int index = 0;
        while(Laptop.getObject(index) != null){
            System.out.println("laptop #" + (index+1));
            Laptop.getObject(index++).printData();
        }
    }

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
            Laptop laptop = new Laptop(Brand, Model, Tahun);
            clear.screen();
        }
        input.close();
    }
}

public class PPBO_01_L0122114 {
    public static void main(String[] args) {
        System.out.println("PROGRAM WISHLIST LAPTOP");
        System.out.println("semoga kesampaian beli");
        
        Laptop.bulkInput();

        clear.screen();
        
        Laptop.printAllData();
    }
}