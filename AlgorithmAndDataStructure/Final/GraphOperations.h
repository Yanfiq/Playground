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
#include "XMLDataHandler.h"

class similar{
    public:
        std::string book_a;
        std::string book_b;
        std::string similarity_type;

        std::list<similar> graph_generation();
};