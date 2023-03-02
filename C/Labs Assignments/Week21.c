/*Graded Assignment: Flesch Readability Index
• Write a program which reads all the text in a file and computes the Flesch Readability Index for it.
• The Flesch Readability Index was invented as a simple tool for determining the legibility of a
document without linguistic analysis. It may be implemented using the following 4 steps:
1. Count all words. A word is any sequence of characters delimited by white space.
2. Count all syllables in each word. Each group of adjacent vowels (a, e, i, o, u, y) counts as one
syllable (for example, the "ea" in "real" contributes one syllable, but the "e..a" in "regal" counts
as two syllables). However, an "e" at the end of a word doesn't count as a syllable. Also, each
word has at least one syllable, even if the previous rules give a count of 0.
3. Count all sentences. A sentence is ended by a full stop, colon, semicolon, question mark, or
exclamation mark.
4. The index is computed by the following formula:*/

#include <stdio.h>
#include <string.h>
#include <ctype.h>

int indexCalculator();

char alltext[200000];
char duplicatedText[200000];
char oneline[1000];

int main() {
    FILE *file_ptr;
    fopen_s(&file_ptr, "C:\\Users\\email\\Desktop\\article-irish-times.txt", "r");
    int word = 0;
    int sentence = 0;

    if (file_ptr == NULL) {
        printf("Could not open article\n");
    } else {
        alltext[0] = '\0'; // make sure this string is empty
        while (fgets(oneline, 999, file_ptr) != NULL) {
            // read the next line and append it (with \n intact)
            strcat(alltext, oneline);
        }
        printf("%s", alltext);
        fclose(file_ptr);
    }
    printf("\n------------------------\n");
    
    indexCalculator(); // do this for two text files
}

int indexCalculator(){
    int word = 0;
    int sentence = 0;
    char *wordToken = strtok(alltext," ");
    //printf("%s",token);
    while (wordToken!=NULL){
        //printf("%s ",token); prints the text again
        wordToken = strtok(NULL," ");
        word++;
    }
    printf("%d words",word);
    printf("\n------------------------\n");
    strcpy(duplicatedText, alltext);
    char sentenceDelims[] = ".:;?!";
    char *sentenceToken = strtok(alltext,sentenceDelims);
    //printf("%s",token);
    while (sentenceToken!=NULL){
        //printf("%s ",token); prints the text again
        sentenceToken = strtok(NULL,sentenceDelims);
        sentence++;
    }
    printf("%d sentences",sentence);
    return 0;
}

