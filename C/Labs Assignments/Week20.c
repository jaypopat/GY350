#include <stdio.h>
#include <string.h>

char dictionary[10000][50];
char filteredDict[10000][50];

void main() {
    FILE *fptr;
    int i =0;
    int j=0;
    fptr = fopen("C:\\Users\\email\\Desktop\\GY350\\Coding in C\\C-Lion Projects\\Labs Week 18\\dict.txt", "r");
    if (fptr == NULL) 
    {
        puts("Error Opening File");
        return;
    }
    while(!feof(fptr))
    {
        char c = fgetc(fptr);
        if (c == '\n')
        {
            dictionary[i][j]='\0';
            i++;
            j=0;
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
            {
                break;
            }
            printf("%c", dictionary[i][j]);
        }
        printf("\n");
    }

    // after debugging -> found out strlen doesnt work --> strings are not null terminated which is why have to manually add it with code above
    // but each word is parsed and separated using a newline

    int num_filteredWords=0;
    for (i = 0; i < 10000; i++) 
    {
        if (strlen(dictionary[i])<4 || strlen(dictionary[i])>7)
        {
            continue; // skips the strings
        }
        strcpy(filteredDict[num_filteredWords],dictionary[i]);
        num_filteredWords++;
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
}