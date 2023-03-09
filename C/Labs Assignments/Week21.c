#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>


char alltext[200000];
char Second_alltext[200000];
char oneline[1000];
char Second_oneline[1000];

int syllableCounter(char* wordToken);
void indexCalculator(char articleText[]);

int main() {
    FILE *file_ptr;
    fopen_s(&file_ptr, "C:\\Users\\email\\Desktop\\article-irish-times.txt", "r");

    if (file_ptr == NULL) {
        printf("Could not open article\n");
    } else {
        alltext[0] = '\0'; // make sure this string is empty
        while (fgets(oneline, 999, file_ptr) != NULL) {
            // read the next line and append it (with \n intact)
            strcat(alltext, oneline);
        }
        fclose(file_ptr);
    }

    printf("Flesh Readability Index");
    printf("\n------------------------\n");

    fopen_s(&file_ptr, "C:\\Users\\email\\Desktop\\article-green-eggs-and-ham.txt", "r");

    if (file_ptr == NULL) {
        printf("Could not open article\n");
    } else {
        Second_alltext[0] = '\0'; // make sure this string is empty
        while (fgets(Second_oneline, 999, file_ptr) != NULL) {
            // read the next line and append it (with \n intact)
            strcat(Second_alltext, Second_oneline);
        }
        fclose(file_ptr);
    }

    indexCalculator(alltext);
    printf("\n------------------------\n");
    indexCalculator(Second_alltext);

    return 0;
}

int syllableCounter(char* wordToken){
    int syllables = 0;
    int vowelsInARow =0;

    int wordTokenLen = strlen(wordToken);
    /* Counting the syllables in a word. */
    for (int i = 0; i < wordTokenLen; i++) {
        char c = tolower(wordToken[i]);
        // word = roach --> used for testing
        //char next_c = tolower(wordToken[i+1]);
        
        /* This is checking if the character is a vowel. If it is, it will increment the syllables. */
        if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u' || c == 'y') {
            if (vowelsInARow == 0) {
                syllables++;
            }
            vowelsInARow++;
        } else {
            vowelsInARow = 0;
        }
        /* This is checking if the last character of the word is an 'e' and if the syllables is greater
        than 1. If it is, it will decrement the syllables. */
        if (i == wordTokenLen - 1 && c == 'e' && syllables > 1) {
            syllables--;
        }
    }
    /* Checking if the syllables is 0. If it is, it will set the syllable value to 1 */
    if (syllables == 0) {
        syllables = 1;
    }
    return syllables;
}

void indexCalculator(char articleText[]) {
    int sentence = 0;
    int syllables;
    int wordCounter = 0;
    int syllableCount = 0;

    /* This is a loop that is going through the articleText and counting the syllables in each word. */
    char *word = strtok(articleText, " ");
    while (word != NULL) {
        syllables = syllableCounter(word);
        syllableCount += syllables;
        word = strtok(NULL, " ");
        wordCounter++;

        /* This is checking if the last character of the word is a full stop, exclamation mark or question
        mark. If it is, it will increment the sentence counter. */
        char lastCharofToken = word[strlen(word) - 1];
        if (lastCharofToken == '.' || lastCharofToken == '!' || lastCharofToken == '?') {
            sentence++;
        }
    }
    printf("%d words\n", wordCounter);
    printf("%d sentences\n", sentence);
    printf("%d syllables\n", syllableCount);

    /* This is calculating the Flesch Readability Index*/
    float index = 206.835 - 84.6 * (syllableCount / wordCounter) - (1.015 * (wordCounter / sentence));
    printf("%.3f", index);
} // my attempt -> didnt print out the output

//solution
#include <stdio.h> 
#include <string.h>
#include <ctype.h>

char alltext[200000];
char oneline[1000];

const char* delimeters = " \n\t,";

int syllablesInWord(char* word);

int main() {
    FILE *file_ptr;
    file_ptr = fopen("article-irish-times.txt", "r"); // open for reading
    //file_ptr = fopen("article-green-eggs-and-ham.txt", "r");

    if (file_ptr == NULL) {
        printf("Could not open article\n");
    }
    else {
        alltext[0] = '\0'; // make sure this string is empty
        while (fgets(oneline, 999, file_ptr)!=NULL) {
            // read the next line and append it (with \n intact)
            strcat(alltext, oneline);
        }

        fclose (file_ptr);

        // loop the whole text to count sentences
        // note that we do this before running strtok since strtok modifies the string itself
        // (A sentence is ended by a full stop, colon, semicolon, question mark, or exclamation mark)
        int sentences = 0;
        int len = strlen(alltext);
        for (int i=0; i<len; i++) {
            char c = alltext[i];
            if (c=='.' || c==':' || c==';' || c=='?' || c=='!')
                sentences++;
        }

        // Calculate the Flesch Readability Index
        char *word = strtok(alltext, delimeters);
        int syllables = 0;
        int words = 0;
        while( word != NULL ) {
            // loop each word to count them and their syllables
            words++;
            syllables += syllablesInWord(word);
            word = strtok(NULL, delimeters);
        }

        float flesch = 206.835 - 84.6 * ((float)syllables/words) - 1.015 * ((float)words/sentences);

        printf("Words=%d  Syllables=%d  Sentences=%d\n", words, syllables, sentences);
        printf("Flesch Readability Index = %f\n", flesch);
    }
}

int syllablesInWord(char* word) {
    // Each group of adjacent vowels (a, e, i, o, u, y) counts as one syllable
    // However, an "e" at the end of a word doesn't count as a syllable
    int s = 0;
    bool inVowelGroup = false;
    int len = strlen(word);
    for (int i=0; i<len; i++) {
        char c = tolower(word[i]);
        bool isVowel = (c=='a' || c=='e' || c=='i' || c=='o' || c=='u' || c=='y');
        if (isVowel && !inVowelGroup) {
            if (i+1<len || c!='e')
                s++;
        }
        inVowelGroup = isVowel;
    }

    if (s==0) // 0-syllable words not allowed
        return 1;

    return s;
}