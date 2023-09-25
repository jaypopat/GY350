/*Write a C program that does the following:

1. Read in 21 user ratings for a movie. Each rating is a number from 1 – 5 stars. Store the ratings in an array. (20 marks)

2. Calculate the average rating. Print this to the screen rounded to 1 decimal point, e.g. 4.1 stars. (20 marks)

3. Create a new array called ‘ratingFrequency’ that stores the number of ratings for each star, e.g. 3 one star ratings, 5 two star ratings, etc. (20 marks)

4. Display the ratings frequency to the screen, where an asterisk (* symbol) is printed out for the number of movie ratings of that star number. See screenshot. (20 marks)

5. Create a new array ‘ratingPercent’ that contains the percentage of ratings for each star, e.g. 14.3% one star, 23.8% two star, etc.
   Display this to the screen in a similar manner to task 4.(20 marks)*/

void main()
{
	int rating_number[21];
	float average_rating, sum_rating  = 0;
	int ratingFrequency[5]={0,0,0,0,0};//index values incremented
	int i = 0;
	int c,j;
	float percentage_rating;
	int ratingPercent[100];

	while (i<21) {
		printf("Enter the rating number %d : ", i+1); 
		scanf_s("%d", &rating_number[i]);
		sum_rating = sum_rating + rating_number[i];
		if (rating_number[i] < 0 || rating_number[i]>5) {
			printf("Not a valid rating\n"); //doesnt accept any value outside the condition in the calculations
		}
		//used to get the frequency of the array elements
		else {
			if (rating_number[i] == 1) {
				ratingFrequency[0]+=1;
			}
			else if (rating_number[i] == 2) {
				ratingFrequency[1]+= 1;
			}
			else if (rating_number[i] == 3) {
				ratingFrequency[2]+= 1;
			}
			else if (rating_number[i] == 4) {
				ratingFrequency[3]+= 1;
			}
			else if (rating_number[i] == 5) {
				ratingFrequency[4]+= 1;
			}
			i++;
		}
	}
	average_rating = sum_rating / 21;
	printf("Average rating is : %0.1f\n", average_rating);
	printf("Rating Frequency\n-------------");
	printf("\n");

	//nested for-loops 
	for (int j = 0; j < 5; j++) {
		for (int k = 0; k < ratingFrequency[j]; k++) {
			printf("*"); //prints as many stars as the frequency
		}
		printf("%d star \t %d \n", j + 1, ratingFrequency[j]);
	}
	printf("--------\nRating Percent\n---------\n");
	printf("\n");
	
	for (int j = 0; j < 5; j++) {
		ratingPercent[j] = ((ratingFrequency[j] *100)/21); //formula to calculate the percentage
		for (int k = 0; k < ratingPercent[j]; k++) {
			printf("* "); //prints as many stars as the percentage number
		}
	printf("%d star is %d percent\n\n", j + 1, ratingPercent[j]);
	}
}

