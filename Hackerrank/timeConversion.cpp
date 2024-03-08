#include <iostream>
#include <string>
#include <iomanip>

using namespace std;


// morning h == 12 = > 0
// evening h != 12 => h +=12

string timeConversion(string s)
{
    int h;
    sscanf(s.c_str(), "%d", &h);
    if(s[8] == 'A' && h == 12) h = 0;
    if(s[8] == 'P' && h != 12) h+=12;
    cout << setw(2) << setfill('0') << h;
    cout << s.substr(2, 6);
    return "Test";
}

int main() {
    string s;
    cin >> s;
    return 0;
}