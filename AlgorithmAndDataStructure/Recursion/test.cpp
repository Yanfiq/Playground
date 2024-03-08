#include <iostream>
#include <string>
using namespace std;

int main() {
    string books[9] = {"Implementation of Linear Algebra",
                       "Data Structure and Algorithm 2nd Edition",
                       "Linear Algebra and Its Applications",
                       "Introduction to OpenCV Library (Python)",
                       "Introduction to Machine Learning",
                       "Data Structure and Algorithm 1st Edition",
                       "Machine Learning",
                       "Database Management System (Expert Edition)",
                       "Database Management System (Beginner Edition)"};


    for (int i = 0; i < 9; i++) {
        if (books[i] == "Introduction to OpenCV Library (Python)") {
            for (int j = i; j < 8; j++) {
                books[j] = books[j + 1];
            }
            break;
        }
    }

    for (int i = 0; i < 8; i++) {
        int max_idx = i;
        for (int j = i + 1; j < 8; j++) {
            if (books[j] > books[max_idx]) {
                max_idx = j;
            }
        }
        swap(books[i], books[max_idx]);
    }
    for(int k=0;k<4;k++){
        books[k]=books[k+1];
        if(books[k]=="Database Management System (Expert Edition)"){
            books[k]="Machine Learning";
    }
    }
    for(int k=7;k>4;k--){
        books[k]=books[k-1];
        if(books[k]=="Database Management System (Expert Edition)"){
            books[k]="Data Structure and Algorithm 1st Edition";
    }
    }
    for(int k=4;k<=8;k++){
        books[k]=books[k+1];
        if(books[k]=="Data Structure and Algorithm 2nd Edition"){
            books[k]="Database Management System (Expert Edition)";
            break;
    }
    }
    for(int k=7;k>=5;k--){
        books[k]=books[k-1];
        
    }
    books[5]="Data Structure and Algorithm 2nd Edition";
    books[1]=books[1+1];
    books[2]="Introduction to Machine Learning";


    for (int i = 0; i < 8; i++) {
        cout << books[i] << endl;
    }
    return 0;
}