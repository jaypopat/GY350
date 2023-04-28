#include <stdio.h>
void main() {
	int degC = 0;
	int degF = 0;


	printf("Enter temperature in degrees Fahrenheit: "); //prints the question 
	scanf_s("%d", &degF); //Reads the user input and stores it

	degC = (degF - 32) * 5/9; //Convert Fahrenheit to Celcius

	if (degC > 32)
		printf("Too hot"); //prints if calculated temperature is over 32 degrees
	else if (degC < 0) printf("Too cold"); //prints if the calculated temperature is under 0 degrees
	else printf("This is near room temperature"); //prints if the calculated temperature is 0<temperature<32

}
