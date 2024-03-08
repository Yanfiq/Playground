#include <stack>
#include <iostream>

int main(){
    std::stack<int>test;
    test.push(1);
    test.push(2);
    test.push(3);

    std::cout << test.top() << std::endl;
    std::cout << test.top() << std::endl;
    test.pop();
    std::cout << test.top() << std::endl;
}