#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <cstring>
#include <vector>
#include <list>
#include <stack>
#include <queue>
#include <ios>
#include <limits>
#include <map>
#include "rapidxml/rapidxml.hpp"

struct similar{
    public:
        std::string book_a;
        std::string book_b;
        std::string similarity_type;
};

class XMLDataHandler {
private:
    rapidxml::xml_document<> doc;
    rapidxml::xml_node<>* root_node = NULL;

public:
    XMLDataHandler() {
        // Perform XML parsing and store the DOM tree
        std::ifstream theFile("books.xml");
        std::vector<char> buffer((std::istreambuf_iterator<char>(theFile)), std::istreambuf_iterator<char>());
        buffer.push_back('\0');

        doc.parse<0>(&buffer[0]);
        root_node = doc.first_node("bookstore");
    }

    std::list<similar> graph_generation();
    std::list<std::string> search(std::string keyword);
    void printBookDataBasedOfId(std::string id);
    void printBook(std::string id);
    void print_struck(std::stack<std::string> &Cart);
};