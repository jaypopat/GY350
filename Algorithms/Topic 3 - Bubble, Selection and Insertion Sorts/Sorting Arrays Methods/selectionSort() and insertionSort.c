#include <stdio.h>
#include <stdlib.h>

void insertionSort(int[], int);
void selectionSort(int[], int);

// testing functions in main() with small sample data
int main() {
    int n = 10;
    int A[] = {2, 4, 6, 5, 10, 1, 3, 9, 7, 8};
    int b[] = {2, 4, 6, 6, 10, 1, 3, 9, 8, 8};

    selectionSort(A, n);
    printf("\n Printing out Sorted array (selection sort):\t");
    for(int i = 0; i < n; i++) {
        printf("%d\t", A[i]);
    }

    insertionSort(b, n);
    printf("\n Printing out Sorted array (insertion sort):\t");
    for(int i = 0; i < n; i++){
        printf("%d\t", b[i]);
    }

    return 0;
}

// Selection Sort: integer array arrA [] of size
void selectionSort(int arrA[], int size) {
    int i, j, min, temp;

    for (i = 0; i < size - 1; i++) {
        min = i;
        //find next smallest
        for (j = min + 1; j < size; j++) {
            if (arrA[min] > arrA[j]) {
                min = j;
            }
        } // end j for loop

        //swap values at locations i and min, if i != min
        if (i != min) {
            temp = arrA[i];
            arrA[i] = arrA[min];
            arrA[min] = temp;
        }
    } //end outer i for loop
}

// Insertion Sort: integer array arrA [] of size
void insertionSort(int arrA[], int size) {
    int i, j, curr;

    for(i = 1; i < size; i++) {
        curr  = arrA[i];

        for(j = i - 1; j >= 0 && curr < arrA[j]; j--) {   //compare
            //make room ...
            arrA[j + 1] = arrA[j];
        }

        if (i != j + 1) // if not at the correct position already
            arrA[j + 1] = curr;

    } // end outer i for
} //return
