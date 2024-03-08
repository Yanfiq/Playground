/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C
*/

#include <iostream>

double f(double x, double y)
{
    return -2*x*y*y;
}

double euler(double x, double y, double h, double n)
{
    if(x >= n*h)
    {
        return(y);
    }
    double y_next = y + h*f(x, y);
    return(euler(x+h, y_next, h, n));
}

int main()
{
    double x_base, y_base, h, b, n_total;
    int option;
    std::cout << "INI ADALAH PROGRAM APPROKSIMASI GRAFIK DENGAN METODE EULER" << std::endl;
    std::cout << "y' = -2xy^2" << std::endl;
    std::cout << "Masukkan nilai dasar X\t: ";                  std::cin >> x_base;
    std::cout << "Masukkan nilai dasar Y\t: ";                  std::cin >> y_base;
    std::cout << "Masukkan nilai batas X\t: ";                  std::cin >> b;
    std::cout << "Grafiknya mau dibagi berapa bagian (n) : ";   std::cin >> n_total;    h = (b-x_base)/n_total;
    std::cout << "1. Iterasi seluruh hasil\n2. Hanya cari n tertentu" << std::endl; 
    while(option<1 || option>2){std::cout<<"Masukkan 1 atau 2 : ";  std::cin>>option;}
    switch(option)
    {
        case 1:
        {
            std::cout << "n  |(x , y)" << std::endl;
            for(double i=0; i<=n_total; i++)
            {
                std::cout<<"n="<<i<<"|("<< x_base+h*i <<" , " << euler(x_base, y_base, h, i) << ")" <<std::endl;
            }
            break;
        }
        case 2:
        {
            double n_asked;
            std::cout<< "n="; std::cin >> n_asked;
            std::cout << "n  |(x , y)" << std::endl;
            std::cout<<"n="<<n_asked<<"|("<< x_base+h*n_asked <<" , " << euler(x_base, y_base, h, n_asked) << ")" <<std::endl;
        }
        system("clear");
    }
}