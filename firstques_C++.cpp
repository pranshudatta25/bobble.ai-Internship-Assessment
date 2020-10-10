#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>
using namespace std;

string input[5000];   //storing the words in the string array
string frequency[5000];       // storing the frequency of the word in integer array

const char* getfield(char* line, int num)
{
    const char* token;
    for (token = strtok(line, ",");
            token && *token;
            token = strtok(NULL, ";\n"))
    {
        if (!--num)
            return token;
    }
    return NULL;
}

void readrec(string rename23){
        int count=0;
        std::string name=rename23;
        for(int i=0;i<sizeof(input);i++){
            if(input[i].compare(name)==0){      //comparing each stored value with the required word;
                std::cout<<"Yes, "<<frequency[i];
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
    FILE* stream = fopen("C:\Users\Pranshu Datta\Downloads", "r");

    char line[1024];
    while (fgets(line, 1024, stream))
    {
        char* tmp = strdup(line);
        char *tmp2 = strdup(line);
        //printf("Field 1 would be %s\n", getfield(tmp, 1));
        input[i] = getfield(tmp, 1);
//        printf("Field 2 would be %s\n", getfield(tmp2, 2));
	frequency[i] = getfield(tmp2, 2);
        // NOTE strtok clobbers tmp
        free(tmp);
        i++;
    }
    
     string inp_word;
    cout<<"Enter the word to search:";
    cin>>inp_word;      //input from user
    
    readrec(inp_word);    // calling the function
    
}
