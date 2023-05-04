// CT102 Algorithms 2023
// countSort() with positive ints only and starting at 0 and passed maxVal


void countSort(int[], int, int);

// sort an array of integers using countSort algorithm from 0 to maxVal
void countSort(int arrA[], int size, int maxVal) 
{

	int i, value, count;
	int freqSize;

	freqSize = maxVal + 1;

	int *freq = (int*)malloc(freqSize * sizeof(int));   //create freq[]
	int *arrB = (int*)malloc(size * sizeof(int));       //create arrB[] same size as arrA[]

	// initialise freq[]
	for (i = 0; i < freqSize; i++) {
		freq[i] = 0;
	}

	// count frequencies
	for (i = 0; i < size; i++) {
		++freq[ arrA[i] ];
	}

	// get <= in freq[]
	for (i = 1; i < freqSize; i++) {
		freq[i] = freq[i] + freq[i - 1];
	}

	// place values from arrA into arrB; update freq[]
	for (i = 0; i < size; i++) {
		value = arrA[i];			//value to sort
		count = freq[value];		//<= freq of value
		arrB[count - 1] = value; 	//place value in arrB
		--freq[value];				//decrement freq[]
	} //next value in arrA
	
	//write back sorted values to arrA[] now that sorting finished
	for (i = 0; i < size; i++) {
		arrA[i] = arrB[i];
	}
        free(freq);
}