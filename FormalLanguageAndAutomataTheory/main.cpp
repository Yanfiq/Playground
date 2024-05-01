#include <iostream>
#include <vector>
#include <set>
#include "factory.cpp"

int main(int argc, char const *argv[]){
    NFA* dfa = factory::NFA_Factory();
    dfa->printTransitionTable();
    dfa->generateIllustration();
    return 0;
}