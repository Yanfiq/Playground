/*
 * Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

package PPBO_08_L0122114;

class mahasiswa{
    private String nama;
    private String nim;
    private String fakultas;
    private String prodi;
    private int semester;

    public void setNama(String nama){
        this.nama = nama;
    }
    public void setNim(String nim){
        this.nim = nim;
    }
    public void setFakultas(String fakultas){
        this.fakultas = fakultas;
    }
    public void setProdi(String prodi){
        this.prodi = prodi;
    }
    public void setSemester(int semester){
        this.semester = semester;
    }

    public String getNama(){return this.nama;}
    public String getNim(){return this.nim;}
    public String getFakultas(){return this.fakultas;}
    public String getProdi(){return this.prodi;}
    public int getSemester(){return this.semester;}

    public void tampilkanInfo(){
        System.out.println(this.nama);
        System.out.println(this.nim);
        System.out.println(this.fakultas);
        System.out.println(this.prodi);
        System.out.println(this.semester);
    }
}

public class PPBO_08_L0122114{
    public static void main(String[] args){
        mahasiswa mhs = new mahasiswa();
        mhs.setNama("Muhammad Ryan Fikri Fakhrezi");
        mhs.setNim("L0122114");
        mhs.setFakultas("FATISDA");
        mhs.setProdi("Informatika");
        mhs.setSemester(3);

        mhs.tampilkanInfo();
    }
}