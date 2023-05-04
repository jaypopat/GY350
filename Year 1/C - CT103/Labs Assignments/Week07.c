/*Write a C program that does the following:

1. Ask the user which shape they would like to calculate the area of: triangle, rectangle, circle, or trapezium. The user’s response should be stored as a string, e.g. ‘circle’. Note: See image for instructions on calculating area of each shape. (33 marks)

2. The user should then enter the required parameters to calculate the area of the chosen shape. Display the answer to the screen. Your programme should only calculate the area for correct, i.e. positive, shape dimensions, e.g., when width and height are > 0 for a rectangle. (33 marks)

3. The user should be asked to enter another shape or type ‘Done’ to end. (33 marks)*/
//------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Jay Popat 22346566 08/11/2022
#include <stdio.h>
#include <string.h>

void main() {
	char shape[100];
	int i = 0;
	const float pi = 3.14;
	int height = 0;
	int width = 0;
	float area = 0;
	int top_basewidth = 0;
	int radius = 0;
	
	puts("\nArea Calculator - Shape choices are:\n------------------------------\nTriangle, Rectangle, Circle, Trapezium.'Done' to end\n------------------------------\nchoice: (The absolute values of the dimensions are used for the calculation...\n");//Since negative values cant be processed in this program, if the dimensions entered are negative, it is converted back to a positive dimension by taking the absolute value
	gets(shape);//scans the string inputted by user
	
	while (1) { //this causes the loop to run infinitely until a certain string - "Done" is inputted
		

		if (strcmp(shape, "Done") == 0) { //used to search if the input is the same as the Exit key
			printf("loop ending");
			break; //loop breaks when "Done" is entered by the user
		}

		else if (strcmp(shape, "Triangle") == 0) {//used to search if the input is the same as the shape name
			printf("Enter height:");
			scanf_s("%d", &height);
			printf("Enter base width:");
			scanf_s("%d", &width);
			//height and width are multiplied by -1 to obtain the absolute
			if (height<= 0) { height = height * -1; }
			if (width <= 0) { width = width * -1; }
			area = (0.5) * (width * height);//formula to calculate triangle area
			printf("Area = %0.2f", area);
		}

		else if (strcmp(shape, "Rectangle") == 0) {//used to search if the input is the same as the shape name
			printf("Enter height:");
			scanf_s("%d", &height);
			printf("Enter width:");
			scanf_s("%d", &width);
			//height and width are multiplied by -1 to obtain the absolute
			if (height <= 0) { height = height * -1; }
			if (width <= 0) { width = width * -1; }
			area = (width * height);//formula to calculate rectangle area
			printf("Area = %0.2f", area);
		}

		else if (strcmp(shape, "Trapezium") == 0) {//used to search if the input is the same as the shape name
			printf("Enter height:");
			scanf_s("%d", &height);
			printf("Enter top base width:");
			scanf_s("%d", &top_basewidth);
			printf("Enter base width:");
			scanf_s("%d", &width);
			//height top base width,and width are multiplied by -1 to obtain the absolute
			if (height <= 0) { height = height * -1; }
			if (width <= 0) { width = width * -1; }
			if (top_basewidth <= 0) { top_basewidth = top_basewidth * -1; }//formula to calculate trapezium area
			area = (0.5) * (top_basewidth + width) * (height);
			printf("Area = %0.2f", area);
		}
		
		else if (strcmp(shape, "Circle") == 0) {
			printf("Radius:");
			scanf_s("%d", &radius);
			//radius ismultiplied by -1 to obtain the absolute
			if (radius <= 0) { radius = radius * -1; }
			area = pi * (radius * radius);//formula to calculate circle area
			printf("Area = %0.2f", area);
		}
		else {
			printf("Invalid input.Enter the values again...");//this prompts the user to input values again if non-alphabetical values are inputted when asked for shape type
		}
		//prints string and stores shape input
		printf("\n\n\nArea Calculator - Shape choices are:\n------------------------------\nTriangle, Rectangle, Circle, Trapezium.'Done' to end\n------------------------------");
		puts("\nchoice: (The absolute values of the dimensions are used for the calculation...\n");
		scanf_s("%s", shape, 100);
	}
}
