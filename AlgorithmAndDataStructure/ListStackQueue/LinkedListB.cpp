/*
Nama : Muhammad Ryan Fikri Fakhrezi
Kelas : C
NIM : L0122114
*/

#include <iostream>
#include <iterator>
#include <list>

std::list<std::string> buah;
std::list<std::string> list1;
std::list<std::string> list2;

void printList(std::list<std::string>namaList)
{
    for (
        std::list<std::string>::iterator it = namaList.begin();
        it != namaList.end();
        ++it
    ) {
        std::cout << *it << std::endl;
    }
}

void eittBagiDua()
{
    for(int i = 0; i<(buah.size()); i++)
    {
        std::list<std::string>::iterator it = std::next(buah.begin(), i);
        if(i<buah.size()/2)
        {
            list1.insert(list1.end(), *it);
        }
        else if(i>=buah.size()/2)
        {
            list2.insert(list2.end(), *it);
        }
    }
}

int main()
{
    buah.insert(buah.end(), "Rambutan");
    buah.insert(buah.end(), "Jambu");
    buah.insert(buah.end(), "Jeruk");
    buah.insert(buah.end(), "Melon");
    buah.insert(buah.end(), "Semangka");
    buah.insert(buah.end(), "Durian");
    std::cout << "Seluruh list:" << std::endl;
    printList(buah);
    eittBagiDua();
    list1.remove("Jambu");
    list2.remove("Durian");
    std::cout << "List 1:" << std::endl;
    printList(list1);
    std::cout << "List 2:" << std::endl;
    printList(list2);
}