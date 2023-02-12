/*Write a C program that does the following:

1. Read in the age of any number of children in a family using a while loop. The user should be able to escape the while loop by entering an age <0. (20 marks)

2. Count how many children there are. Print result to the screen. (20 marks)

3. Calculate the average age of the children. Print this to the screen at the end. When calculating the average age, decimal point is important, e.g. age 1 = 10, age 2 =11, average = 10.5. (20 marks)

4. Keep track of the oldest and youngest child. Print the ages of each to the screen at the end. (20 marks)

5. If an age entered is 18 or greater. Print a message that the age entered is not for a child. Do not count this age when determining the average age, the maximum age or the number of children in the family. */
//----------------------------------------------------------------------------------------------------------------------------------------------------------------------------
#include <stdio.h>

int main(void) {
  int age = 0;
  int count = 0;
  float sum_age = 0;
  float average_age = 0;
  int maximum_age = 0;
  int minimum_age = 1000000;

  while (1) {
    printf("Enter the age of the child %d: ", count + 1);
    scanf("%d", &age);

    if (age < 0) {
      printf("While loop ending\n");
      break;
    }

    if (age >= 18) {
      printf("Not a child\n");
    } else {
      count++;
      sum_age += age;

      if (age > maximum_age) {
        maximum_age = age;
      }
      if (age < minimum_age) {
        minimum_age = age;
      }
    }
  }

  average_age = sum_age / count;

  printf("There are %d children\n", count);
  printf("The average age is %.2f\n", average_age);
  printf("The maximum age is %d\n", maximum_age);
  printf("The minimum age is %d\n", minimum_age);

  return 0;
}
