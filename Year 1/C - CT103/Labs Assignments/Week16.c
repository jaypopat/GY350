/*Today you will be given two datasets stored as text (.txt) files. The first is called “dogs.txt”
and contains a list of dog breeds. The second is called “countries.txt” and contains a list of
countries and their populations.
Write a C program that does the following:
1. Read in the data from the file “dogs.txt”. Get the size of the file in bytes and print it
to the screen. (20 marks)
2. Update “dogs.txt” to replace the dog breed on the final line of the file with the breed
“Boxer”. (25 marks)
3. Read in the data from the file “countries.txt”. As you read in the data, store the full
name of each country as a string and the population as an integer. Print the data in
the file to the screen. Hint: “atoi()” converts strings to ints. (35 marks)
4. Update “countries.txt” by appending a new country and corresponding population to
the end of the file. Select a country that begins with the same letter as either your
first name or surname, e.g. if your name is “Bob” you could pick “Brazil”, “Smith”
could be “South Africa”. */
#include <stdio.h>
/* Declaring global variables. */
char countries[30];
long int population;
void main() {
    /* Opening the file dogs.txt in read and write mode(r+). */
    FILE *fptr;
    fopen_s(&fptr, "C:/Users/email/Desktop/dogs.txt", "r+");
    
    /* Checking if the file is opening. If it is, it will print the size of the file. If it is not, it
    will print "File aint opening". */
    
    if (fptr != NULL) {
        fseek(fptr, 0, SEEK_END);
        int len = ftell(fptr);
        printf("Size of dogs.txt: %d bytes.\n", len);
    } else {
        printf("File aint opening");
    }
    
    /* Moving the pointer 3 bytes back from the end of the file and then writing the word "Boxer" to
    the file. */
    fseek(fptr, -3, SEEK_END);
    fprintf(fptr, "Boxer");
    fclose(fptr);

    /* Reading the file countries.txt and printing the countries and their populations. */
    fopen_s(&fptr, "C:/Users/email/Desktop/countries.txt", "r");
    int j=0;
    /* If a tab is not detected the individual characters are copied onto the countries character array.Following this the population is scanned in and j getting initialised back to 0 so it runs again*/
    while(!feof(fptr)){
        char c = fgetc(fptr);
        while (c!= '\t'){
            countries[j]=c;
            j++;
            c = fgetc(fptr);
        }
        countries[j]='\0';
        fscanf_s(fptr,"%ld",&population);
        printf("%s %ld", countries,population);
        j=0;
    }
    fclose(fptr);
    /* Opening the file countries.txt in append mode and then writing the country Jamaica and its
    population 88000 to the file. */
    fptr = fopen("C:/Users/email/Desktop/countries.txt", "a+");
    fprintf(fptr, "\n%s","Jamaica\t88000");
    fclose(fptr);
}
/*output text of txt files
dogs:
Labrador
German Shepherd
Golden Retriever
Beagle
Poodle
Boxer

countries:
United Kingdom	68000000
Republic of Ireland	5000000
United States of America	332000000
Italy	59000000
Spain	48000000
Germany	83000000
The Netherlands	18000000
Jamaica	88000
 */
