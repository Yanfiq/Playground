#include "GraphOperations.h"
#include "XMLDataHandler.h"
#include "rapidxml/rapidxml.hpp"

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

std::list<similar> graph_generation(){
    std::list<similar> similarity_list;

    for (rapidxml::xml_node<> * book_node_A = root_node->first_node("book");
        book_node_A; 
        book_node_A = book_node_A->next_sibling())
    {   
        for (rapidxml::xml_node<> * book_node_B = book_node_A->next_sibling();
            book_node_B; 
            book_node_B = book_node_B->next_sibling())
        {
            std::string Book_A_author = book_node_A->first_node("author")->value();
            std::string Book_A_genre = book_node_A->first_node("genre")->value();
            std::string Book_B_author = book_node_B->first_node("author")->value();
            std::string Book_B_genre = book_node_B->first_node("genre")->value();
            if(Book_A_author == Book_B_author){
                similarity_list.push_back({book_node_A->first_attribute("id")->value(), book_node_B->first_attribute("id")->value(), "author"});
                similarity_list.push_back({book_node_B->first_attribute("id")->value(), book_node_A->first_attribute("id")->value(), "author"});
            }
            if(Book_A_genre == Book_B_genre){
                similarity_list.push_back({book_node_A->first_attribute("id")->value(), book_node_B->first_attribute("id")->value(), "genre"});
                similarity_list.push_back({book_node_B->first_attribute("id")->value(), book_node_A->first_attribute("id")->value(), "genre"});
            }
        }
    }
    return similarity_list;
}