// Linear Search Version 3: Assuming data in ascending sorted order
// CT102 January 2023

#include <stdio.h>
#include <stdbool.h>

int main() {

		//sample array from lectures
		int arrA[6] = {2, 6, 14, 29, 32, 49};
		int size = 6;
		int i, item;
		int position = -1;
		bool grThan = 0;


		printf("\n Please enter item to find: ");
		scanf_s("%d", &item);

		// keep searching while haven't found item 
		// or reached the end of the array
		// or found a value greater than item

		for (i = 0; i < size && position == -1 && grThan == 0; i++) {
			if (arrA[i] == item) {
				position = i;
			}
			else if (arrA[i] > item) {
				grThan = 1;
			}
			
		}

		if (position != -1) {
			printf("\n Found %d at position %d", item, position);
		}
		else {
			printf("Item not in the array");
		}
		return(0);
}