/*
 * Author: Levi Hutchins
 * This simple C++ script is executed at the end of the system.
 * It compares every character between the decrypted and original file.
 * If a difference is found then will inform the user that the files are not the same.
*/


// Include all required libraries
#include <string>
#include <fstream>
#include <iostream>
#include <chrono>
using namespace std;
using namespace std::chrono;

int main(){
    // I include the time for comparing the two files to demonstrate
    // C++ speed.
    auto start = high_resolution_clock::now();
    fstream file1,file2;
    file1.open("../output/new.txt", ios::in);
    file2.open("../output/Decrypted.txt", ios::in);

    if(file1.is_open() && file2.is_open()) {
        string f1, f2;
        bool isMatch = true;
        while(getline(file1,f1) && getline(file2,f2)){}
        if( f1.length() != f2.length() ) cout << "Error Files Do Not Match" << endl;
        unsigned int length = f1.length();
        for(int i = 0; i < length; i++) {
            if(f1[i] != f2[i]) isMatch = false; // If a single character is not the same then the files are not
            else continue;         // idenctical therefore decryption failed.
        }
        auto stop = high_resolution_clock::now();

        auto timeTaken = duration_cast<microseconds>(stop-start);
        cout << "--------------------------------" << endl;
        if(isMatch) cout << "Files Match!" << endl;
        else cout << "Error Files Do Not Match!" << endl;
        cout << "Time taken to compare file: " << endl;
        cout << "--- " << timeTaken.count() << " Microseconds ---" <<  endl;


    }
    return 0;
}