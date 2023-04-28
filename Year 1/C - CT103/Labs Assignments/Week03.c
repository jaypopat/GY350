/*Write a C program that does the following:

1. Requests the user to enter the total weekly sales (Euro) for a shoe shop on 5 separate weeks. These should be stored using 5 separate floats.

2. Calculate the average weekly sales over the 5 weeks, store this as a float, and print the result to the screen.

3. If the average weekly sales is less than €13,000, print a message to the user saying that sales are low. If the sales are between €13,000 and €20,000, print a message saying the sales are normal. If the sales are over €15,000, print a message saying that the sales are high.
4. The average number of weekly customers is 230. Calculate the average spend per customer (Euro). Store this value as a float. Print the result to the screen.*/
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
#include <stdio.h>
void main() {
	float week1 = 0;
	float week2 = 0;
	float week3 = 0;
	float week4 = 0;
	float week5 = 0;
	float avg_sales = 0;
	float week = 0;
	float avg_customer_spent = 0;

	printf("Enter total sales for week 1: ");
	scanf_s("%f", &week1); //reads input for week 1 and stores it

	printf("Enter total sales for week 2: ");
	scanf_s("%f", &week2);//reads input for week 2 and stores it

	printf("Enter total sales for week 3: ");
	scanf_s("%f", &week3);//reads input for week 3 and stores it

	printf("Enter total sales for week 4: ");
	scanf_s("%f", &week4);//reads input for week 4 and stores it

	printf("Enter total sales for week 5: ");
	scanf_s("%f", &week5);//reads input for week 5 and stores it

	avg_sales = (week1 + week2 + week3 + week4 + week5) / 5; 
	printf("The average weekly sales is: %.2f\n", avg_sales);

	if (13000 <= avg_sales && avg_sales <= 20000) {
		printf("Sales are normal\n"); 
	}

	else if (avg_sales > 20000) {
		printf("Sales are high\n");
	}

	else {
		printf("Sales are low\n");
	}

	avg_customer_spent = (avg_sales / 230);
	printf("The average spend per customer is : %.2f\n", avg_customer_spent); //output calculated using the algorithm above

}
