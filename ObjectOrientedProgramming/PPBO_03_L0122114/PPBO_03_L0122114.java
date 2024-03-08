/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C
*/

package PPBO_03_L0122114;

import java.util.Scanner;

class gudang{
    private static gudang instance = null;
    private String[] buah = new String[0];
    private String[] sayur = new String[0];

    public static gudang Instance(){
        if(instance == null){
            instance = new gudang();
        }
        return instance;
    }

    private void AddBuah(String namaBuah){
        String[] oldBuah = new String[buah.length];
        System.arraycopy(buah, 0, oldBuah, 0, buah.length);
        buah = new String[oldBuah.length + 1];
        System.arraycopy(oldBuah, 0, buah, 0, oldBuah.length);
        
        buah[oldBuah.length] = namaBuah;
    }

    private void AddSayur(String namaSayur){
        String[] oldSayur = new String[sayur.length];
        System.arraycopy(sayur, 0, oldSayur, 0, sayur.length);
        sayur = new String[oldSayur.length + 1];
        System.arraycopy(oldSayur, 0, sayur, 0, oldSayur.length);
        sayur[oldSayur.length] = namaSayur;
    }

    public void CheckInput(String[] item){
        for(String it : item){
            if(it.toLowerCase().contains("buah")){
                AddBuah(it.substring(5));
            }else if(it.toLowerCase().contains("sayur")){
                AddSayur(it.substring(6));
            }
        }
    }

    public void PrintBuah(){
        for(int i = 0; i< buah.length; i++){
            System.out.println((i+1)+ ". " + buah[i]);
        }
    }

    public void PrintSayur(){
        for(int i = 0; i< sayur.length; i++){
            System.out.println((i+1) + ". " + sayur[i]);
        }
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

public class PPBO_03_L0122114 {
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);
        boolean masihKerja = true;
        while(masihKerja){
            System.out.print("[1] Input barang\n[2] Lihat list buah\n[3] Lihat list sayur\n[4] Udh\nKm mw ngapain : ");
            int choice = 0;

            try{
                choice = input.nextInt();
                if(choice < 1 || choice > 4){
                    throw new Exception("Inputnya gk valid");
                }
            } catch(Exception ex){
                System.out.println("Inputnya gk valid");
                execute.promptEnterKey();
            }
            input = new Scanner(System.in);
            switch(choice){
                case 1:
                {
                    int jumlahItem = 0; boolean jumlahValdi = false;
                    System.out.print("Pengen masukin berapa item : ");

                    while(!jumlahValdi){
                        jumlahValdi = true;
                        try{
                            jumlahItem = input.nextInt();
                            input = new Scanner(System.in);
                        } catch(java.util.InputMismatchException e){
                            System.out.println("Inputnya gk valid coy");
                            jumlahItem = 0;
                            jumlahValdi = false;
                        }
                    }

                    String[] itemsStrings = new String[jumlahItem];
                    int counter = 0;
                    while(jumlahItem!=counter){
                        String namaItem = input.nextLine();
                        itemsStrings[counter++] = namaItem;
                    }
                    gudang.Instance().CheckInput(itemsStrings);;
                    break;
                }
                case 2:
                {
                    System.out.println("\nList buah");
                    gudang.Instance().PrintBuah();
                    execute.promptEnterKey();
                    break;
                }
                case 3:
                {
                    System.out.println("\nList sayur");
                    gudang.Instance().PrintSayur();
                    execute.promptEnterKey();
                    break;
                }
                case 4:
                {
                    masihKerja = false;
                    break;
                }
            }
            execute.clearScreen();
        }
        input.close();
    }
}