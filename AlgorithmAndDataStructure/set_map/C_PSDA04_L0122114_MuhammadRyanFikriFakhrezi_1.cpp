/*
Nama : Muhammad Ryan Fikri Fakhrezi
NIM : L0122114
Kelas : C
*/

#include <iostream>
#include <set>

// mengecek apakah `n` adalah bilangan prima
// return: `true` jika bilangan prima, `false` jika tidak
bool is_prime(int n) {
    for (int i = 2; i < n; i++) {
        if (n % i == 0) {
            return false;
        }
    }
    return true;
}

int main() {
    std::set<int> prime_numbers;

    // generate bilangan prima dari 1 sampai 100
    // dan masukkan ke dalam himpunan `prime_numbers`
    // tulis code anda di sini
    for(int i=2; i<100; i++) {
        if(is_prime(i)){
            prime_numbers.insert(i);
        }
    }
    // cek bilangan prima
    std::cout << "Send Ctrl+C to exit" << std::endl;
    while (true) {
        int num;
        std::cout << "Enter a number: ";
        std::cin >> num;

        // cek apakah `num` adalah bilangan prima dari 1 dan 100
        // fungsi is_prime() memiliki kompleksitas O(N), adakah versi yang lebih baik?
        // jika ada, perbaiki kondisi dalam if di bawah
        if (prime_numbers.count(num) == 1) {
            std::cout << num << " is a prime number between 1 and 100" << std::endl;
        } else {
            std::cout << num << " is not a prime number between 1 and 100" << std::endl;
        }
    }

    return 0;
}