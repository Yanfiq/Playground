/*
 * Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

package PPBO_09_L0122114;

class mahasiswa{
    private String nama;
    private String nim;
    private int semester;

    mahasiswa(String nama, String nim, int semester){
        this.nama = nama;
        this.nim = nim;
        this.semester = semester;
    }

    public void setNama(String nama){
        this.nama = nama;
    }
    public void setNim(String nim){
        this.nim = nim;
    }
    public void setSemester(int semester){
        this.semester = semester;
    }

    public String getNama(){return this.nama;}
    public String getNim(){return this.nim;}
    public int getSemester(){return this.semester;}

    public void tampilkanInfo(){
        System.out.println("\nNama\t: "+this.nama);
        System.out.println("NIM\t: "+this.nim);
        System.out.println("Semester: "+this.semester);
    }
}

class asisten extends mahasiswa {
    private String matkul;
    asisten(String nama, String nim, int semester, String matkul){
        super(nama, nim, semester);
        this.matkul = matkul;
    }
    public void setMatkul(String matkul){
        this.matkul = matkul;
    }
    public String getMatkul(){
        return this.matkul;
    }
    @Override
    public void tampilkanInfo(){
        System.out.println("\nNama\t: "+this.getNama());
        System.out.println("NIM\t: "+this.getNim());
        System.out.println("Semester: "+this.getSemester());
        System.out.println("Matkul\t: "+this.matkul);
    }
}

public class PPBO_09_L0122114{
    public static void main(String[] args){
        mahasiswa mhs = new mahasiswa("Muhammad Ryan Fikri Fakhrezi", "L0122114", 3);
        asisten asprak1 = new asisten("Alfiki Diastama Afan Firdaus", "M0521009", 5, "P.OOP");
        asisten asprak2 = new asisten("Hezkiel Bram Setiawan", "M0521030", 5, "P.OOP");
        asisten asprak3 = new asisten("Muhammad Anang Fathur Rohman", "M0521047", 5, "P.OOP");

        asprak1.setMatkul("P.PBO");
        asprak2.setMatkul("P.PBO");
        asprak3.setMatkul("P.PBO");

        mhs.tampilkanInfo();
        asprak1.tampilkanInfo();
        asprak2.tampilkanInfo();
        asprak3.tampilkanInfo();
    }
}