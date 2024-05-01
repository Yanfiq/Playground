#pragma once
#include <iostream>
#include <vector>
#include <set>

//Class ini merepresentasikan state didalam DFA/NFA. class ini menampung informasi value dari state sama pointer ke state yg ditunjuk sama transition table.
class state{
    private:
    std::string value;
    bool isAccept;
    std::vector<std::pair<std::string, state*>> transitions; //ini pakai std::pair + std::vector karena kalau pake std::map, nanti gabisa buat NFA
    std::set<std::string> acceptedInput;

    public:
    state(std::string value, bool isAccept){
        this->value = value;
        this->isAccept = isAccept;
    }

    void addTransition(std::string input, state* destination){
        auto transition = std::make_pair(input, destination);
        transitions.push_back(transition);
        acceptedInput.insert(input);
    }

    std::vector<state*> getNextState(std::string input) {
        std::vector<state*> subsetState;
        for (const auto& transition : transitions) {
            if (transition.first == input) {
                subsetState.push_back(transition.second);
            }
        }
        return subsetState;
    }

    std::set<std::string> getAcceptedInput(){
        return acceptedInput;
    }

    std::string getValue(){
        return value;
    }

    bool isFinalState(){
        return isAccept;
    }
};