#include <stdio.h>
#include "stdlib.h"
#include "stdbool.h"


int puzzle (int);
int test (int[], int);
int sequence (int);
int factorial(int);
int fib (int);
int search (int[], int, int);
bool checkSorted(int[], int);	
int binarySearch (int[], int, int, int);


int puzzle (int num) {

	printf("\n num is now %d", num);
	if (num <= 0) {
		return (0);
        }
	else {
		return (puzzle (num - 1));
	}
}

// Problem 2
int test(int arrA[], int size) {


	if (size == 1) {
		return (arrA[0]);
	}
	else {
		return (arrA[size - 1] + test (arrA, size - 1));
	}
}

// Problem 3 sequence()
int sequence(int n) {
    if (n == 0) {
        return 0;
    } else if (n == 1) {
        return 1;
    } else if (n % 2 == 0) {
        return sequence(n/2);
    } else {
        return sequence((n-1)/2) + sequence((n+1)/2);
    }
}



// Problem 4 factorial()
int factorial (int n) {

   	if (n <= 1) {
	    return (1);
        }
	else {
	    return ( n * factorial (n - 1) );
        }
}

// Problem 5
//fibonnaci sequence - recursive
int fib(int n)
{
	if (n <= 1) {
	    return (n);
        }
	else {
	    return (fib (n - 1) + fib (n - 2));
        }
}


// Problem 6 
// linear search, starting at size-1. returns -1 if not found
int search (int arrA[], int size, int item)
{
	if (size == 0) {
	    return (-1);
	}
	else if (arrA[size - 1] == item) {
	    return (size - 1);
        }
	else {
	    return (search (arrA, size - 1, item));
	}
}


// Problem 7 TO DO - check if sorted
bool checkSorted(int arrA[], int size) {
    if (size <= 1) {
        return true;
    } else {
        return (arrA[size - 1] >= arrA[size - 2]) && checkSorted(arrA, size - 1);
    }
}


// Problem 8
// must be called with begSec= 0 and endSec = size-1
int binarySearch (int arrA[], int begSec, int endSec, int item)
{
	int mid = ((begSec + endSec) / 2);

	if (begSec > endSec) {
	    return (-1);
	}
	else if (arrA[mid] == item) {
	    return (mid);
	}
	else if (item < arrA[mid]) {
	    return (binarySearch (arrA, begSec, mid - 1, item));
	}
	else {
	    return (binarySearch (arrA, mid + 1, endSec, item));
	}
}