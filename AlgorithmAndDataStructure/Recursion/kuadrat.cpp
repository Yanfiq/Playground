/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C
*/

#include <iostream>

int pangkat(int angka, int pangkatBerapa)
{
    if(pangkatBerapa==0)
    {
        return 1;
    }
    else
    {
        return angka * pangkat(angka, pangkatBerapa-1);
    }
}

int main()
{
    int num1, num2;
    std::cin >> num1;
    std::cin >> num2;
    std::cout << pangkat(num1, num2) << std::endl;
}