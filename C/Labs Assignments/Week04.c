/*Write a C program that does the following:

1. Read in the age of any number of children in a family using a while loop. The user should be able to escape the while loop by entering an age <0. (20 marks)

2. Count how many children there are. Print result to the screen. (20 marks)

3. Calculate the average age of the children. Print this to the screen at the end. When calculating the average age, decimal point is important, e.g. age 1 = 10, age 2 =11, average = 10.5. (20 marks)

4. Keep track of the oldest and youngest child. Print the ages of each to the screen at the end. (20 marks)

5. If an age entered is 18 or greater. Print a message that the age entered is not for a child. Do not count this age when determining the average age, the maximum age or the number of children in the family. */
----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#include <stdio.h>
void main() {


	int num = 100000;
	int i = 0;
	int age = 0;
	int oldest = 0;
	int youngest = 1000;
	float sum_age = 0;
	float average_age = 0;
	int maximum_age = 0;
	int minimum_age = 1000;


	while (1) {
		printf("Enter the age of the child %d : ", i + 1); //inputs the age of the child till user inputs a negative integer --(loop)

		scanf_s("%d", &age);//scans the age and stores it in the variable age


		if (age < 0) {
			printf("While loop ending\n");
			break; // if value entered is a negative integer, the loop ends
		}

		if (age > 17)
			printf("Not a child \n"); //if value entered is over 18, it is excluded and not used in further calculations


		else
		{
			i++;
			sum_age = sum_age + age; //algorithm to find the sum of the ages(used to find average)

			if (age > maximum_age) {
				maximum_age = age; // this finds the maximum age in the inputs entered
			}
			if (age < minimum_age) {
				minimum_age = age; //// this finds the minimum age in the inputs entered
			}
		}

	}
	average_age = sum_age / (i); //algorithm to calculate the average - sum of the numbers divided by the number of ages 

	printf("There are %d children\n", i );
	printf("The average age is %.2f \n", average_age);
	printf("The maximum age is %d \n", maximum_age);
	printf("The minimum age is %d \n", minimum_age);
}
