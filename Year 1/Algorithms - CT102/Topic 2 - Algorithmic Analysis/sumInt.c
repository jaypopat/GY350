#include <stdio.h>
int sumIntArray(int[], int);

int sumIntArray(int arrA[], int size) {

	int i, sum;
	sum = 0;
	for(i = 0; i < size; i++)
		sum = sum + arrA[i];

	return(sum);
}