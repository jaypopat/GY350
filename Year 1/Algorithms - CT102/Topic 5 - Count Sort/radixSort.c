#include <stdio.h>
#include <stdlib.h>

void countSortRadix(int[], int, int);
void radixSort(int[], int);



// sort an array of positive integers of size
void radixSort(int arrA[], int size) {

	int exp, i;

	//find maxVal to get number of digits
	int maxVal = findMax(arrA, size);


	// call countSortRadix for each digit: exp = 1 for 1's, exp = 10 for 10's, etc
	for (exp = 1; maxVal / exp > 0; exp *= 10) {
		countSortRadix(arrA, size, exp);
	}

	//print out (or add code to write to file)
	for (i = 0; i < size; i++) {
		printf("\n arrA[%d] is %d", i, arrA[i]);
	}
}

// sort an array of positive integers for radix Sort using countSort
// range of values is 0-9
void countSortRadix(int arrA[], int size, int exp) {

	int i, value, count;
	int freqSize = 10;
	int freq[10];

	int* arrB = (int*)malloc(size * sizeof(int));       //create arrB[] same size as arrA[]

	//initialise freq[]
	for (i = 0; i < freqSize; i++) {
		freq[i] = 0;
	}

	//count using exp to do 1's, 10's, 100's depending on exp 
	for (i = 0; i < size; i++) {
		++freq[(arrA[i] / exp) % 10];   

	}
	//get <= in freq[]
	for (i = 1; i < freqSize; i++) {
		freq[i] = freq[i] + freq[i - 1];
	}

	// place values from arrA into arrB; update freq[] - go in opposite order  - start at size-1
	for (i = size - 1; i >= 0; i--) {
		arrB[freq[(arrA[i] / exp) % 10] - 1] = arrA[i];
		--freq[(arrA[i] / exp) % 10];
	} //next value in arrA

	printf("\n for exp %d values are:", exp);
	//write back sorted values to arrA[] now that sorting of this digit is finished
	for (i = 0; i < size; i++) {
		arrA[i] = arrB[i];
	}
	free(arrB);
}