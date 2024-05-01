#pragma once
#include "DFA.hpp"
#include "NFA.hpp"
#include "utils.cpp"

namespace factory{
    DFA* DFA_Factory(){
        std::map<std::string, state*> states;
        state* startState = nullptr;
        std::string stringStates, delimiter = " ";
        std::cout << "Enter the names of all states separated by spaces \nnb:\n- Write in the format *StateName for the final state\n- Write in the format >StateName for start state\n- Write in the format >*State name if the start state is also the final state" << std::endl;
        std::getline(std::cin, stringStates);
        std::vector<std::string> splittedStringStates = utils::splitString(stringStates, " ");
        
        //string yg ada 
        for(const auto& stateName : splittedStringStates) {
            state* State = nullptr;

            if(stateName.substr(0, 1) == "*"){
                State = new state(stateName.substr(1), true);
            }else if(stateName.substr(0, 1) == ">"){
                if(stateName.substr(1, 1) == "*"){
                    State = new state(stateName.substr(2), true);
                }else{
                    State = new state(stateName.substr(1), false);
                }
                startState = State;
            }
            else{
                State = new state(stateName.substr(0), false);
            }
            states[State->getValue()] = State;
        }

        std::cout << "enter all possible input separated by space " << std::endl;
        std::string rawPossibleInput; std::getline(std::cin, rawPossibleInput);
        std::vector<std::string> splittedPossibleInput = utils::splitString(rawPossibleInput, " ");

        for(const auto& _state : states){
            for(const auto& input : splittedPossibleInput){
                std::string destinationStateValue;
                do{
                    std::cout << _state.first << "--" << input << "-->"; std::cin >> destinationStateValue;
                    if(states.find(destinationStateValue) == states.end()){
                        std::cout << "There's no such state name" << std::endl;
                    }
                }while(states.find(destinationStateValue) == states.end());
                _state.second->addTransition(input, states[destinationStateValue]);
            }
        }

        DFA* dfa = new DFA(states, startState);
        return dfa;
    }

    NFA* NFA_Factory(){
        std::map<std::string, state*> states;
        state* startState = nullptr;
        std::string stringStates, delimiter = " ";
        std::cout << "Enter the names of all states separated by spaces \nnb:\n- Write in the format *StateName for the final state\n- Write in the format >StateName for start state\n- Write in the format >*State name if the start state is also the final state" << std::endl;
        std::getline(std::cin, stringStates);
        std::vector<std::string> splittedStringStates = utils::splitString(stringStates, " ");
        
        for(const auto& stateName : splittedStringStates) {
            state* State = nullptr;

            if(stateName.substr(0, 1) == "*"){
                State = new state(stateName.substr(1), true);
            }else if(stateName.substr(0, 1) == ">"){
                if(stateName.substr(1, 1) == "*"){
                    State = new state(stateName.substr(2), true);
                }else{
                    State = new state(stateName.substr(1), false);
                }
                startState = State;
            }
            else{
                State = new state(stateName.substr(0), false);
            }
            states[State->getValue()] = State;
        }

        std::cout << "Enter all possible input separated by space " << std::endl;
        std::string rawPossibleInput; std::getline(std::cin, rawPossibleInput);
        std::vector<std::string> splittedPossibleInput = utils::splitString(rawPossibleInput, " ");

        for(const auto& _state : states){
            for(const auto& input : splittedPossibleInput){
                std::string destinationState;
                std::cout << _state.first << "--" << input << "-->"; std::getline(std::cin, destinationState);
                std::vector<std::string> subsetOfState = utils::splitString(destinationState, ",");
                for(const auto& it : subsetOfState){
                    _state.second->addTransition(input, states[it]);
                }
            }
        }

        NFA* nfa = new NFA(states, startState);
        return nfa;
    }
}