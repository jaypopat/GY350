/*Write a C program that does the following:

1. Create an array of strings to store the names of 5 car manufacturers. (20 marks)

2. Use a loop to read in the car manufacturer names typed by the user. Store these in the previously created array of strings. (30 marks)

3. Next, search through each of the letters in each of the manufacturer names and check if the letter is uppercase. Print all uppercase letters to the screen. (30 marks)

4. Finally, convert all letters in the array of strings to uppercase. Display the final list of car manufacturers to the screen. (20 marks)*/
-------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Name - Jay Popat		22346566			15/11/2022
#include <stdio.h> 
#include <ctype.h> 
#include <string.h>

void main() {
	char car_manufacturers[5][100];
	char num_car[5];

	printf("Enter car manufacturers\n"); //asks user for 5 inputs - (strings)
	for (int i = 0; i < 5; i++) { //for loop to scan input into the array car_manufacturers
		scanf_s("%s", &car_manufacturers[i],100);//stores it in the array
		num_car[i] = strlen(car_manufacturers[i]);//determines the length of the string for the car manufacturer string
	}
	printf("Checking for uppercase letters\n");
	for (int i = 0; i < 5; i++) { //loops 5 times accounting for the 5 car manufactureres
		for (int j = 0; j < num_car[i]; j++) {
			if (isupper(car_manufacturers[i][j])) {
				printf("%c\n", car_manufacturers[i][j]);//prints the characters in the strings which are in upper case
			}
		}
	}
	printf("New List\n");
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < num_car[i]; j++) {
			if (islower(car_manufacturers[i][j])) {
				car_manufacturers[i][j] = toupper(car_manufacturers[i][j]);//converts lower case to upper case
			}
		}
		printf("%s\n", car_manufacturers[i]);//prints updated strings
	}
}
