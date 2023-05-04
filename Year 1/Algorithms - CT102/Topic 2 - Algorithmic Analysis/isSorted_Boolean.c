#include <stdbool.h>
#include <stdio.h>

bool isSorted(int arr[], int size);

// check if data in an integer array is in ascending sorted order
// return True or False
bool isSorted(int arrA[], int size)
{
    int i;
    bool sorted = true;
    
    for(i = 0; i < size - 1 && sorted; i++) {
        if(arrA[i] > arrA[i+1]) {
            sorted = false;
        }
    }
    return sorted;
}

int main(void)
{
    int arrA[] = {1, 2, 3, 4, 5};
    int sizeA = sizeof(arrA) / sizeof(arrA[0]);
    bool sortedA = isSorted(arrA, sizeA);
    printf("Array A is sorted: %s\n", sortedA ? "True" : "False");

    int arrB[] = {5, 4, 3, 2, 1};
    int sizeB = sizeof(arrB) / sizeof(arrB[0]);
    bool sortedB = isSorted(arrB, sizeB);
    printf("Array B is sorted: %s\n", sortedB ? "True" : "False");

    return 0;
}