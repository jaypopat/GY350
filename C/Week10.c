/*Write a C program that does the following:

1. Write a function called setPasscode. This function should generate a random passcode between 0 and N. ‘N’ is the largest possible passcode and is passed to the function as an integer. The function should return the random passcode. Call this function in main(). (20 marks)

2. Create a new function called ‘randomPasscodeSearch’ that accepts a passcode and max possible passcode as parameters. This function must generate random passcodes until the correct passcode if found. Print the passcode to the screen and the number of attempts it took to find it. Call this function in main(). (25 marks)

3. Create another function called ‘orderedPasscodeSearch’ that accepts a passcode and number of passcode digits as parameters. This function must check every code in order from 0 until the correct passcode if found, i.e. 0, 1, 2, … , N. Print the passcode to the screen and the number of attempts it took to find it. Call this function in main(). (25 marks)

4. Test your program by creating 4 passcodes. The first between [0, 9], second between [0, 99], third [0, 999], and fourth [0, 9999]. For each of these passcodes, use both ‘randomPasscodeSearch’ and ‘orderedPasscodeSearch’ functions. (20 marks)

5. Test your program once more by setting the passcode to the final 3 digits of your student ID. Again, use both ‘randomPasscodeSearch’ and ‘orderedPasscodeSearch’ functions to search for the passcode. Note, if your student ID contains ‘0’s, use next non-zero numbers.*/

//Jay Popat		22346566	29/11/2022
#include <stdio.h>
#include <stdlib.h>
#include <math.h>
#include <time.h>

//declaring the function
int setPasscode(int max_possible);
void randomPasscodeSearch(int passcode, int max_possible_passcode);
void orderedPasscodeSearch(int passcode,int size);

void main(){
	int size;
	
	for (int i = 0; i < 5; i++) { //for loop to run it 4 times
		if (i == 4) { // User is prompted for the last 3 digits of the student ID at the last iteration
			printf("Final Code: ");
			scanf_s("%d", &size);
			int passcode = setPasscode(size); //runs the generating a random number function and assigns the value to a variable
			// the variable's value is used as the parameter for the following random and ordered functions
			randomPasscodeSearch(passcode, size); 
			orderedPasscodeSearch(passcode, size);
			break;//breaks the loop after the last iteration
		}
		//else if its not the last iteration, this code is executed
		printf("\nMax pass-code size: \n");
		scanf_s("%d", &size);
		int passcode = setPasscode(size);
		randomPasscodeSearch(passcode, size); 
		orderedPasscodeSearch(passcode, size);
	}
}
int setPasscode(int max_possible){
	long seed = time(NULL); //this converts a time structure(special C type) to a long integer
	srand(seed);
	int randNum;
	randNum = rand() % max_possible;
	return randNum;
}
void randomPasscodeSearch(int passcode,int max_possible){
	int random_num = 0;
	int counter = 0;
	while (!(random_num==passcode)) {//counts the iterations which is printed later on if the random number generated is 
		counter++;
		random_num = rand() % max_possible + 1; 
		}
	printf("Random Search - Pass-code = %d after %d iterations", random_num, counter);//prints the random search -> iterations taken

}
void orderedPasscodeSearch(int passcode,int size){
	for (int i = 0; i < size; i++) {
		if (passcode == i) {
			printf("\nOrdered Search - Pass-code = %d after %d iterations\n", i, i + 1);//prints the ordered search -> iterations taken
			break;
		}
	}
}
