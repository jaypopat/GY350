
#include <stdio.h>
#include <stdlib.h>

void merge(int[], int, int, int);
void mergeSort(int[], int, int);



// mergeSort to sort values in an integer array arrA[]
// lb = 0 and ub = size - 1 for the first call
void mergeSort(int arrA[], int lb, int ub) {

	int mid;

	if (lb < ub) {
		mid =((lb + ub) / 2);
		mergeSort(arrA, lb, mid);
		mergeSort(arrA, mid + 1, ub);
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
		if (arrA[i] <= arrA[j]) {
			arrC[k] = arrA[i];
			i++;
		}
		else {
			arrC[k] = arrA[j];
			j++;
		}
		k++;
	} //end while

	// write out anything left in i part
	while (i <= mid) {
		arrC[k] = arrA[i];
		i++;
		k++;
	}
	// write out anything left in j part
	while (j <= ub) {
		arrC[k] = arrA[j];
		j++;
		k++;
	}

	//write back from arrC to arrA so correct values are in place for next merge
	i = lb;
	k = 0;
	while (i <= ub) {
		arrA[i] = arrC[k];
		i++;
		k++;
	}
	free(arrC);
}