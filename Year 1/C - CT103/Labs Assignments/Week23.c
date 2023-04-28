/*Semester 2, Week 10 Lab Assignment
• Using the file adventure_locations.txt, implement the
movement commands (N, S, E, W, IN, OUT) as well as LOOK,
HELP and QUIT
• Display the description of each location as the player moves to it
• You can start with the code in 10_AdventureA_ReadLocations.cpp
• See sample input/output on next slide
• Questions:
• What additional data do we need?
• How do we get the continual game loop working?
• What does its loop test assess, in order to continue or exit?
• What do we do inside the loop?
• And how?*/

#include <stdio.h>
#include <string.h>
#include "stdbool.h"

typedef struct {
    char description[100];
    int n, s, e, w, in, out;
} location;

//Creating an array of 40 locations of type location struct
location locations[40];
int numLocations = 0;
//path to txt file passed to the function
char locationsFile[] = "C:\\Users\\email\\Desktop\\adventure_locations.txt";


//Open a file for reading and return a pointer to it.
FILE* openFileForReading(char* filename) {
    FILE* file_ptr = fopen(filename, "r");
    if (file_ptr == NULL)
        printf("Could not open %s\n", filename);
    return file_ptr;
}


//It opens a file for reading, and if it can't, it returns false.
bool readLocations() {
    FILE* file_ptr = openFileForReading(locationsFile);
    if (file_ptr == NULL)
        return false;

    numLocations = 0;
    int readHeaderLines = 0;
    char line[200];
    //Reading the first two lines of the file and ignoring them.
    while (fgets(line, 99, file_ptr) != NULL) {
        if (readHeaderLines<2)
            readHeaderLines++;
            //Reading the file and storing the information in the array of locations.
        else {
            numLocations++;
            location l;
            int locNum;
            sscanf(line, "%d\t%d\t%d\t%d\t%d\t%d\t%d\t%[^\t]\n", &locNum, &l.n, &l.s, &l.e, &l.w, &l.in, &l.out, l.description);
            locations[numLocations] = l; // the 1st location is 1 (not 0) so we can use 0 to mean 'nothing' in adventure_locations.txt
        }
    }
    return true;
}

int main() {
    char input[8];
    //This is the welcome message that is displayed when the program is run.
    if (readLocations()) {
        printf("Welcome to Galway Adventure. Type 'help' for help.\n""On the NUIG campus, outside the CS building.\n""warning: this program uses gets(), which is unsafe.\n""> help\n""\n""I know these commands:\n""\n""n, s, e, w, in, out, look, help, quit.\n");

        int currPosition = 1;
        while (strcmp(input, "quit") != 0) {
            printf("\n");
            printf("> ");
            gets(input);

            /* This is the main loop of the program. It reads the user's input and then checks if it is
            a valid command. If it is, it updates the current position and prints information by accessing
            the structure using the current position as an index for the location array. If it is not, it prints error message. */

            if (strcmp(input, "quit") == 0) {
                printf("Bye!!");
                break;
            }
            int prevPosition = currPosition;
            if (strcmp(input, "in") == 0) {
                currPosition = locations[currPosition].in;
            } else if (strcmp(input, "out") == 0) {
                currPosition = locations[currPosition].out;
            } else if (strcmp(input, "look") == 0) {
            } else if (strcmp(input, "help") == 0) {
                printf("I know these commands:\n""\n""n, s, e, w, in, out, look, help, quit.");
                continue; // as a result it doesn't print description which is at L97
            } else if (strcmp(input, "n") == 0) {
                currPosition = locations[currPosition].n;
            } else if (strcmp(input, "s") == 0) {
                currPosition = locations[currPosition].s;
            } else if (strcmp(input, "e") == 0) {
                currPosition = locations[currPosition].e;
            } else if (strcmp(input, "w") == 0) {
                currPosition = locations[currPosition].w;
            } else{
                printf("Invalid Command \nI know these commands: n, s, e, w, in, out, look, help, quit.");
                continue;
            }
            if (currPosition == 0) { // check if new position is invalid
                printf("You cannot go there.\n");
                currPosition = prevPosition; // reset to previous position
            } else {
                //printf("%d\n", currPosition); - used for debugging/checking purposes
                printf("%s", locations[currPosition].description);
            }
        }
    }
}

/*in main
    if (readLocations()) {
        printf("Successfully read %d locations from file\n", numLocations);
        int location_counter = 1;
        while (location_counter<=numLocations) {
            int northNum = locations[location_counter].n;
            char northDescription[100]={};
            if (northNum==0) {
                char noNorthOptionString[] = "From there you cannot go North";
                strcpy(northDescription,noNorthOptionString);
            } else{
                char suffix[20];
                int north_ref = locations[location_counter].n;
                strcpy(suffix,locations[north_ref].description);
                char NorthOptionString[] = "North leads to ";
                strcat(NorthOptionString,suffix);
                strcpy(northDescription,NorthOptionString);
            }
            printf("Location %d is %s %s\n", location_counter, locations[location_counter].description,northDescription);
            location_counter++;
        }
    } // EXERCISE TO DISPLAY LOCATION WHEN NORTH IS CHOSEN
*/


