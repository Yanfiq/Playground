#include <iostream>
using namespace std;

int waktuAwal = 0;
int waktuAkhir;
int jarak;
int kecepatanKonstan = 50;

int glbb(int waktuAwal, int waktuN)
{
    if (waktuAwal != waktuN)
    {
        return kecepatanKonstan + glbb(waktuAwal + 1, waktuN);
    }
    else
    {
        return 0;
        cout << "END" << endl;
    }
}

int main()
{
    cout << "==================== KONDISI AKHIR ====================" << endl;
    cout << "Masukkan waktu yang ingin ditempuh (jam): ";
    cin >> waktuAkhir;

    cout << "Kecepatan Mobil       : " << kecepatanKonstan << " KM/JAM" << endl;
    cout << "Lama waktu berkendara : " << waktuAkhir << " jam" << endl;
    cout << "Jarak tempuh total    : " << endl;
    for(int i=0; i<=waktuAkhir; i++)
    {
        cout << "Waktu ke-" << i << "-->";
        cout << glbb(waktuAwal, i) << endl;
    }
}