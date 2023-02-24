#include <stdio.h>
#include <string.h>
#include "time.h"
#include "stdlib.h"
#define   MAXSTRING   100
char dict[10000][MAXSTRING];
char filteredDict[10000][50];
char randomWord[8];
char guess[8];

int main() {
    FILE *file_ptr;
    int running = 1;
    int i =0;
    int j=0;
    int word = 0;
    file_ptr = fopen("C:\\Users\\email\\Desktop\\dict.txt", "r");

    if (file_ptr == NULL)
        printf("Could not open dictionary.txt");
    else {
        char txt[MAXSTRING];
        while (fgets(txt, MAXSTRING-1, file_ptr)!=NULL) {
            txt[strlen(txt) - 1] = '\0';
            strcpy(dict[word], txt);
            word++;
        }
        printf("dictionary.txt contained %d lines.", word);
        fclose (file_ptr);
    }
    for (int k = 0; k <word ; ++k) {
        for (int l = 0; l < MAXSTRING; ++l) {
            printf("%c",dict[i][j]);
        }
    }


    return 0;
}