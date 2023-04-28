#include <stdio.h>
#include <stdlib.h>

void countSortRange(int[], int, int, int);

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
}