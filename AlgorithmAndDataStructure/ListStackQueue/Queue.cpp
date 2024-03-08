/*
Nama : Muhammad Ryan Fikri Fakhrezi
Kelas : C
NIM : L0122114
*/

#include <iostream>
#include <queue>
#include <stack>

std::queue<std::string> instruction;
std::stack<int> num;

void run(std::queue<std::string> commandQueue, std::stack<int> stackDestination)
{
    while(commandQueue.size()!=0)
    {
        std::string nowRunning = commandQueue.front();
        if(nowRunning == "PUSH 0")
        {
            stackDestination.push(0);
        }
        else if(nowRunning == "PUSH 1")
        {
            stackDestination.push(1);
        }
        else if(nowRunning == "TOP")
        {
            std::cout << stackDestination.top();
        }
        else if(nowRunning == "POP")
        {
            stackDestination.pop();
        }
        commandQueue.pop();
    }
}

int main()
{
    instruction.push("PUSH 0");
    instruction.push("PUSH 0");
    instruction.push("TOP");
    instruction.push("PUSH 1");
    instruction.push("TOP");
    instruction.push("POP");
    instruction.push("POP");
    instruction.push("TOP");
    run(instruction, num);
}