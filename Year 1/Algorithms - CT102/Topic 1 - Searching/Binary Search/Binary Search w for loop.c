// Binary Search Version 2: using for loop instead of while loop
// CT102 January 2023

#include <stdio.h>

int main() {

	//sample array from lectures
	int arrA[] = { 6, 12, 17, 21, 33, 34, 42, 59, 60, 93, 97 };
	int size = 11;
	int item;

	int begSec, endSec, mid;
	begSec = 0;
	endSec = size - 1;

	printf("\n Please enter item to find: ");
	scanf_s("%d", &item);

	//keep searching while something left to search and item not found

	for (mid = ((begSec + endSec) / 2); 
		begSec <= endSec && arrA[mid] != item; 
		mid = ((begSec + endSec) / 2)) {

		if (item > arrA[mid]) {
			begSec = mid + 1;
		}

		else if (item < arrA[mid]) {
			endSec = mid - 1;
		}
	}

	// check if found 

	if (arrA[mid] == item) {
		printf("\n Found %d at position %d", item, mid);
	}
	else {
		printf("Item not in the array");
	}
	return(0);
}
