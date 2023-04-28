/*Write a C program that does the following:

1. Use a For loop to read in the Euro amount of daily sales for a clothes shop over 7 days from the user. Store the daily sales in an array of floats. (20 marks)

2. Calculate the total and average sales. Print both to the screen. (20 marks)

3. Now ask the user how many additional days of sales they would like to record. Use a While loop to read in the Euro amount of daily sales for the additional days. Store these in the same array that you created for task 1. Be careful not remove/replace any of the values from the first 7 days. (20 marks)

4. Update your calculation of the average and total sales based on the new additional days of sales. Print both to the screen. (20 marks)

5. Use a For loop to print out all sales values stored in your array. (20 marks)*/
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------
/*Name - Jay Popat
Student ID = 22346566
Date - 25/10/2022*/
#include <stdio.h>
void main() {
	//declaring and initialising variables
	float daily_sales[100];//Declaring the array by specifying the size
	float avg_sales = 0;
	float total_sales = 0;
	int additional_days;
	float additional_days_sales[1000];//Declaring the array by specifying the size
	int j = 0;
	int increment_day = 7;
	int k = 0;
	float updated_total_sales = 0;
	float updated_average_sales = 0;

	for (int i = 0; i < 7; i++) { //for loop to print the statement 7 times to receive 7 unique inputs
		printf("Enter sales total (Euro) for day %d:\n", i + 1);
		scanf_s("%f", &daily_sales[i]); //storing the input in the array (which has a size of 100 as declared)
		total_sales = total_sales + daily_sales[i];//formula to calculate total sales (initially)
	}
	avg_sales = total_sales / 7;//formula to calculate average sales (initially)
	printf("Total sales is %.2f\n", total_sales);
	printf("The average daily sales is %.2f\n", avg_sales); //printing avergae sales and total sales using the formulas above

	printf("How many additional days would you like to record:\n"); 
	scanf_s("%d", &additional_days);
	while (j < additional_days) { //while loop to calculate the additional days average and total sales
		printf("Enter sales total for day %d\n", increment_day + 1);
		scanf_s("%f", &additional_days_sales[j]);
		updated_total_sales = updated_total_sales + additional_days_sales[j];
		daily_sales[increment_day] = additional_days_sales[j];
		j++;
		increment_day++;
	}
	updated_total_sales = updated_total_sales + total_sales;//formula to calculate the updated sales
	updated_average_sales = updated_total_sales / increment_day;//formula to calculate the updated average

	//prints the updated total sales and average sales
	printf("Updated total sales is %.2f\n", updated_total_sales); 
	printf("Updated average daily sales is %.2f\n",updated_average_sales);
	
	for (int t = 0; t < increment_day; t++) {
		printf("Sales for day %d was %.2f euro.\n", t + 1, daily_sales[t]); //prints the sales for each day including the additional days 
	}
}
