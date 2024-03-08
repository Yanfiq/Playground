#include <iostream>
#include <vector>
#include <stack>
#include <queue>

int main(){
    std::queue<int> fav_numbers;
    fav_numbers.push(1);
    fav_numbers.push(2);
    fav_numbers.push(3);

    while(!fav_numbers.empty()){
        std::cout << fav_numbers.front() << std::endl;
        fav_numbers.pop();
    }

    return 0;
}