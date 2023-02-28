/*Lab Assignment: Word guessing game
• Write a C program which reads the supplied dictionary file into an
array of strings (make sure the array is big enough for all the words..
100000 is plenty)
• The program should reject words from the file which have less than 4
or more then 7 letters
• It should then randomly pick a word and the user must guess letters
in the word and try to get the whole word in as few guesses as
possible (a politically incorrect person might call the game 'Hangman')
• Make appropriate use of functions
• For required output, see next slide*/

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

   /* This is reading the dictionary file and storing each word in the array dict. */
    char txt[MAXSTRING];
    while (fgets(txt, MAXSTRING - 1, file_ptr) != NULL) {
        txt[strlen(txt) - 1] = '\0';
        strcpy(dict[word], txt);
        word++;
    }
    printf("dictionary.txt contained %d lines.\n", word);
    fclose(file_ptr);

    /* This is filtering the dictionary file to only contain words that are between 4 and 7 characters
    long. */
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
    printf("Do you want to reveal the random word? Answer with 'Y or 'N': "); // used for testing purposes
    char revealInput;
    scanf("%c",&revealInput);
    if (revealInput == 'Y'){
        printf("%s\n", randomWord);
    }
    int num_guesses=0;
/* This is the main game loop. It is responsible for displaying the word to the user, asking for a
letter and checking if the letter is in the word. */
    do {
        for (int i = 0; i < strlen(randomWord); ++i) {
            if (wordDisplayed[i] == '\0') {
                // --> all chars in the empty character array are \0 hence we can use this to populate the char
                // array with dashes as required in the UI
                wordDisplayed[i] = '-';
            }
        }
        num_guesses++;
        printf("\nGuess %d. \n%s \n", num_guesses,wordDisplayed);
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
            //Setting the value of the variable running to 0. This is used to exit the game loop.
            running = 0;
        }
    } while (running);
}
