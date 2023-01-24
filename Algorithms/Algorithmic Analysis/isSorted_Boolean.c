// CT102 January 2023
// Topic: Functions; Algorithm Analysis
#include <bool.h>
#include <stdio.h>

bool isSorted(int arr[], int size);

// check if data in an integer array is in ascending sorted order
// return True or False
bool isSorted(int arrA[], int size)
{
	int i;
	bool sorted = true;
	
	for(i = 0; i < size - 1 && sorted; i++){
		if(arrA[i] > arrA[i+1]){
			sorted = false;
		}
	}
	return(sorted);
}


// variations which are incorrect - can you see why?

bool isSorted(int arrA[], int size)
{
	int i;
	bool sorted = true;
	
	for(i = 0; i < size && sorted; i++){
		if(arrA[i] > arrA[i+1]){
			sorted = false;
		}
	}
	return(sorted);
}

bool isSorted(int arrA[], int size)
{
	int i;
	bool sorted = true;
	
	for(i = 1; i < size - 1 && sorted; i++){
		if(arrA[0] > arrA[i]){
			sorted = false;
		}
	}
	return(sorted);
}