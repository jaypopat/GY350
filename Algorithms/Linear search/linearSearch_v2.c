// Linear Search Version 2
// CT102 January 2023

#include <stdio.h>

int main() {

		//sample array from lectures
		int arrA[6] = { 12, 6, 4, 29, 2, 9 };
		int size = 6;
		int i, item;
		int position = -1;


		printf("\n Please enter item to find: ");
		scanf_s("%d", &item);

		//keep searching while haven't found item or reached the end of the array

		for (i = 0; i < size && position == -1; i++) {
			if (arrA[i] == item) {
				position = i;
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