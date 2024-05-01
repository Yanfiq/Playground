#pragma once
#include "state.hpp"
#include "utils.cpp"
#include <fstream>
#include <sstream>
#include <map>

class DFA{
    private:
    std::map<std::string, state*> states;
    std::set<std::string> possibleInput;
    state* startState;

    public:
    DFA(std::map<std::string, state*> states, state* startState){
        this->states = states;
        possibleInput = states.begin()->second->getAcceptedInput();
        this->startState = startState;
    }


    //Fungsi ini dipakai buat cari suatu state dari map berdasarkan nama statenya
    state* getState(std::string stateName){
        return (states.find(stateName) != states.end()) ? states[stateName] : nullptr;
    }

    //Fungsi ini dipakai buat tampilin tabel transisi di terminal
    void printTransitionTable(){
        std::cout << "state";
        for(const auto& it : possibleInput){
            std::cout << "\t| " << it;
        }
        std::cout << std::endl;

        for(const auto& _state : states){
            std::cout << _state.second->getValue();
            for(const auto& input : possibleInput){
                std::vector<state*> subsetState = _state.second->getNextState(input);
                std::cout << "\t| " << subsetState[0]->getValue();
            }
            std::cout << std::endl;
        }
    }

    //Fungsi ini dipakai buat generate gambar DFA pakai Graphviz
    void generateIllustration(){
        int result = std::system("dot -V");

        if(result == 0){
            std::ofstream ofs("dfa.dot");
            ofs << "digraph DFA {" << std::endl;
            ofs << "  rankdir=LR;" << std::endl;
            ofs << "  node [shape=circle];" << std::endl;
            for(auto _state : states){
                ofs << "  \""+_state.first+"\" [shape=" + ((_state.second->isFinalState()) ? "doublecircle" : "circle") + "];" << std::endl;
            }
            ofs << "  \"\" [shape=none, label=\"\", width=0, height=0];" << std::endl;
            ofs << "  \"\" -> \""+startState->getValue()+"\" [label=\"start\"];" << std::endl;
            for(auto _state : states){
                for(auto input : possibleInput){
                    ofs << "  \""+_state.first+"\" -> \""+_state.second->getNextState(input)[0]->getValue()+"\" [label=\""+input+"\"];" << std::endl;
                }
            }
            ofs << "}" << std::endl;

            system("dot -Tpng dfa.dot -o dfa.png");
            std::cout << "DFA visualization successfully printed in dfa.png" << std::endl;
        }else{
            std::cout << "Graphviz is not installed.\nInstall from https://graphviz.org/download/" << std::endl;
        }
    }
};