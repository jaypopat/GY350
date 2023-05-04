#include <stdio.h>
#include <string.h>
#include <stdbool.h>

#define INVENTORY_LOCATION 0

typedef struct {
    char description[100];
    int n, s, e, w, in, out;
} location;

typedef struct {
    char name[50];
    char description[100];
    int location; // the location that the object is at. If 0, then it's in the player's inventory
} object;

enum command {
    ERROR,
    N, S, E, W, IN, OUT,
    LOOK, HELP, QUIT,
    TAKE, DROP, EXAMINE, INVENTORY,
    END
};

char commands[][20] = {"error","n","s","e","w","in","out","look","help","quit",
    "take","drop","examine","inventory"};

location locations[40];
int numLocations = 0;
char locationsFile[] = "adventure_locations.txt";

object objects[20];
int numObjects = 0;
char objectsFile[] = "adventure_objects.txt";

int playerLocationNum = 1;

FILE* openFileForReading(char* filename) {
    FILE* file_ptr = fopen(filename, "r");
    if (file_ptr == NULL) 
        printf("Could not open %s\n", filename);
    return file_ptr;
}

bool readLocations() {
    FILE* file_ptr = openFileForReading(locationsFile);
    if (file_ptr == NULL)
        return false;

    numLocations = 0;
    int readHeaderLines = 0;
    char line[200];
    while (fgets(line, 199, file_ptr) != NULL) {
        if (readHeaderLines<2)
            readHeaderLines++;
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

bool readObjects() {
    FILE* file_ptr = openFileForReading(objectsFile);
    if (file_ptr == NULL)
        return false;

    numObjects = 0;
    int readHeaderLines = 0;
    char line[200];
    while (fgets(line, 199, file_ptr) != NULL) {
        if (readHeaderLines<2)
            readHeaderLines++;
        else {
            object o;
            sscanf(line, "%[^\t]\t%d\t%[^\t]\n", o.name, &o.location, o.description);
            objects[numObjects] = o;
            numObjects++; 
        }
    }    

    return true;
}

command identifyCommand(char* txt) {
    for (int i=0; i<(int)END; i++) {
        if (strcmp(txt,commands[i])==0)
            return (command)i;
    }
    return ERROR;
}

bool tryMoveTo(int locationNum) {
    if (locationNum>0) {
        playerLocationNum = locationNum;
        return true;
    }

    printf("\nYou can't go that way.\n\n");
    return false;
}

void displayObjectsAtLocation(int locationNum) {
    bool anyObjects = false;
    for (int i=0; i<numObjects; i++) {
        if (objects[i].location==locationNum) {
            if (anyObjects) // this is not the first, so prefix with a comma
                printf(", ");
            printf("%s",objects[i].name);
            anyObjects = true;
        }
    }   
    if (!anyObjects)
        printf("None.");
}

void displayCurrentLocation() {
    location currLoc = locations[playerLocationNum];
    printf("\n%s", currLoc.description);
    printf("Objects here: ");
    displayObjectsAtLocation(playerLocationNum);
    printf("\n");
}

int askPlayerForObject(int atLocation1, int atLocation2) {
    // only consider objects at the same location as player, or in their inventory
    char txt[200] = "";
    gets(txt);

    for (int i=0; i<numObjects; i++) {
        if (objects[i].location==atLocation1 || objects[i].location==atLocation2) {
            if (strcmp(objects[i].name, txt)==0)
                return i; // matching object found, so return its array index
        }
    }

    return -1; // no object matched
}

void examine() {
    printf("Examine what? > ");
    int i = askPlayerForObject(playerLocationNum, INVENTORY_LOCATION);
    if (i>=0)
        printf("%s", objects[i].description);
    else
        printf("You can't see that.\n");
}

void drop() {
    printf("Drop what? > ");
    int i = askPlayerForObject(INVENTORY_LOCATION, INVENTORY_LOCATION);
    if (i>=0) {
        printf("You drop %s.\n", objects[i].name);
        objects[i].location = playerLocationNum;
    }
    else
        printf("You don't have that.\n");
}

void take() {
    printf("Take what? > ");
    int i = askPlayerForObject(playerLocationNum, playerLocationNum);
    if (i>=0) {
        printf("You take %s.\n", objects[i].name);
        objects[i].location = INVENTORY_LOCATION;
    }
    else
        printf("That is not here.\n");
}

void inventory() {
    printf("You are carrying: ");
    displayObjectsAtLocation(INVENTORY_LOCATION);
    printf("\n");
}

int main() {
    if (readLocations()) {
        printf("Welcome to Galway Adventure. Type 'help' for help.\n");
        if (!readObjects())
            printf("Warning: failed to read %s.\n", objectsFile);

        // game loop (one iteration per command from the player)
        char txt[200] = "";
        command cmd = ERROR;
        bool displayLocation = true;

        while (cmd!=QUIT) {
            location currLoc = locations[playerLocationNum];
            if (displayLocation) {
                displayCurrentLocation();
                displayLocation = false; // (for next iteration)
            }

            // read and interpret user input
            printf("> ");
            gets(txt);
            cmd = identifyCommand(txt);
        
            switch (cmd) {
                case N:
                    displayLocation = tryMoveTo(currLoc.n);
                break;

                case S:
                    displayLocation = tryMoveTo(currLoc.s);
                break;

                case E:
                    displayLocation = tryMoveTo(currLoc.e);
                break;

                case W:
                    displayLocation = tryMoveTo(currLoc.w);
                break;

                case IN:
                    displayLocation = tryMoveTo(currLoc.in);
                break;

                case OUT:
                    displayLocation = tryMoveTo(currLoc.out);
                break;

                case LOOK:
                    displayLocation = true;
                break;

                case EXAMINE:
                    examine();
                break;

                case TAKE:
                    take();
                break;

                case DROP:
                    drop();
                break;

                case INVENTORY:
                    inventory();
                break;

                case HELP:
                    printf("I know these commands:\n");
                    for (int i=1; i<(int)END; i++) {
                        if (i>1)
                            printf(", ");
                        printf("%s", commands[i]);
                    }
                    printf(".\n\n");
                break;

                case QUIT:
                    printf("Bye!\n");
                break;

                default:
                    printf("Huh?\n");
            }
        }
    }
}