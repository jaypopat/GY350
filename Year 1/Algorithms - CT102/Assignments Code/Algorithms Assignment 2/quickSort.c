// CT102 Algorithms 2023
// quicksort with 2 partitions

// To Do: 
// 1. Include libraries
// 2. Include main() and call quickSort function
// 3. Add code to time, count comparisons and swaps



void quickSort (int[], int, int);
int partition (int[], int, int);
int partition2 (int[], int, int);

void swap (int *, int *);


void quickSort(int arrA[], int startval, int endval) {
	
	if ( (endval - startval) < 1) {
		return;
	}
	else {
		int k = partition2(arrA, startval, endval); 
		//now sort the two sub-arrays
		quickSort(arrA, startval, k - 1);  //left partition
		quickSort(arrA, k + 1, endval);   //right partition
	}
}

// partition using nested while loops
int partition (int arrA[], int startval, int endval)
{
	int i = startval + 1;
	int k = endval;
	int pivot = arrA[startval];

	while (k >= i) {		
		while (arrA[i] <= pivot && i <= k) {
			i++;
		}
		while (arrA[k] > pivot && k >= i) {
			k--;
		}    
	    if (k > i){ //swap values at k and i
			swap(&arrA[i], &arrA[k]);
		}       
	} 
	//out of this loop when k >= i not true
    swap(&arrA[startval], &arrA[k]);

	return(k);
}

// better version of partition .. no nested loop
// pivot at startval as before
int partition2 (int arrA[], int startval, int endval)
{
	int k;
	int pivot = arrA[startval];
	int i = startval;
  
	for (k = startval + 1; k <= endval; k++) { // k keeps incrementing to the end
        if (arrA[k] <= pivot) {  
            i++;   // i only increments when there is a new value to add to the <= portion
            if (i != k) {  
				swap (&arrA[i], &arrA[k]);
			}
        }
    }
	swap (&arrA[i], &arrA[startval]);  // put pivot in correct location i
	return(i);
}

// call with  ... swap(&arrA[i], &arrA[j]) to swap the values at positions i and j
void swap(int* a, int* b)
{
    //++cnt_swap_calls;
	int temp = *a;
    *a = *b;
    *b = temp;
}