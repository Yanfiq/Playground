#include <iostream>

double f(double x, double y)
{
    return x - 2*y;
}

double euler_rec (double x, double y, double h, double b) 
{
    if(b > x+h)
    {
        y = euler_rec(x ,y ,h , b-h); 
    }
    else
    {
        y = y;
        h = b-x;
    }
    return y + h * f(b-h, y); 
}

int main()
{
    std::cout << euler_rec(0.1, 1, 0.1, 0.5);
}