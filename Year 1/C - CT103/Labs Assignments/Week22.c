/*• Write a command-line-driven
program which accepts a year
number as an argument, and one
or more month numbers as
subsequent arguments
• The program should print out
calendars for the specified months*/
#include <stdio.h>
#include <stdlib.h>

void createCalendar(int month);
int is_leap(int year);
int no_of_days(int year, int month);
int newYearDay(int year);
int yearArg; // declaring global variable for command line argument - year
int main(int argCount, char *argStringValue[]) {
    int i;
    int monthArr[15] = {0};
    yearArg = atoi(argStringValue[1]);
    if (yearArg<1900){
        exit(0);
    }
    printf("Year: %d\n", yearArg);
    i = 2;
    /* This is a while loop that is checking the command line arguments for the months. It is checking
    if the month is between 0 and 12. If it is not, it will exit the program.It is also adding the month command line
    arguments to the monthArr array*/
    while (i < argCount) {
        monthArr[i - 2] = atoi(argStringValue[i]);
        if(monthArr[i]<0||monthArr[i]>12){
            exit(0);
        }
        i++;
    }
    /* This is a for loop that is printing the calendar of the months that are in the command line arguments by
    calling the months as parameters to the createCalendar function */
    for (int j = 0; j < argCount - 2; ++j) {
        printf("\n");
        printf("\n");
        printf("%d/%d\n", monthArr[j], yearArg);
        createCalendar(monthArr[j]);
        printf("\n");
    }
    return 0;
}

 /*If the month is September, April, June, or November, return 30. Otherwise, if the month is not
 February, return 31. Otherwise, return 28 plus the result of the function is_leap
 */
int no_of_days(int year, int month) {
    if (month == 9 || month == 4 || month == 6 || month == 11)
        return 30;
    if (month != 2)
        return 31;
    return 28 + is_leap(year);
}

int is_leap(int year) {
    if (year % 400 == 0)
        return 1;
    if (year % 100 == 0)
        return 0;
    if (year % 4 == 0)
        return 1;
    return 0;
}

//It calculates the day of the week for the first day of the year.
int newYearDay(int year){
    int sumOfDays = 0;
    /* This is a for loop that is adding the number of days in each year from 1900 to the year that is
    given in the command line argument. */
    for (int i = 1900; i < year; i++) {
        if (is_leap(i) == 1) {
            sumOfDays += 366;
        }
        else {
            sumOfDays += 365;
        }
    }
   //Calculating the day of the week for the first day of the year.
    return ((sumOfDays+1) % 7);
}

void createCalendar(int month) {
    printf("Sun \tMon \tTue \tWed \tThu \tFri \tSat\n");
    int currColumn = 0;
    int firstDayOfYear = newYearDay(yearArg);
    int daysInMonth[] = {31, 28 + is_leap(yearArg), 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    int numDaysFromStartOfYearToGivenMonth = 0;
    /* This is a for loop that is adding the number of days in each month from January to the month
    that is given in the command line argument. */
    for (int i = 0; i < month - 1; i++) {
        numDaysFromStartOfYearToGivenMonth += daysInMonth[i];
    }
    /* This is calculating the day of the week for the first day of the month. */
    int firstDayOfMonth = (firstDayOfYear + numDaysFromStartOfYearToGivenMonth) % 7;
    for (int k=0; k<firstDayOfMonth; k++) {
        printf("%s\t"," ");
        currColumn++;   
    }
    int numDays = no_of_days(yearArg, month);
    int date = 1;
    /* This is a for loop that is printing the dates of the month. It is also printing a new line
    when the current column is 7. */
    for (int date=1; date<=numDays; date++){
        printf("%d \t", date);
        currColumn++;
        if (currColumn==7){
            printf("\n"); // end row and start
            currColumn=0; // our next day will be a Sunday
        }
    }
}