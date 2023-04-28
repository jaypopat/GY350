#include <stdio.h>
#include <stdlib.h>

void merge(int [], int, int [], int);

int main() {
// file pointers for 2 input files
FILE *fp1, *fp2;
int sizeA, sizeB;
int *arrA, *arrB;
int i;

// Open file1 in read mode
fp1 = fopen("fileA_forTesting.txt", "r");
if (fp1 == NULL) {
    printf("Unable to open file1\n");
    exit(1);
}

// Open file2 in read mode
fp2 = fopen("fileB_forTesting.txt", "r");
if (fp2 == NULL) {
    printf("Unable to open file2\n");
    exit(1);
}

// read size of array A from file1
fscanf(fp1, "%d", &sizeA);

// read size of array B from file2
fscanf(fp2, "%d", &sizeB);

// allocate memory for arrays A and B
arrA = (int*)malloc(sizeA * sizeof(int));
arrB = (int*)malloc(sizeB * sizeof(int));

// read data from file1 into array A
for (i = 0; i < sizeA; i++) {
    fscanf(fp1, "%d", &arrA[i]);
}

// read data from file2 into array B
for (i = 0; i < sizeB; i++) {
    fscanf(fp2, "%d", &arrB[i]);
}

// close file1 and file2
fclose(fp1);
fclose(fp2);

// merge the arrays
merge(arrA, sizeA, arrB, sizeB);

// free memory
free(arrA);
free(arrB);

return 0;

}

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

// write arrC to file 
FILE *fp;
fp = fopen("merged_array.txt", "w");
if (fp == NULL) {
    printf("Could not open file\n");
    exit(1);
}

for (i = 0; i < sizeC; i++) {
    fprintf(fp, "%d ", arrC[i]);
}

fclose(fp);
}