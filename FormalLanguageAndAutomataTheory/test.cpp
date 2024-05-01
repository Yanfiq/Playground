#include <iostream>
#include <fstream>
#include <sstream>
#include <vector>
#include "utils.cpp"

void generateGraphviz(const std::string& filename) {
    std::ofstream ofs(filename);
    ofs << "digraph DFA {" << std::endl;
    ofs << "  rankdir=LR;" << std::endl;
    ofs << "  node [shape=circle];" << std::endl;
    ofs << "  \"q0\" [shape=doublecircle];" << std::endl; // Example accepting state
    ofs << "  \"q0\" -> \"q1\" [label=\"a\"];" << std::endl; // Example transition
    // Add more transitions as needed
    ofs << "}" << std::endl;
}

int main() {
    generateGraphviz("dfa.dot");
    system("dot -Tpng dfa.dot -o dfa.png");
    return 0;
}