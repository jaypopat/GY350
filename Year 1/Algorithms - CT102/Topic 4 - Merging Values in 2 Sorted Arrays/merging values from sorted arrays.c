#include <stdio.h>
#include <stdlib.h>

void merge(int [], int, int [], int);

// Function to merge the values in 2 integer arrays - not keeping duplicates
// Assumes data sorted in arrA[] and arrB[]

void merge (int arrA[], int sizeA, int arrB[], int sizeB) {

    int i, j, k;
    int sizeC;
		
    i = j = k = 0;
    sizeC = sizeA + sizeB;

    // declare arrC to be of size sizeC
    int *arrC;
    arrC = (int*) malloc(sizeC * sizeof(int));       
		
    while (i < sizeA && j < sizeB) {

        if (arrA[i] < arrB[j]) {
            arrC[k] = arrA[i];
 	    i++;
	    k++;
	}
	else if (arrB[j] < arrA[i]) {
	    arrC[k] = arrB[j];
	    j++;
	    k++;
	}
        else if (arrB[j] == arrA[i]) {     // can replce with else
	    arrC[k] = arrA[i];
	    i++;
	    j++;
	    k++;
	} 

    } //end while 


    //reached the end of one of the arrays at this point
    if (i == sizeA) {		// all of arrA written to arrC already

	while(j < sizeB) {
            arrC[k] = arrB[j];
	    j++;
	    k++;
	}
    } 

    else if (j == sizeB) {	//all of arrB written to arrC already

	while (i < sizeA) {
	    arrC[k] = arrA[i];
	    i++;
	    k++;
	}
    }
	
    sizeC = k; //correct value of sizeC

    // write arrC to file ... To Do

    // printing out the merged array
    printf("Merged array: ");
    for (i = 0; i < sizeC; i++) {
        printf("%d ", arrC[i]);
    }
    printf("\n");

    // freeing up memory allocated to arrC
    free(arrC);
}
