#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void insertionSort(int arrA[], int size);
void selectionSort(int arrA[], int size);
void countSort(int arrA[], int size, int maxVal);
void bubbleSort(int arrA[], int size);


#define SIZE 5000

int main()
{

    int i;clock_t start, end;
    int sortedNumsArray[SIZE], unsortedNumsArray[SIZE];
    int maxVal = 9999; // assuming all numbers in file are less than 10000

    // read first file into sortedNumsArray
    FILE* ptr = fopen("C:\\Users\\email\\Desktop\\GY350\\Coding in C\\C-Lion Projects\\Labs Week 18\\sortedNums.txt", "r");
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
    ptr = fopen("C:\\Users\\email\\Desktop\\GY350\\Coding in C\\C-Lion Projects\\Labs Week 18\\unsortedNums.txt", "r");
    if (ptr == NULL)
    {
        printf("Error opening unsortedNums.txt\n");
        exit(1);
    }
    for (i = 0; i < SIZE; i++)
    {
        fscanf(ptr, "%d", &unsortedNumsArray[i]);
    }
    fclose(ptr);


    // sort Sorted Array using insertion sort and print sorted array
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

    // sort Unsorted Array using insertion sort and print sorted array
    start = clock();
    insertionSort(unsortedNumsArray, SIZE);
    end = clock();
    printf("Unsorted Array sorted using Insertion Sort:\n");
    for (i = 0; i < SIZE; i++) {
        printf("%d ", unsortedNumsArray[i]);
    }
    printf("\n");
    printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("\n");

    // sort Sorted Array using selection sort and print sorted array
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

    // sort Unsorted Array using selection sort and print sorted array
    start = clock();
    selectionSort(unsortedNumsArray, SIZE);
    end = clock();
    printf("Unsorted Array sorted using Selection Sort:\n");
    for (i = 0; i < SIZE; i++) {
        printf("%d ", unsortedNumsArray[i]);
    }
    printf("\n");
    printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("\n");

    // sort Sorted Array using count sort and print sorted array
    start = clock();
    countSort(sortedNumsArray, SIZE, maxVal);
    end = clock();
    printf("Sorted Array sorted using Count Sort:\n");
    for (i = 0; i < SIZE; i++) {
        printf("%d ", sortedNumsArray[i]);
    }
    printf("\n");
    printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("\n");

    // sort Unsorted Array using count sort and print sorted array
    start = clock();
    countSort(unsortedNumsArray, SIZE, maxVal);
    end = clock();
    printf("Unsorted Array sorted using Count Sort:\n");
    for (i = 0; i < SIZE; i++) {
        printf("%d ", unsortedNumsArray[i]);
    }
    printf("\n");
    printf("Time taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("\n");

    // sort Sorted Array using bubble sort and print sorted array
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


    // sort Unsorted Array using bubble

    // sort Unsorted Array using bubble sort and print sorted array
    start = clock();
    bubbleSort(unsortedNumsArray, SIZE);
    end = clock();
    printf("Unsorted Array sorted using Bubble Sort:\n");
    for (i = 0; i < SIZE; i++) {
        printf("%d ", unsortedNumsArray[i]);
    }
    printf("\nTime taken: %.5f seconds\n", (double)(end - start) / CLOCKS_PER_SEC);
    printf("\n");


    return 0;
}


// sort an array of integers using countSort algorithm from 0 to maxVal
void countSort(int arrA[], int size, int maxVal)
{

    int i, value, count;
    int freqSize;
    int numSwaps = 0;
    int numComparisons = 0;

    freqSize = maxVal + 1;

    int *freq = (int*)malloc(freqSize * sizeof(int));   //create freq[]
    int *arrB = (int*)malloc(size * sizeof(int));       //create arrB[] same size as arrA[]

    // initialise freq[]
    for (i = 0; i < freqSize; i++)
    {
        freq[i] = 0;
    }

    // count frequencies
    for (i = 0; i < size; i++)
    {
        ++freq[ arrA[i] ];
    }

    // get <= in freq[]
    for (i = 1; i < freqSize; i++)
    {
        freq[i] = freq[i] + freq[i - 1];
    }

    // place values from arrA into arrB; update freq[]
    for (i = 0; i < size; i++)
    {
        value = arrA[i];			//value to sort
        count = freq[value];		//<= freq of value
        arrB[count - 1] = value; 	//place value in arrB
        --freq[value];				//decrement freq[]
        numSwaps++;
        numComparisons++;
    } //next value in arrA

    //write back sorted values to arrA[] now that sorting finished
    for (i = 0; i < size; i++)
    {
        arrA[i] = arrB[i];
    }
    free(freq);
    printf("Count Sort: %d swaps, %d comparisons\n", numSwaps, numComparisons);

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