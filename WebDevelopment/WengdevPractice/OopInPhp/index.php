<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>OOP</title>
</head>
<body>
    <?php
        class Manusia{
            private $Nama;
            private $TanggalLahir;
            private $Alamat;

            function __construct($Nama, $TanggalLahir, $Alamat) {
                $this->Nama = $Nama;
                $this->TanggalLahir = $TanggalLahir;
                $this->Alamat = $Alamat;
            }
            function get_Nama(){ return $this->Nama;}
            function get_TanggalLahir(){ return $this->TanggalLahir;}
            function get_Alamat(){ return $this->Alamat;}

            function set_Nama($Nama){$this->Nama = $Nama;}
            function set_TanggalLahir($TanggalLahir){$this->TanggalLahir = $TanggalLahir;}
            function set_Alamat($Alamat){$this->Alamat = $Alamat;}
        }
        class Mahasiswa extends Manusia{
            private $NIM;
            private $Prodi;
            private $IPK;
            function __construct($NIM, $Nama, $Prodi, $TanggalLahir, $Alamat, $IPK) {
                parent::__construct($Nama, $TanggalLahir, $Alamat);
                $this->NIM = $NIM;
                $this->Prodi = $Prodi;
                $this->IPK = $IPK;
            }

            function get_NIM(){ return $this->NIM;}
            function get_Prodi(){ return $this->Prodi;}
            function get_IPK(){ return $this->IPK;}

            function set_NIM($NIM){ $this->NIM = $NIM;}
            function set_Prodi($Prodi){ $this->Prodi = $Prodi;}
            function set_IPK($IPK){ $this->IPK = $IPK;}
        }

        class Dosen extends Manusia{
            private $NIP;
            private $Prodi;
            function __construct($NIP, $Nama, $Prodi, $TanggalLahir, $Alamat) {
                parent::__construct($Nama, $TanggalLahir, $Alamat);
                $this->NIP = $NIP;
                $this->Prodi = $Prodi;
            }

            function get_NIP(){ return $this->NIP;}
            function get_Prodi(){ return $this->Prodi;}

            function set_NIP($NIP){ $this->NIP = $NIP;}
            function set_Prodi($Prodi){ $this->Prodi = $Prodi;}
        }

        $maba = new Mahasiswa("M0521981", "Putri Anggraeni", "Informatika", "2003-06-31", "Surakarta", "3.98");
        $doba = new Dosen(198508312012121004, "Adi Nugroho", "Informatika", "1973-09-25", "Karanganyar");

        echo "<div> Mahasiswa : ".$maba->get_NIM()." | ".$maba->get_Nama()." | ".$maba->get_Prodi()." | ".$maba->get_TanggalLahir()." | ".$maba->get_Alamat()." | IPK: ".$maba->get_IPK()."</div>";
        echo "<div> Dosen : ".$doba->get_NIP()." | ".$doba->get_Nama()." | ".$doba->get_Prodi()." | ".$doba->get_TanggalLahir()." | ".$doba->get_Alamat()."</div>";

        echo "<hr/>";

        echo "<div> IPK: " . $maba->get_IPK() . "</div>";
        $doba->set_Nama("Dwi Utomo");
        echo "<div> Nama dosen baru: " . $doba->get_Nama() . "</div>";
    ?>  
</body>
</html>