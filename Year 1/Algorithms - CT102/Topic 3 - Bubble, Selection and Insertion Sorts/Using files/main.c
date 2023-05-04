// CT102 Algorithms
// Reading integers from a file in to an array
// Writing integers from an array in to a file


#include <stdio.h>
#include <stdlib.h>

#define ARRAY_LIMIT 1500


// testing functions in main() with data read from file
int main (void) {

	int arrA[ARRAY_LIMIT];
	
	int i, size;
	
	FILE *fptr, *outfp;

	fopen_s(&fptr, "C:/Users/1000Ints.txt", "r");
	
	if (fptr == NULL) {
		puts("Error Opening File \n Exiting ........");
		return(1);
	}
	else {
		printf("Opening File ....\n");
		
		for (i = 0; !feof(fptr) && i < ARRAY_LIMIT; i++) {
			fscanf_s(fptr, "%d", &arrA[i]);
		}
	
		fclose(fptr);
		size = i;

		// do some work ... call you functions


		//write array data TO file and print out to check

		fopen_s(&outfp, "C:/Users/outData.txt", "w");

		if (outfp == NULL) {
			puts("Error Opening File \n Exiting ........");
			return(1);
		}
		else {
			printf("\n Printing out values in array and writing to file:: ");
			for (i = 0; i < size; i++) {
				fprintf(outfp, "%d\n", arrA[i]);
				printf("\n %d", arrA[i]);
			}

			fclose(outfp);
		}
		

	} //close else
	return(1);
}