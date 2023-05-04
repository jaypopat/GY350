//Bubble Sort
void bubbleSort(int arrA[], int size) 
{
	int i, k, temp;

	for (k = 0; k < size; k++){
		for (i = 0; i < size - 1 - k; i++){
			if (arrA[i] > arrA[i+1]){
				//out of order so swap
				temp = arrA[i];
  				arrA[i]=arrA[i+1];
				arrA[i+1]=temp;
			} 
		} //end inner i for 
	} //end outer k for 
}