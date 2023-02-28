#include <stdio.h>
#include <string.h>
#include "time.h"
#include "stdlib.h"

#define MAXWORDS 100000
#define MAXSTRING 100

char dict[MAXWORDS][MAXSTRING];
char filteredDict[100000][50];
char wordDisplayed[8];
char randomWord[8];
char wordLetter;


int main() {
    FILE* file_ptr;
    int running = 1;
    int word = 0;
    fopen_s(&file_ptr, "C:\\Users\\email\\Desktop\\dict.txt", "r");

    if (file_ptr == NULL) {
        printf("Could not open dictionary.txt");
        return 1;
    }

    char txt[MAXSTRING];
    while (fgets(txt, MAXSTRING - 1, file_ptr) != NULL) {
        txt[strlen(txt) - 1] = '\0';
        strcpy_s(dict[word], 100, txt);
        word++;
    }
    printf("dictionary.txt contained %d lines.\n", word);
    fclose(file_ptr);

    int num_filteredWords=0;
    for (int j = 0; j <word ; ++j) {
        if (strlen(dict[j]) >= 4 && strlen(dict[j]) <= 7)
        {
            strcpy(filteredDict[num_filteredWords], dict[j]);
            num_filteredWords++;
        }
    }
    printf("Loaded %d suitable words from the dictionary.\n",num_filteredWords);
    srand(time(NULL));
    strcpy(randomWord,filteredDict[rand() % num_filteredWords]); // random word assigned
    printf("%s\n", randomWord);

    int num_guesses=0;
    do {
        for (int i = 0; i < strlen(randomWord); ++i) {
            if (wordDisplayed[i] == '\0') { 
                // --> all chars in the empty character array are \0 hence we can use this to populate the char 
                // array with dashes as required in the UI..
                wordDisplayed[i] = '-';
            }
        }

        num_guesses++;
        printf("Guess %d. \n%s \n", num_guesses,wordDisplayed);
        printf("Guess a letter >  ");
        scanf(" %c",&wordLetter);
        for (int i = 0; i < strlen(randomWord); ++i) {
            if (randomWord[i]==wordLetter){
                wordDisplayed[i] = wordLetter;
            }
        }
        if (strcmp(randomWord, wordDisplayed) == 0)
        {
            printf("\n");
            printf("Well done, that took you %d guesses to find %s!",num_guesses,randomWord);
            running = 0;
        }
    } while (running);

}
