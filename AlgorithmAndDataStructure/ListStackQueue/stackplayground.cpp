#include <iostream>
#include <stack>
#include <string>

// Mendeklarasikan stack dengan nama history
std::stack<std::string> history;

// Mendeklarasikan fungsi visit dengan parameter string
void visit(std::string website)
{
    // Memasukkan string dalam stack history dengan operasi push
    history.push(website);
    std::cout << "Mengunjungi " << website << std::endl;
}

void back()
{
    // Jika stack tidak kosong maka perintah di dalam fungsi if akan dijalankan
    if (history.size() != 0)
    {
        // Menghapus string yang terakhir masuk dalam stack history dengan operasi pop
        history.pop();
        std::string current_page = history.top();
        std::cout << "Mengunjungi " << current_page << std::endl;
    }
    // Jika stack kosong maka else akan dijalankan
    else
    {
        std::cout << "Tidak ada halaman sebelumnya" << std::endl;
    }
}

int main()
{
    visit("google.com");
    visit("instagram.com");
    visit("github.com");
    back();
    back();
    visit("stackoverflow.com");

    return 0;
}