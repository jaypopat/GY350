/*Write a C program that does the following:

1. Write a function called setPasscode. This function should generate a random passcode between 0 and N. ‘N’ is the largest possible passcode and is passed to the function as an integer. The function should return the random passcode. Call this function in main(). (20 marks)

2. Create a new function called ‘randomPasscodeSearch’ that accepts a passcode and max possible passcode as parameters. This function must generate random passcodes until the correct passcode if found. Print the passcode to the screen and the number of attempts it took to find it. Call this function in main(). (25 marks)

3. Create another function called ‘orderedPasscodeSearch’ that accepts a passcode and number of passcode digits as parameters. This function must check every code in order from 0 until the correct passcode if found, i.e. 0, 1, 2, … , N. Print the passcode to the screen and the number of attempts it took to find it. Call this function in main(). (25 marks)

4. Test your program by creating 4 passcodes. The first between [0, 9], second between [0, 99], third [0, 999], and fourth [0, 9999]. For each of these passcodes, use both ‘randomPasscodeSearch’ and ‘orderedPasscodeSearch’ functions. (20 marks)

5. Test your program once more by setting the passcode to the final 3 digits of your student ID. Again, use both ‘randomPasscodeSearch’ and ‘orderedPasscodeSearch’ functions to search for the passcode. Note, if your student ID contains ‘0’s, use next non-zero numbers.*/
//-------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Jay Popat	22346566	22/11/2022
#include <stdio.h>
#include <math.h> //gives access to math library functions such as the 'ceil' in this case

//function declaration
float vol(float l, float w, float h); //passing the parameters of length width and height
void mass(float volume); 
float barrels(float volume); 

//main function
void main() {
	
	float volume = vol(5, 6, 6); //the values of 5,6,6 correspond to the parameters 'l','w' and 'h'
	printf("Volume = %0.2f\n", volume);//prints the volume which is calculated using the vol function which is referenced to the line above
	mass(volume);
	float numbarrels = barrels(volume);//storing the output from the function into a float variable which allows us to print
	printf("\nBarrels = %0.2f\n", numbarrels); //prints the barrel number which is calculated using the user defined function barrels which is referenced to the line above
}
//defining the function -> volume
float vol(float l, float w, float h) {
	return l*w*h; //calculation for volume
}
//defining the function -> mass
void mass(float volume) {
	float calcmass = volume * 1000; //storing the output from the function into a float variable which allows us to print
	printf("The mass of the water is %.2f", calcmass);
}
//defining the function barrels
float barrels(float volume) { //the parameter volume is used to calculate the number of barrels
	return ceil(volume / 0.48); //alongside calculating the number of barrels, the ceil function from the math library gets the absolute value of the result
}

