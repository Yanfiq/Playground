/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C

nb : Belum jadi
*/
#include <iostream>
#include <stack>

std::stack<int> origin;
std::stack<int> transit;
std::stack<int> destination;

void towerOfHanoi(std::stack<int>A, std::string A_Name, std::stack<int>B, std::string B_Name, std::stack<int>C, std::string C_Name)
{
    int move = A.top(); A.pop();
    if(origin.size()==0 && transit.size()==0)
        return;
    if(B.top() > move)
    {
        B.push(move);
        std::cout << "Moving" << move << "from" << A_Name << "to" << B_Name << std::endl;
    }
    else if(C.top() > move)
    {
        C.push(move);
        std::cout << "Moving" << move << "from" << A_Name << "to" << C_Name << std::endl;
    }
    towerOfHanoi(B, B_Name, C, C_Name, A, A_Name);
}

void print(std::stack<int>namaStack)
{
    while(namaStack.size()!=0)
    {
        std::cout << namaStack.top() << std::endl;

        namaStack.pop();
    }
}

int main()
{
    origin.push(5);
    origin.push(3);
    origin.push(1);
    origin.push(2);
    origin.push(4);
    std::string Origin, Destination, Transit;
    towerOfHanoi(origin, "Origin", destination, "Destination", transit, "Transit");
    print(destination);
}