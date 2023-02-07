#include <stdio.h>
#include <stdlib.h>
#include "time.h"
//Declaring the functions that will be used in the program.
void printDoubleArray(double* dp, int len);
void swapIntegerValues(int* i1, int* i2);
void squareIntArray(int* i1, int len);
void printIntegerArrayBackwards(int* arr, int len);
void randomIntArray(int* arr, int len, int max);

int main() 
{
    //Seeding the random number generator with the current time.
    srand(time(NULL));
    int x = 10, y = 20;
    printf("Before inversion: x = %d, y = %d\n", x, y);
    swapIntegerValues(&x, &y);
    printf("After inversion: x = %d, y = %d\n", x, y);
    int array[] = {1,2,3,4,5};
    double dbarray[4] = {1.5,4.9,8.9,5.9};
    printf("%s","Printing Integer Array Backwards: \n");
    printIntegerArrayBackwards(array, 5);
    printf("\n%s","Printing Square of the integer array elements: \n");
    squareIntArray(array,5);
    printDoubleArray(dbarray,4);
    printf("\nRandom Number Generator\n");
    int randomArray[10];
    randomIntArray(randomArray,10,10);
    return 0;
}
/*
 The function takes a pointer to a double and an integer as arguments. It prints the double array
  pointed to by the pointer. dp pointer to the first element of the array and len - length of the array
 */
void printDoubleArray(double* dp, int len) 
{
    puts("\nPrinting double array");
    for (int i = 0; i < len; i++) 
    {
        printf("%.1lf ", *(dp+i));
    }
}

  //The function takes two integer pointers as arguments and swaps the values of the integers they point
void swapIntegerValues(int* i1, int* i2)
{
    int temp;
    temp = *i1;
    *i1 = *i2;
    *i2 = temp;
}
//The function takes a pointer to an array of integers and the length of the array as arguments and prints the square of each element of the array

void squareIntArray(int* i1, int len)
{
    for (int i = 0; i < len;i++) 
    {
        printf("%d ",*(i1+i)**(i1+i));
    }
}

//It prints the elements of an array in reverse order

void printIntegerArrayBackwards(int* arr, int len)
{
    for (int i = 0; i < len;i++) 
    {
        printf("%d ",*(arr + len - 1 - i));
    }
}
/*
  It takes an array of integers, a length, and a maximum value, and fills the array with random
  integers between 0 and the maximum value
 */
void randomIntArray(int* arr, int len, int max)
{
    for (int i=0;i<len;i++)
    {
        *(arr + i) = rand() % max;
        printf("%d", *(arr + i));
    }
}
