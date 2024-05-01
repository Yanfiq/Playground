#pragma once
#include <vector>
#include <iostream>

namespace utils{

    //fungsi ini digunakan untuk memisahkan suatu string menjadi beberapa substring berdasarkan delimiter yang ditentukan
    std::vector<std::string> splitString(std::string string, std::string delimiter){
        std::vector<std::string> strings;

        size_t pos = 0;
        while ((pos = string.find(delimiter)) != std::string::npos) {
            strings.push_back(string.substr(0, pos));
            string.erase(0, pos + delimiter.length());
        }
        strings.push_back(string);
        return strings;
    }

    //https://stackoverflow.com/questions/14265581/parse-split-a-string-in-c-using-string-delimiter-standard-c
}