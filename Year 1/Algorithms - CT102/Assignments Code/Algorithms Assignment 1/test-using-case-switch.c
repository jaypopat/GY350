#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void insertionSort(int arrA[], int size);
void selectionSort(int arrA[], int size);
void countSortRange(int[], int, int, int);
void bubbleSort(int arrA[], int size);

#define SIZE 5000

int main()
{
    int i;
    clock_t start, end;
    int sortedNumsArray[SIZE], unsortedNumsArray[SIZE], originalUnsortedNumsArray[SIZE];

    // read first file into sortedNumsArray
    FILE* ptr = fopen("C:\\Users\\email\\Desktop\\GY350\\Coding in C\\C-Lion Projects\\L19\\sorted.txt", "r");
    if (ptr == NULL)
    {
        printf("Error opening sortedNums.txt\n");
        exit(1);
    }
    for (i = 0; i < SIZE; i++)
    {
        fscanf(ptr, "%d", &sortedNumsArray[i]);
    }
    fclose(ptr);

    // read second file into Unsorted Array
    ptr = fopen("C:\\Users\\email\\Desktop\\GY350\\Coding in C\\C-Lion Projects\\L19\\unsorted.txt", "r");
    if (ptr == NULL)
    {
        printf("Error opening unsortedNums.txt\n");
        exit(1);
    }
    for (i = 0; i < SIZE; i++)
    {
        fscanf(ptr, "%d", &unsortedNumsArray[i]);
        originalUnsortedNumsArray[i] = unsortedNumsArray[i]; // create a copy of the unsorted array
    }
    fclose(ptr);

    int sortingMethodInput;
    do {
        printf("Choose the sorting technique:\n");
        printf("1. Insertion Sort\n");
        printf("2. Selection Sort\n");
        printf("3. Count Sort\n");
        printf("4. Bubble Sort\n");
        printf("-1. Exit\n");

        scanf("%d", &sortingMethodInput);

        switch(sortingMethodInput)
        {
            case 1:
                start = clock();
                insertionSort(sortedNumsArray, SIZE);
                end = clock();
                printf("Sorted Array sorted using Insertion Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", sortedNumsArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");

                start = clock();
                int insertionSortArray[SIZE];
                for (i = 0; i < SIZE; i++)
                {
                    insertionSortArray[i] = originalUnsortedNumsArray[i];
                }
                insertionSort(insertionSortArray, SIZE);
                end = clock();
                printf("Unsorted Array sorted using Insertion Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", insertionSortArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");
                break;

            case 2:
                start = clock();
                selectionSort(sortedNumsArray, SIZE);
                end = clock();
                printf("Sorted Array sorted using Selection Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", sortedNumsArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");

                start = clock();
                int selectionSortArray[SIZE];
                for (i = 0; i < SIZE; i++)
                {
                    selectionSortArray[i] = originalUnsortedNumsArray[i];
                }
                selectionSort(selectionSortArray, SIZE);
                end = clock();
                printf("Unsorted Array sorted using Selection Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", selectionSortArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");
                break;

            case 3:
                start = clock();
                countSortRange(sortedNumsArray, SIZE, -100,100);
                end = clock();
                printf("Sorted Array sorted using Count Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", sortedNumsArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");


                start = clock();
                int countSortArray[SIZE];
                for (i = 0; i < SIZE; i++)
                {
                    countSortArray[i] = originalUnsortedNumsArray[i];
                }
                countSortRange(countSortArray, SIZE, 0, 9999);
                end = clock();
                printf("Unsorted Array sorted using Count Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", countSortArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");
                break;

            case 4:
                start = clock();
                bubbleSort(sortedNumsArray, SIZE);
                end = clock();
                printf("Sorted Array sorted using Bubble Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", sortedNumsArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");

                start = clock();
                int bubbleSortArray[SIZE];
                for (i = 0; i < SIZE; i++)
                {
                    bubbleSortArray[i] = originalUnsortedNumsArray[i];
                }
                bubbleSort(bubbleSortArray, SIZE);
                end = clock();
                printf("Unsorted Array sorted using Bubble Sort:\n");
                for (i = 0; i < SIZE; i++) {
                    printf("%d ", bubbleSortArray[i]);
                }
                printf("\n");
                printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
                printf("\n");
                break;
        }
    }while (sortingMethodInput!=-1);
    return 0;
}
// sort an array of integers using countSort algorithm from minVal to maxVal
void countSortRange(int arrA[], int size, int minVal, int maxVal)
{

    int i, value, count;
    int freqSize = maxVal - minVal + 1;

    int* freq = (int*)malloc(freqSize * sizeof(int));   //create freq[]
    int* arrB = (int*)malloc(size * sizeof(int));       //create arrB[] same size as arrA[]

    // initialise freq[]
    for (i = 0; i < freqSize; i++) {
        freq[i] = 0;
    }

    // count frequencies
    for (i = 0; i < size; i++) {
        ++freq[arrA[i] - minVal];
    }

    // get <= in freq[]
    for (i = 1; i < freqSize; i++) {
        freq[i] = freq[i] + freq[i - 1];
    }

    // place values from arrA[] into arrB[]; update freq[]
    for (i = 0; i < size; i++) {
        value = arrA[i];			        //value to sort
        count = freq[value - minVal];		//<= freq of value
        arrB[count - 1] = value; 	        //place value in arrB
        --freq[value - minVal];				//decrement freq[]
    } //get next value in arrA[]

    //write back sorted values to arrA[] now that sorting finished
    for (i = 0; i < size; i++) {
        arrA[i] = arrB[i];
    }
    free(freq);
    puts("No comparisons or swaps take place in count sort");
}
void bubbleSort(int arrA[], int size)
{
    int i, k, temp;
    int numSwaps = 0;
    int numComparisons = 0;

    for (k = 0; k < size; k++)
    {
        for (i = 0; i < size - 1 - k; i++)
        {
            numComparisons++;
            if (arrA[i] > arrA[i+1]){
                //out of order so swap
                temp = arrA[i];
                arrA[i]=arrA[i+1];
                arrA[i+1]=temp;
                numSwaps++;
            }
        } //end inner i for
    } //end outer k for
    printf("Bubble Sort: %d swaps, %d comparisons\n", numSwaps, numComparisons);

}
void selectionSort(int arrA[], int size)
{

    int i, j, min, temp;
    int numSwaps = 0;
    int numComparisons = 0;

    for (i = 0; i < size - 1; i++)
    {
        min = i;
        //find next smallest
        for (j = min + 1; j < size; j++)
        {
            numComparisons++;
            if (arrA[min] > arrA[j]) {
                min = j;
            }
        } // end j for loop

        //swap values at locations i and min, if i != min
        if (i != min)
        {
            temp = arrA[i];
            arrA[i] = arrA[min];
            arrA[min] = temp;
            numSwaps++;
        }
    } //end outer i for loop
    printf("Selection Sort: %d swaps, %d comparisons\n", numSwaps, numComparisons);

}


void insertionSort(int arrA[], int size)
{
    int i, j, curr;
    int numSwaps = 0;
    int numComparisons = 0;

    for(i = 1; i < size; i++)
    {
        curr  = arrA[i];

        for(j = i - 1; j >= 0; j--)
        {
            numComparisons++;
            if (curr < arrA[j])
            {   //compare
                //make room ...
                arrA[j + 1] = arrA[j];
                numSwaps++;
            }
            else
                break;
        }

        if (i != j + 1)
        {
            // if not at the correct position already
            arrA[j + 1] = curr;
        }

    } // end outer i for

    printf("Insertion Sort: %d swaps, %d comparisons\n", numSwaps, numComparisons);
}

    