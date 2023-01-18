/*
Write a C program that does the following:
1. A ship is lost somewhere in the sea. The sea is represented as a 12X12 grid, i.e. grid size = 12. Create two global integers “shipX” and “shipY” that represent the x and y
co-ordinates of the ship at sea. Initialize these randomly between 0 and 11
(inclusive) within main(). (20 marks)

2. Create a function called ‘randomSearch’. This function is of type void. This function should accept the grid size as a parameter passed into the function. This function
should randomly generate x, y co-ordinates until the location of the ship is found.Print the number of locations tried and the location of the ship found. Note: Do not
use recursion for this function. (30 marks)

3. Create another function called ‘gridSearch’. This function is of type void. This function should accept: 1) The grid size and 2) the number of locations tried so far,
as parameters passed into the function. This function should sequentially search through the grid until the ship is located. See Figure 1. Print the number of locations
tried and the location of the ship found. Note: Use recursion for this function. (40 marks)

4. Test your program once more by changing the grid size to the final digit in your student ID, and resetting the random position of the ship. Use both ‘randomSearch’
and ‘gridSearch’ functions again to search for the ship. If your student ID ends in ‘0’ or ‘1’, use ‘9’ instead. 
*/

// Jay Popat    22346566    17/01/2023
#include <stdio.h>
#include <time.h>
#include <stdlib.h>

// These are the variables that are used to store the location of the ship(x and y co-ordinates).
int shipX = 0;
int shipY = 0;

//This is the variable that is used to store the location of the grid search. 
int recurX = 0;
int recurY = 0;

//function declarations
void randomSearch(int gridSize);
void gridSearch(int gridSize, int tries);

void main()
{
    int gridSize = 12;
    /* This is generating a random number between 0 and the 11 inclusive, and assigning it to the ship's x
    and y coordinates. */
    srand(time(NULL));
    shipX = rand() % gridSize;
    shipY = rand() % gridSize;
    randomSearch(gridSize);
    gridSearch(gridSize, 0);
    printf("\n============New grid============");
    gridSize = 6;//A different grid size is assigned to the variable to test out the program (step4)

    /* This is generating a random number between 0 and 6 inclusive, and assigning it to the ship's x
    and y coordinates. */
    shipX = rand() % gridSize;
    shipY = rand() % gridSize;
    randomSearch(gridSize);
    gridSearch(gridSize, 0);
}

void randomSearch(int gridSize)
{
    int x = 0;
    int y = 0;
    int counter = 0;
    /*A random x and y coordinate is generated until the ship's location is found, and it prints out the number of 
    locations searched and the ship's location.*/
    while (!(x == shipX && y == shipY)) {
        x = rand() % gridSize;
        y = rand() % gridSize;
        counter++;
    }
    printf("\n------------- Random Search -----------");
    printf("\nShip found after %d locations!\nShip Co-ordinates - x : %d , y : %d.", counter, x, y);
}

void gridSearch(int gridSize, int tries) {

    /*checking if the ship's location has been found. If it has, it prints out the number of
    tries and the ship's location. */
    if (recurX == shipX && recurY == shipY) {
        printf("\n------------- Grid Search -----------");
        printf("\nShip found after %d locations", tries);
        printf("\nShip Co-ordinates - x : %d , y : %d .", recurX, recurY);
        return;
    }
    else {
        /*  The x coordinate is assigned by taking the remainder of the number of tries divided by the grid size. The y coordinate is
        assigned by taking the number of tries divided by the grid size. */
        recurX = tries % gridSize;
        recurY = tries / gridSize;
        //printf("\n x,y co ordinates= %d, %d", recurX, recurY); Used to help to visualise the process undertaken by the function
        gridSearch(gridSize, tries + 1);//This is calling the function again(recursion), but with the number of tries incremented by 1. 
    }
}
