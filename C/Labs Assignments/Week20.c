#include <stdio.h>
#include <string.h>
#include <stdlib.h>

char dictionary[10000][50];
char adict[10000][50];



void main() {
    FILE *fptr;
    fopen_s(&fptr, "C:\\Users\\email\\Desktop\\GY350\\Coding in C\\C-Lion Projects\\Labs Week 18\\dict.txt", "r");
    if (fptr == NULL) {
        puts("Error Opening File \n Exiting ........");
        return;
    }
    for (int i=0;i<10000;i++){
        for (int j=0;j<20;j++)
        {
            fscanf(fptr, "%c", &dictionary[i][j]);
        }
    }
    fclose(fptr);

    int num_filteredWords=0;
    for (int i = 0; i < 10000; ++i) {
        if (strlen(dictionary[i])<4 || strlen(dictionary[i])>7){
            continue;
        }
        strcpy(adict[num_filteredWords],dictionary[i]);
        num_filteredWords++;
    }
    printf("%d", num_filteredWords);

    for (int i = 0; i <num_filteredWords ; ++i) {
        for (int j = 0; j <20 ; ++j) {
            printf("%c", adict[i][j]);
        }
    }

}