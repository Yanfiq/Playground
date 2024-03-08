/* Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

package PPBO_07_L0122114;

import java.io.File;  // Import the File class
import java.io.FileWriter;
import java.io.IOException;  // Import the IOException class to handle errors
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Calendar;
import java.text.SimpleDateFormat;  
import java.util.Date;  

class export{
    public static void todo(String namefile, ArrayList<activity> daftarTodo){
        try {
            File myObj = new File(namefile + ".txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        }catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        
        try{
            FileWriter myWriter = new FileWriter(namefile + ".txt");
            myWriter.write(String.format("|%-15s|%-15s|\n", "Nama Kegiatan", "Waktu kegiatan"));
            for(activity it : daftarTodo){
                myWriter.write(String.format("|%-15s|%-15s|\n", it.name, it.date));
            }
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occured");
            e.printStackTrace();
        }
    }
}

class activity{
    public String name, date;
    public activity(String name, String date){
        this.name = name;
        this.date = date;
    }
}

class inputValidation{
    //return the date validation message
    public static String DateValidation(String date){
        String strPat = "(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([0-9]){4}";
        if(!date.matches(strPat)){
            return "Tanggal Tidak Valid (ex: 29/04/2004)";
        }

        int tahun = Integer.parseInt(date.substring(6, 10));
        int bulan = Integer.parseInt(date.substring(3, 5));
        int tanggal = Integer.parseInt(date.substring(0, 2));

        boolean isKabisat = false;
        if (tahun % 4 == 0) {
            if (tahun % 100 != 0 || tahun % 400 == 0) {
                isKabisat = true;
            }
        }

        //early return februari tanggal invalid
        if(bulan == 2){
            if(isKabisat && tanggal > 29){
                return "Tanggal Tidak Valid (ex: 29/04/2004)";
            }else if(!isKabisat && tanggal > 28){
                return "Tanggal Tidak Valid (ex: 29/04/2004)";
            }
        }
        
        //early return bulan yang tanggalnya cuma mentok 30 (bulan genap <= 7 dan bulan ganjil > 7)
        else if(tanggal > 30 && ((bulan <= 7 && bulan%2==0) || (bulan>7 && bulan%2==1))){
            return "Tanggal Tidak Valid (ex: 29/04/2004)";
        }

        //finally
        return "Tanggal Valid";
    }
}

public class PPBO_07_L0122114 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<activity> daftarTodo = new ArrayList<>();
        System.out.println("program todo list");
        while(true){
            System.out.print("apa kegiatannya (ketik done kalo udah) : ");String name = scanner.nextLine();
            if(name.equals("done")) break;
            System.out.print("Kapan kegiatannya (DD/MM/YYYY) : ");String date = scanner.nextLine();
            if(inputValidation.DateValidation(date).contains("Tidak")) {System.out.println(inputValidation.DateValidation(date));continue;}
            daftarTodo.add(new activity(name, date));
        }
        Date date = new Date();  
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");  
        String strDate= formatter.format(date);  
        export.todo(strDate, daftarTodo);
        scanner.close();
    }
}