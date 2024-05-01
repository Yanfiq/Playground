#pragma once
#include "state.hpp"
#include <map>
#include <fstream>
#include <sstream>

//fungsi-fungsinya kayak yg di DFA, cuma udah disesuaikan aja.

class NFA{
    private:
    std::map<std::string, state*> states;
    std::set<std::string> possibleInput;
    state* startState;

    public:
    NFA(std::map<std::string, state*> states, state* startState){
        this->states = states;
        possibleInput = states.begin()->second->getAcceptedInput();
        this->startState = startState;
    }

    state* getState(std::string stateName){
        return states.find(stateName) != states.end() ? states[stateName] : nullptr;
    }

    void printTransitionTable(){
        std::cout << "state";
        for(auto it : possibleInput){
            std::cout << "\t| " << it;
        }

        std::cout << std::endl;

        for(auto _state : states){
            std::cout << _state.first;
            for(auto input : possibleInput){
                std::vector<state*> subsetState = _state.second->getNextState(input);
                if(!subsetState.empty()){
                    std::cout << "\t|{";
                    for(auto subsetOfDestination : subsetState){
                        std::cout << subsetOfDestination->getValue() << ",";
                    }
                    std::cout << "\b}";
                }else{
                    std::cout << "\t|e";
                }
            }
            std::cout << std::endl;
        }
    }

    void generateIllustration(){
        int result = std::system("dot -V");

        if(result == 0){
            // https://graphviz.org/documentation/
            std::ofstream ofs("nfa.dot");
            ofs << "digraph NFA {" << std::endl;
            ofs << "  rankdir=LR;" << std::endl;
            ofs << "  node [shape=circle];" << std::endl;
            for(const auto& _state : states){
                ofs << "  \""+_state.first+"\" [shape=" + ((_state.second->isFinalState()) ? "doublecircle" : "circle") + "];" << std::endl;
            }
            ofs << "  \"\" [shape=none, label=\"\", width=0, height=0];" << std::endl;
            ofs << "  \"\" -> \""+startState->getValue()+"\" [label=\"start\"];" << std::endl;
            for(const auto& _state : states){
                for(const auto& input : possibleInput){
                    std::vector<state*> subsetOfState = _state.second->getNextState(input);
                    for(const auto& _stateDest : subsetOfState){
                        ofs << "  \""+_state.first+"\" -> \""+_stateDest->getValue()+"\" [label=\""+input+"\"];" << std::endl;
                    }
                }
            }
            ofs << "}" << std::endl;

            system("dot -Tpng nfa.dot -o nfa.png");
            std::cout << "NFA visualization successfully printed in nfa.png" << std::endl;
        }else{
            std::cout << "Graphviz is not installed.\nInstall from https://graphviz.org/download/" << std::endl;
        }
    }
};