/*
Nama : Muhammad Ryan Fikri Fakhrezi
Kelas : C
NIM : L0122114
*/

#include <stack>
#include <iostream>
#include <iterator>
#include <list>

std::stack<std::string> bookShelf;
std::list<std::string> woodenTable; 

void print(std::list<std::string>namaList)
{
    for (
        std::list<std::string>::iterator it = namaList.begin();
        it != namaList.end();
        ++it
    ) {
        std::cout << *it << std::endl;
    }
}

void print(std::stack<std::string>namaStack)
{
    while(namaStack.size()!=0)
    {
        std::cout << namaStack.top() << std::endl;

        namaStack.pop();
    }
}

void putOnTable()
{
    while(bookShelf.size()!=0)
    {
        std::string pick = bookShelf.top();
        woodenTable.insert(woodenTable.end(), pick);
        bookShelf.pop();
    }
}

void sortTheBooks(int a, int b, int c, int d, int e, int f, int g, int h, int i)
{
    int x[9] = {i, h, g, f, e, d, c, b, a};
    for(int i=0; i<9; i++)
    {   
        std::list<std::string>::iterator pick = std::next(woodenTable.begin(), x[i]-1);
        bookShelf.push(*pick);
    }
}

int main()
{
    bookShelf.push("Database Management System (Beginner Edition)");
    bookShelf.push("Database Management System (Expert Edition)");
    bookShelf.push("Machine Learning");
    bookShelf.push("Data Structure and Algorithm 1st Edition");
    bookShelf.push("Introduction to Machine Learning");
    bookShelf.push("Introduction to OpenCV Library (Python)");
    bookShelf.push("Linear Algebra and Its Applications");
    bookShelf.push("Data Structure and Algorithm 2nd Edition");
    bookShelf.push("Implementation of Linear Algebra");
    putOnTable();
    std::cout << "Before sort" << std::endl;
    print(woodenTable);
    sortTheBooks(4, 3, 1, 5, 7, 6, 2, 9, 8);
    std::cout << "\nAfter sort & sell" << std::endl;
    bookShelf.pop();
    print(bookShelf);
}