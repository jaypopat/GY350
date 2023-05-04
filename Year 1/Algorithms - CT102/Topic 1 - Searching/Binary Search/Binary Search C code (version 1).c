// Binary Search Version 1
// CT102 January 2023

#include <stdio.h>

int main() {

    //sample array from lectures
    int arrA[] = {6, 12, 17, 21, 33, 34, 42, 59, 60, 93, 97};
    int size = 11;
    int item;

    int begSec, endSec, mid;
    begSec = 0;
    endSec = size - 1;
    mid = ((begSec + endSec) / 2);


    printf("\n Please enter item to find: ");
    scanf_s("%d", &item);

    //keep searching while something left to search and item not found

    while (begSec <= endSec && arrA[mid] != item) {

        if (item > arrA[mid]) {
            begSec = mid + 1;
        }

        else if (item < arrA[mid]) {
            endSec = mid - 1;
        }

        mid = (begSec + endSec) / 2;

    }

    // check if found 

    if (arrA[mid] == item) {
        printf("\n Found %d at position %d", item, mid);
    }
    else {
        printf("Item not in the array");
    }
    return(0);
}