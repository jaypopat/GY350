//Adventure Game 2 but using dynamic memory allocation
/*Extend last week’s ”Galway Adventure” assignment to now include objects which you
can TAKE and DROP.
• After typing TAKE or DROP, your program should ask
for the object’s name
• An object can only be taken if it’s in the current
location
• An object can only be dropped if it’s in the player’s
inventory
• You can start with your own code or with the
solution to last week’s work which I provided in
class
• Whenever the current location is displayed, a
list of objects at the location should also be
displayed.
• The command INVENTORY should display a list
of whatever the player is holding
• The command EXAMINE should display the
description of the requested object (only if it is
in the current location or player’s inventory)*/

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
typedef struct {
    char description[100];
    char name[20];
    int location;
} object;

//Creating an array of 40 locations of type location struct
location locations[40];
object objects[2];

int numLocations = 0;
//path to txt file passed to the function
char locationsFile[] = "C:\\Users\\email\\Desktop\\adventure_locations.txt";
char objectsFile[] = "C:\\Users\\email\\Desktop\\adventure_objects.txt";


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
    FILE* file_ptr2 = openFileForReading(objectsFile);
    if (file_ptr || file_ptr2 == NULL)
        return false;

    numLocations = 0;
    int readHeaderLines = 0;
    char line[200];
    char lineObjects[200];
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
    readHeaderLines=0;
    int numObjects =0;


    while (fgets(lineObjects, 99, file_ptr2) != NULL) {
        if (readHeaderLines<2)
            readHeaderLines++;
            //Reading the file and storing the information in the array of locations.
        else {
            numObjects++;
            object o;
            int objectCounter;
            sscanf(lineObjects, "%[^\t]\t%d\t%[^\t]\n", &o.name, &o.location,&o.description);
            object[numObjects] = o; 
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
                printf("%s", locations[currPosition].description);
            }
        }
    }
}

