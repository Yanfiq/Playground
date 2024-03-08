#include <iostream>

//deklarasi single linked list
struct buah
{
    std::string nama;

    buah *next;
};

buah *head, *tail, *cur, *newNode, *del;

void create(std::string namaBuah)
{
    head = new buah();
    head->nama = namaBuah;
    head->next = NULL;
    tail = head;
}

void addFirst(std::string namaBuah)
{
    newNode = new buah();
    newNode->nama = namaBuah;
    newNode->next = head;
    head = newNode;
}

void addLast(std::string namaBuah)
{
    newNode = new buah();
    newNode->nama = namaBuah;
    newNode->next = NULL;
    tail->next = newNode;
    tail = newNode;
}

void delFirst()
{
    del = head;
    head = head->next;
    delete del;
}

void delLast()
{
    del = tail;
    cur = head;
    while(cur->next!=tail)
    {
        cur = cur->next;
    }
    tail = cur;
    tail->next = NULL;
    delete del;
}

void print()
{
    cur = head;
    while(cur!=NULL)
    {
        std::cout << cur->nama <<std::endl;
        cur = cur->next;
    }
}

int main()
{
    create("Rambutan(ini pertama)");
    print();

    std::cout << "\n" << std::endl;

    addFirst("Anggur(harusnya di head)");
    print();

    std::cout << "\n" << std::endl;

    addLast("Pisang(harusnya di tail)");
    print();

    std::cout << "\nyang dibawah itu awalnya udh dihapus" << std::endl;

    delFirst();
    print();

    std::cout << "\nyang dibawah itu akhirnya udh dihapus" << std::endl;

    delLast();
    print();
}