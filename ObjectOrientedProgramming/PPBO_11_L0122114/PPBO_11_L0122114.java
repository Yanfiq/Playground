package PPBO_11_L0122114;

/*
 * Nama : Muhammad Ryan Fikri Fakhrezi
 * NIM  : L0122114
 * Kelas: C
 */

// Interface untuk menghitung nilai
interface HitungNilai {
    double hitungNilai();
}

// Abstract class untuk merepresentasikan sebuah mata kuliah
abstract class MataKuliah implements HitungNilai {
    private String nama;
    private double nilai;
    private int sks;

    public MataKuliah(String nama, double nilai, int sks) {
        this.nama = nama;
        this.nilai = nilai;
        this.sks = sks;
    }

    public double getNilai() {
        return nilai;
    }

    public int getSKS() {
        return sks;
    }
}

// Class Mahasiswa yang menampung informasi mata kuliah
class Mahasiswa {
    private String nama;
    private MataKuliah[] mataKuliah;
    private int totalSKS;

    public Mahasiswa(String nama, MataKuliah[] mataKuliah) {
        this.nama = nama;
        this.mataKuliah = mataKuliah;
        hitungTotalSKS();
    }

    private void hitungTotalSKS() {
        for (MataKuliah mk : mataKuliah) {
            totalSKS += mk.getSKS();
        }
    }

    public double hitungNilaiFinal() {
        double totalNilai = 0.0;
        for (MataKuliah mk : mataKuliah) {
            totalNilai += mk.hitungNilai();
        }
        return totalNilai / totalSKS;
    }

    public void tampilkanInfo() {
        System.out.println("Nama: " + nama);
        System.out.println("Total SKS: " + totalSKS);
        System.out.println("Nilai: " + hitungNilaiFinal());
    }
}

// Implementasi untuk mata kuliah tertentu (contoh)
class NilaiMataKuliah extends MataKuliah {
    public NilaiMataKuliah(String nama, double nilai, int sks) {
        super(nama, nilai, sks);
    }

    @Override
    public double hitungNilai() {
        return getNilai() * getSKS();
    }
}

public class PPBO_11_L0122114 {
    public static void main(String[] args) {
        // Informasi Mata Kuliah untuk Mahasiswa A
        MataKuliah[] mataKuliahMahasiswaA = {
            new NilaiMataKuliah("Kuliah A", 80, 3),
            new NilaiMataKuliah("Kuliah B", 90, 2),
            new NilaiMataKuliah("Kuliah C", 75, 4),
            new NilaiMataKuliah("Kuliah D", 85, 3),
            new NilaiMataKuliah("Kuliah E", 95, 2)
        };

        // Informasi Mata Kuliah untuk Mahasiswa B
        MataKuliah[] mataKuliahMahasiswaB = {
            new NilaiMataKuliah("Kuliah A", 70, 3),
            new NilaiMataKuliah("Kuliah B", 85, 2),
            new NilaiMataKuliah("Kuliah C", 80, 4),
            new NilaiMataKuliah("Kuliah D", 90, 3),
            new NilaiMataKuliah("Kuliah E", 75, 2)
        };

        // Informasi Mata Kuliah untuk Mahasiswa C
        MataKuliah[] mataKuliahMahasiswaC = {
            new NilaiMataKuliah("Kuliah A", 90, 3),
            new NilaiMataKuliah("Kuliah B", 95, 2),
            new NilaiMataKuliah("Kuliah C", 85, 4),
            new NilaiMataKuliah("Kuliah D", 80, 3),
            new NilaiMataKuliah("Kuliah E", 88, 2)
        };

        // Informasi Mata Kuliah untuk Mahasiswa D
        MataKuliah[] mataKuliahMahasiswaD = {
            new NilaiMataKuliah("Kuliah A", 88, 3),
            new NilaiMataKuliah("Kuliah B", 92, 2),
            new NilaiMataKuliah("Kuliah C", 78, 4),
            new NilaiMataKuliah("Kuliah D", 85, 3),
            new NilaiMataKuliah("Kuliah E", 90, 2)
        };

        // Informasi Mata Kuliah untuk Mahasiswa E
        MataKuliah[] mataKuliahMahasiswaE = {
            new NilaiMataKuliah("Kuliah A", 95, 3),
            new NilaiMataKuliah("Kuliah B", 85, 2),
            new NilaiMataKuliah("Kuliah C", 90, 4),
            new NilaiMataKuliah("Kuliah D", 88, 3),
            new NilaiMataKuliah("Kuliah E", 75, 2)
        };

        // Membuat objek Mahasiswa untuk 5 mahasiswa
        Mahasiswa mahasiswaA = new Mahasiswa("Mahasiswa A", mataKuliahMahasiswaA);
        Mahasiswa mahasiswaB = new Mahasiswa("Mahasiswa B", mataKuliahMahasiswaB);
        Mahasiswa mahasiswaC = new Mahasiswa("Mahasiswa C", mataKuliahMahasiswaC);
        Mahasiswa mahasiswaD = new Mahasiswa("Mahasiswa D", mataKuliahMahasiswaD);
        Mahasiswa mahasiswaE = new Mahasiswa("Mahasiswa E", mataKuliahMahasiswaE);

        // Menampilkan informasi untuk setiap mahasiswa
        mahasiswaA.tampilkanInfo();
        mahasiswaB.tampilkanInfo();
        mahasiswaC.tampilkanInfo();
        mahasiswaD.tampilkanInfo();
        mahasiswaE.tampilkanInfo();
    }
}