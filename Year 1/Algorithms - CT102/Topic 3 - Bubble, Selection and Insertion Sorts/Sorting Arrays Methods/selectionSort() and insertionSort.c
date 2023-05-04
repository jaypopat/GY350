#include <stdio.h>
#include <stdlib.h>

int comparisons = 0;
int swaps = 0;

void insertionSort(int[], int);
void selectionSort(int[], int);


int main() {

    int n = 10;
    int A[] = {2, 4, 6, 5, 10, 1, 3, 9, 7, 8};
    int b[] = {2, 4, 6, 6, 10, 1, 3, 9, 8, 8};

    selectionSort(A, n);
    printf("\nPrinting out Sorted array (Selection Sort):\t");
    for(int i = 0; i < n; i++)
        printf("%d  ", A[i]);
    printf("\nNumber of comparisons: %d\n", comparisons);
    printf("Number of swaps: %d\n", swaps);

    comparisons = 0;
    swaps = 0;

    insertionSort(b, n);
    printf("\nPrinting out Sorted array (Insertion Sort):\t");
    for(int i = 0; i < n; i++){
        printf("%d  ", b[i]);
    }
    printf("\nNumber of comparisons: %d\n", comparisons);
    printf("Number of swaps: %d\n", swaps);

    return 0;
}


// Selection Sort: integer array arrA [] of size
void selectionSort(int arrA[], int size) {

    int i, j, min, temp;

    for (i = 0; i < size - 1; i++) {
        min = i;
        //find next smallest
        for (j = min + 1; j < size; j++) {
            comparisons++;
            if (arrA[min] > arrA[j]) {
                min = j; // SWAP VALUES AT I AND J
            }
        } // end j for loop

        //swap values at locations i and min, if i != min
        if (i != min) {
            swaps++;
            temp = arrA[i];
            arrA[i] = arrA[min];
            arrA[min] = temp;
        }
    } //end outer i for loop
}


// Insertion Sort: integer array arrA [] of size
void insertionSort(int arrA[], int size)
{
    int i, j, curr;

    for(i = 1; i < size; i++) {
        curr  = arrA[i];

        for(j = i - 1; j >= 0; j--) {
            comparisons++;
            if (curr < arrA[j]) {   //compare
                //make room ...
                swaps++;
                arrA[j + 1] = arrA[j];
            }
            else
                break;
        }

        if (i != j + 1) // if not at the correct position already
            arrA[j + 1] = curr;

    } // end outer i for

} //return
