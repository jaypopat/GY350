#include <stdio.h>
#include <stdlib.h>
#include <time.h>


void merge(int[], int, int, int);
void mergeSort(int[], int, int);
void quickSort (int[], int, int);
int partition (int[], int, int);
int partition2 (int[], int, int);
void swap (int *, int *);
void printArray(int A[], int size);

int recursiveCalls=0;
int quickSortComparisons =0;
int quickSortSwaps = 0;

int mergeSortComparisons =0;
int mergeSortSwaps = 0;


#define SIZE 5000

int main(){
    int i;
    clock_t start1, end1,start2,end2;
    int ints[SIZE],originalInts[SIZE];;

    FILE* ptr = fopen("C:\\Users\\email\\Desktop\\5000intsUnsorted.txt", "r");
    if (ptr == NULL)
    {
        printf("Error opening 5000intsUnsorted.txt\n");
        exit(1);
    }
    for (i = 0; i < SIZE; i++)
    {
        fscanf(ptr, "%d", &ints[i]);
    }
    for (i = 0; i < SIZE; i++)
    {
        fscanf(ptr, "%d", &ints[i]);
        originalInts[i] = ints[i]; // create a copy
    }
    fclose(ptr);
    start1 = clock();
    mergeSort(ints,0,SIZE-1);
    end1 = clock();
    //printArray(ints,SIZE);
    printf("Time taken: %.5f seconds\n", (double)(end1 - start1) / CLOCKS_PER_SEC);
    printf("\n");
    printf("%d merge sort swaps\n",mergeSortSwaps);
    printf("%d ,merge sort comparisons\n",mergeSortComparisons);

    start2 = clock();
    quickSort(originalInts,0,SIZE-1);
    end2 = clock();
    //printArray(originalInts,SIZE);
    printf("\n\nTime taken: %.5f seconds\n", (double)(end2 - start2) / CLOCKS_PER_SEC);
    printf("\n");
    printf("%d quick sort swaps\n",quickSortSwaps);
    printf("%d quick sort comparisons\n",quickSortComparisons);
    printf("Overall Recursive Calls: %d\n",recursiveCalls);
}
void printArray(int A[], int size)
{
    int i;
    for (i = 0; i < size; i++)
        printf("%d ", A[i]);
    printf("\n");
}
// mergeSort to sort values in an integer array arrA[]
// lb = 0 and ub = size - 1 for the first call
void mergeSort(int arrA[], int lb, int ub) {

    int mid;

    if (lb < ub) {
        mid =((lb + ub) / 2);
        mergeSort(arrA, lb, mid);
        mergeSort(arrA, mid + 1, ub);
        recursiveCalls++;
        merge(arrA, lb, mid, ub);
    }
}


// merge two sorted portions of an integer array arrA[]:
// portions are lb to mid and mid+1 to ub
void merge(int arrA[], int lb, int mid, int ub) {

    int i, j, k;
    int size = ub - lb + 1;
    int* arrC = (int*) malloc(size * sizeof(int)); //create arrC[] to be of size needed for current merge


    i = lb;
    j = mid + 1;
    k = 0;

    while (i <= mid && j <= ub) {
        mergeSortComparisons++;
        if (arrA[i] <= arrA[j]) {
            arrC[k] = arrA[i];
            i++;
        }
        else {
            arrC[k] = arrA[j];
            j++;
        }
        k++;
        mergeSortSwaps++;
    } //end while

    // write out anything left in i part
    while (i <= mid) {
        arrC[k] = arrA[i];
        i++;
        k++;
        mergeSortSwaps++;
    }
    // write out anything left in j part
    while (j <= ub) {
        arrC[k] = arrA[j];
        j++;
        k++;
        mergeSortSwaps++;
    }

    //write back from arrC to arrA so correct values are in place for next merge
    i = lb;
    k = 0;
    while (i <= ub) {
        arrA[i] = arrC[k];
        i++;
        k++;
        mergeSortSwaps++;
    }
    free(arrC);
}

// quicksort with 2 partitions

// To Do:
// 1. Include libraries
// 2. Include main() and call quickSort function
// 3. Add code to time, count comparisons and swaps

void quickSort(int arrA[], int startval, int endval) {

    if ( (endval - startval) < 1) {
        return;
    }
    else {
        int k = partition2(arrA, startval, endval);
        //now sort the two sub-arrays
        quickSort(arrA, startval, k - 1);
        recursiveCalls++;//left partition
        quickSort(arrA, k + 1, endval);   //right partition
        recursiveCalls++;
    }
}

// partition using nested while loops


// better version of partition .. no nested loop
// pivot at startval as before
int partition2 (int arrA[], int startval, int endval)
{
    int k;
    int pivot = arrA[startval];
    int i = startval;

    for (k = startval + 1; k <= endval; k++) { // k keeps incrementing to the end
        if (arrA[k] <= pivot)
        {
            quickSortComparisons++;
            i++;   // i only increments when there is a new value to add to the <= portion
            if (i != k) {
                swap (&arrA[i], &arrA[k]);
                quickSortComparisons++;
                quickSortSwaps++;
            }
        }
    }
    swap (&arrA[i], &arrA[startval]);  // put pivot in correct location i
    quickSortSwaps++;
    return(i);
}

// call with  ... swap(&arrA[i], &arrA[j]) to swap the values at positions i and j
void swap(int* a, int* b)
{
    //++cnt_swap_calls;
    int temp = *a;
    *a = *b;
    *b = temp;
}