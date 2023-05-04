// week6 Code : 2023
// Topic: Counting Integers
// Josephine Griffith for CT102 Algorithms

#include <stdio.h>
#include <stdlib.h>

void countPosInts(int[], int, int);
void countRange(int[], int, int, int);


// count the frequency of each integer in an array and print
void countPosInts(int arrA[], int size, int maxVal) {

	int i;
	int freqSize = maxVal + 1;
	int* freq = (int*)malloc(freqSize * sizeof(int));


	//initialise freq[]
	for (i = 0; i < freqSize; i++)
		freq[i] = 0;

	//count
	for (i = 0; i < size; i++) {
		++freq[arrA[i]];
	}

	//output
	for (i = 0; i < freqSize; i++)
		printf("\n Number of %d's is %d", i, freq[i]);

	free(freq);

}


// count the frequency of each integer in an array and print
void countRange(int arrA[], int size, int minVal, int maxVal) {

	int i;
	int freqSize = maxVal - minVal + 1;
	int* freq = (int*)malloc(freqSize * sizeof(int));


	//initialise freq[]
	for (i = 0; i < freqSize; i++)
		freq[i] = 0;

	//count
	for (i = 0; i < size; i++) {
		++freq[arrA[i] -  minVal];
	}

	//output
	for (i = 0; i < freqSize; i++)
		printf("\n Number of %d's is %d", minVal + i, freq[i]);

	free(freq);

}