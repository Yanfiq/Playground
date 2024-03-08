/* Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

package PPBO_05_L0122114;

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

class inputValidation{
    //return name validation
    public static String nameValidation(String nama){
        String strPat = "(([A-Z]{1,1}[a-z]{1,}\\s){1,}([A-Z]{1,1}[a-z]{1,}))|([A-Z]{1,1}[a-z]{1,})";
        if(!nama.matches(strPat)){
            return "Nama Tidak Valid (ex: Ryan Fikri)";
        }
        return "Nama valid";
    }

    //return the birth date validation message
    public static String BirthDateValidation(String tanggalLahir){
        String strPat = "(0[1-9]|[1-2][0-9]|3[0-1])/(0[1-9]|1[0-2])/([0-9]){4}";
        if(!tanggalLahir.matches(strPat)){
            return "Tanggal Lahir Tidak Valid (ex: 29/04/2004)";
        }

        int tahun = Integer.parseInt(tanggalLahir.substring(6, 10));
        int bulan = Integer.parseInt(tanggalLahir.substring(3, 5));
        int tanggal = Integer.parseInt(tanggalLahir.substring(0, 2));

        boolean isKabisat = false;
        if (tahun % 4 == 0) {
            if (tahun % 100 != 0 || tahun % 400 == 0) {
                isKabisat = true;
            }
        }

        //early return februari tanggal invalid
        if(bulan == 2){
            if(isKabisat && tanggal > 29){
                return "Tanggal Lahir Tidak Valid (ex: 29/04/2004)";
            }else if(!isKabisat && tanggal > 28){
                return "Tanggal Lahir Tidak Valid (ex: 29/04/2004)";
            }
        }
        
        //early return bulan yang tanggalnya cuma mentok 30 (bulan genap <= 7 dan bulan ganjil > 7)
        else if(tanggal > 30 && ((bulan <= 7 && bulan%2==0) || (bulan>7 && bulan%2==1))){
            return "Tanggal Lahir Tidak Valid (ex: 29/04/2004)";
        }

        //finally
        return "Tanggal Lahir Valid";
    }

    //return  the phone number validation message
    public static String phoneNumberValidation(String noTelp){
        String strPat = "([+][0-9]{1,2}|00[0-9]{1,2})([0-9]){10,15}";
        if(!noTelp.matches(strPat)){
            return "Nomor HP Tidak Valid (ex: +6287734341945)";
        }
        return "Nomor HP Valid";
    }

    //return the email validation message
    public static String emailValidation(String email){
        String strPat = "[A-Z|a-z|0-9]{5,50}[@][a-z]{5,10}.[a-z]{1,3}";
        if(!email.matches(strPat)){
            return "Email Tidak Valid (ex: ryanfikri5@gmail.com1)";
        }
        return "Email Valid";
    }
}

class userData{
    private String nama;
    private String tanggalLahir;
    private String noTelp;
    private String email;

    public userData(String nama, String tanggalLahir, String noTelp, String email){
        this.nama = nama;
        this.tanggalLahir = tanggalLahir;
        this.noTelp = noTelp;
        this.email = email;
    }

    public String getName(){return nama;}
    public String getBirthDate(){return tanggalLahir;}
    public String getPhoneNumber(){return noTelp;}
    public String getEmail(){return email;}
}

class database{
    private ArrayList<userData> Database = new ArrayList<>();
    private static database instance = null;
    private database(){}
    
    public static database Instance(){
        if(instance == null){
            instance = new database();
        }
        return instance;
    }

    public void inputData(String nama, String tanggalLahir, String noTelp, String email){
        userData data = new userData(nama, tanggalLahir, noTelp, email);
        Database.add(data);
    }
    public userData getUserData(String nama){
        for(userData data : Database){
            if(data.getName().equals(nama)){
                return data;
            }
        }
        return null;
    }
    public void displayAllData(){
        System.out.println(String.format("|%-30s|%-10s|%-15s|%-30s", "Nama", "Tgl. Lahir", "No. Telp", "E-mail"));
        for(userData data : Database){
            System.out.println(String.format("|%-30s|%-10s|%-15s|%-30s", data.getName(), data.getBirthDate(), data.getPhoneNumber(), data.getEmail()));
        }
    }
}

public class PPBO_05_L0122115 {
    public static void main(String[] args){
        boolean open = true;
        while(open){
            System.out.println("PROGRAM PENGUMPULAN DATA");
            System.out.print("[1] Masukin data\n[2] Lihat data\n[3] Udh\nKm mw ngapain : "); int choice = scanner.scanner.nextInt();

            execute.clearScreen();
            scanner.scanner = new Scanner(System.in);
            switch(choice){
                case 1:
                {
                    String nama, tanggalLahir, noTelp, email, message = "Tidak";
                    System.out.println("============ MASUKKAN DATA ============");
                    while(true){
                        System.out.print("\rNama\t\t: "); nama = scanner.scanner.nextLine();
                        message = inputValidation.nameValidation(nama);
                        System.out.print("\u001b[1A"); // Move up one line
                        System.out.print("\u001b[2K"); // Clear the line
                        if(message.contains("Tidak")){
                            System.out.print(String.format("\r%46s%-25s", "", message));
                        }else{
                            System.out.println(String.format("\r%-16s: %-28s%-25s", "Nama", nama, message));
                            break;
                        }
                    }
                    while(true){
                        System.out.print("\rTanggal Lahir\t: "); tanggalLahir = scanner.scanner.nextLine();
                        message = inputValidation.BirthDateValidation(tanggalLahir);
                        System.out.print("\u001b[1A"); // Move up one line
                        System.out.print("\u001b[2K"); // Clear the line
                        if(message.contains("Tidak")){
                            System.out.print(String.format("\r%46s%-25s", "", message));
                        }else{
                            System.out.println(String.format("\r%-16s: %-28s%-25s", "Tanggal Lahir", tanggalLahir, message));
                            break;
                        }
                    }
                    while(true){
                        System.out.print("\rNo. Telp\t: "); noTelp = scanner.scanner.nextLine();
                        message = inputValidation.phoneNumberValidation(noTelp);
                        System.out.print("\u001b[1A"); // Move up one line
                        System.out.print("\u001b[2K"); // Clear the line
                        if(message.contains("Tidak")){
                            System.out.print(String.format("\r%46s%-25s", "", message));
                        }else{
                            System.out.println(String.format("\r%-16s: %-28s%-25s", "No. Telp", noTelp, message));
                            break;
                        }
                    }
                    while(true){
                        System.out.print("\rE-mail\t\t: "); email = scanner.scanner.nextLine();
                        message = inputValidation.emailValidation(email);
                        System.out.print("\u001b[1A"); // Move up one line
                        System.out.print("\u001b[2K"); // Clear the line
                        if(message.contains("Tidak")){
                            System.out.print(String.format("\r%46s%-25s", "", message));
                        }else{
                            System.out.println(String.format("\r%-16s: %-28s%-25s", "E-mail", email, message));
                            break;
                        }
                    }

                    database.Instance().inputData(nama, tanggalLahir, noTelp, email);
                    execute.promptEnterKey();
                    execute.clearScreen();
                    break;
                }
                case 2:
                {
                    System.out.println("Data siapa yang mau ditampilkan (ketik \"all\" untuk mendapatkan semua data)");
                    System.out.print("input : "); String name = scanner.scanner.nextLine();
                    if(name.equals("all")){
                        database.Instance().displayAllData();
                    }else{
                        userData data = database.Instance().getUserData(name);
                        if(data != null){
                            System.out.println("Nama\t\t: " + name);
                            System.out.println("Tanggal lahir\t: " + data.getBirthDate());
                            System.out.println("No. Telp\t: " + data.getPhoneNumber());
                            System.out.println("E-mail\t\t: " + data.getEmail());
                        }else{
                            System.out.println("Data tidak ditemukan");
                        }
                    }
                    execute.promptEnterKey();
                    execute.clearScreen();
                    break;
                }
                case 3:
                {
                    open = false;
                    break;
                }
            }
        }
    }
}