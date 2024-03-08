#include <list>
#include <iterator>
#include <iostream>

int main(){
    std::list<int> fav_numbers;

    fav_numbers.push_back(1);
    fav_numbers.push_back(2);
    fav_numbers.push_back(3);

    while(fav_numbers.size()){
        std::cout << *std::next(fav_numbers.begin(), 0) << std::endl;
        fav_numbers.pop_front();
    }
}