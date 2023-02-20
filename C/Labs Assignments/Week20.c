#include <stdio.h>
#include <string.h>
#include "time.h"
#include "stdlib.h"

char dictionary[10000][50];
char filteredDict[10000][50];
char randomWord[8];
char guess[8];

int main()
{
    FILE *fptr;
    int running = 1;
    int i =0;
    int j=0;
    fptr = fopen("C:\\Users\\email\\Desktop\\dict.txt", "r");
    if (fptr == NULL)
    {
        puts("Error Opening File");
        return 1;
    }
    while(!feof(fptr))
    {
        char c = fgetc(fptr);
        if (c == '\n')
        {
            dictionary[i][j]='\0';
            i++;j=0;
        }
        else
        {
            dictionary[i][j]= c;
            j++;
        }

    }
    fclose(fptr);
    for ( i = 0; i < 10000; i++)
    {
        for ( j = 0; j < 50; j++)
        {
            if (dictionary[i][j] == '\0')
                break;

            printf("%c", dictionary[i][j]);
        }
        printf("\n");
    }

    // after debugging -> found out strlen doesnt work --> strings are not null terminated which is why have to 
    // manually add it with code above but each word_token is parsed and separated using a newline

    char *word_token = strtok(dictionary[0], "\n");
    int num_filteredWords=0;
    while (word_token != NULL)
    {
        if (strlen(word_token) >= 4 && strlen(word_token) <= 7)
        {
            strcpy(filteredDict[num_filteredWords], word_token);
            num_filteredWords++;
        }
        word_token = strtok(NULL, "\n");
    }
    printf("%d", num_filteredWords);

    for (i = 0; i < num_filteredWords; i++)
    {
        for (j = 0; filteredDict[i][j] != '\0'; j++)
        {
            printf("%c", filteredDict[i][j]);
        }
        printf("\n");
    }

    srand(time(NULL));
    strcpy(randomWord,filteredDict[rand() % num_filteredWords]); // random word assigned
    printf("%s", randomWord);

    int num_guesses=0;
    do {
        num_guesses++;
        printf("Guess %d. Enter answer: ", num_guesses);
        scanf("%s", guess);
        if (strcmp(randomWord, guess) == 0)
        {
            printf("You won!\n");
            running = 0;
        }
        else
        {
            printf("Wrong answer, try again.\n");
        }
    } while (running);
    return 0;
}
