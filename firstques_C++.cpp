#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
using namespace std;

string word[5000];   //storing the words in the string array
string freq[5000];       // storing the frequency of the word in integer array

const char* getfield(char* line, int num)
{
    const char* tok;
    for (tok = strtok(line, ",");
            tok && *tok;
            tok = strtok(NULL, ";\n"))
    {
        if (!--num)
            return tok;
    }
    return NULL;
}

void readrecord(string rename23){
        int count=0;
        std::string name=rename23;
        for(int i=0;i<sizeof(word);i++){
            if(word[i].compare(name)==0){      //comparing each stored value with the required word;
                std::cout<<"Yes, "<<freq[i];
                count++;
                break;
            }
        }
            if(count==0){
                std::cout<<"No";
            }
}

int main()
{
    int i=0;
    FILE* stream = fopen("/home/pc799/Desktop/EnglishDictionary.csv", "r");

    char line[1024];
    while (fgets(line, 1024, stream))
    {
        char* tmp = strdup(line);
        char *tmp2 = strdup(line);
        //printf("Field 1 would be %s\n", getfield(tmp, 1));
        word[i] = getfield(tmp, 1);
//        printf("Field 2 would be %s\n", getfield(tmp2, 2));
	freq[i] = getfield(tmp2, 2);
        // NOTE strtok clobbers tmp
        free(tmp);
        i++;
    }
    
     string rename23;
    cout<<"Enter the word to search:";
    cin>>rename23;      //input from user
    
    readrecord(rename23);    // calling the function
    
}
