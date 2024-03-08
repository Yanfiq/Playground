/*
 * Nama : Muhammad Ryan Fikri Fakhrezi
 * Kelas: C
 * NIM  : L0122114
 */

package PPBO_10_L0122114;

class Karyawan{
    private float gajiPokok;
    private float gajiDiterima;

    float getGajiTerima(){
        return gajiDiterima;
    }
    float getGajiPokok(){
        return gajiPokok;
    }
    void setGajiPokok(float gajiPokok){
        this.gajiPokok = gajiPokok;
    }
    void setGajiTerima(float gajiDiterima){
        this.gajiDiterima = gajiDiterima;
    }
}

class KaryawanGajiTetap extends Karyawan {
    KaryawanGajiTetap(float gajiPokok){
        super.setGajiPokok(gajiPokok);
        super.setGajiTerima(gajiPokok);
    }
    @Override
    void setGajiTerima(float gajiPokok){
        super.setGajiPokok(gajiPokok);
        super.setGajiTerima(gajiPokok);
    }
    void setGajiPokok(float gajiPokok){
        super.setGajiPokok(gajiPokok);
        super.setGajiTerima(gajiPokok);
    }
}

class KaryawanPerJam extends Karyawan {
    KaryawanPerJam(float gajiPokok){
        super.setGajiPokok(gajiPokok);
    }
    @Override
    void setGajiTerima(float jamKerja){
        super.setGajiTerima(super.getGajiPokok() * jamKerja);
    }
}

class KaryawanKomisi extends Karyawan {
    KaryawanKomisi(){
        super.setGajiPokok(0);
    }

    void setGajiTerima(float totalPenjualan, float persentase){
        super.setGajiTerima(totalPenjualan*persentase);
    }
    void setGajiTerima(float gaji){
        super.setGajiTerima(gaji);
    }
}

class KaryawanKomisiPlus extends KaryawanKomisi {
    KaryawanKomisiPlus(float gajiPokok){
        super.setGajiPokok(gajiPokok);
    }
    @Override
    void setGajiTerima(float totalPenjualan, float persentase){
        super.setGajiTerima(totalPenjualan*persentase + super.getGajiPokok());
    }
    void setGajiTerima(){
        super.setGajiTerima(super.getGajiPokok());
    }
}

public class PPBO_10_L0122114 {
    public static void main(String[] args){
        KaryawanGajiTetap a = new KaryawanGajiTetap(5000000);
        KaryawanPerJam b = new KaryawanPerJam(500000); b.setGajiTerima(8);
        KaryawanKomisi c = new KaryawanKomisi(); c.setGajiTerima(2000000, (30.0f/100.0f));
        KaryawanKomisiPlus d = new KaryawanKomisiPlus(2000000); d.setGajiTerima(2000000, (30.0f/100.0f));
        System.out.println("Gaji masing-masing karyawan");
        System.out.println("Karyawan a : "+a.getGajiTerima());
        System.out.println("Karyawan b : "+b.getGajiTerima());
        System.out.println("Karyawan c : "+c.getGajiTerima());
        System.out.println("Karyawan d : "+d.getGajiTerima());

        System.out.println("Karyawan yang mendapat komisi gajinya ditambah 10%");
        c.setGajiTerima(c.getGajiTerima()*(110.0f/100.0f));
        d.setGajiTerima(d.getGajiTerima()*(110.0f/100.0f));
        System.out.println("Karyawan c : "+c.getGajiTerima());
        System.out.println("Karyawan d : "+d.getGajiTerima());
    }
}

/*
 * polimorphism dapat mempermudah perhitungan gaji karena adanya method overriding dn overloading, 
 * disini dalam KaryawanKomisi, terdapat 2 skenario gaji: dengan totalPenjualan & persentase dan dengan memasukkan gaji secara langsung
 * method pertama digunakan ketika kondisi normal tidak ada kenaikan gaji
 * method kedua digunakan ketika karyawanKomisi mendapat penghargaan berupa penambahan gaji sebanyak 10%
 * 
 * program sebenarnya tetap dapat berjalan tanpa polimorphism dengan cara membuat nama fungsi yang berbeda untuk setiap kondisi.
 * namun hal ini akan meningkatkan redundansi dan mengurangi readability kode.
 */