// Calendar
#include <stdio.h>
#include <stdlib.h>
// still having problems with first day of month formula fix that(only problem)

void createCalendar(int month);
int is_leap(int year);
int no_of_days(int year, int month);
int count_leap_years(int start_year, int end_year);
int yearArg;

int main(int argCount, char *argStringValue[]) {
    int i;
    int monthArr[15] = {0};
    yearArg = atoi(argStringValue[1]);
    printf("Year: %d\n", yearArg);
    i = 2;
    while (i < argCount) {
        monthArr[i - 2] = atoi(argStringValue[i]);
        i++;
    }
    for (int j = 0; j < argCount - 2; ++j) {
        printf("\n");
        printf("\n");
        printf("%d/%d\n", monthArr[j], yearArg);
        createCalendar(monthArr[j]);
        printf("\n");
    }
    return 0;
}
int no_of_days(int year, int month) {
    if (month == 9 || month == 4 || month == 6 || month == 11)
        return 30;
    if (month != 2)
        return 31;
    return 28 + is_leap(year);
}

int is_leap(int year) {
    if (year % 4 == 0) {
        if (year % 100 == 0) {
            if (year % 400 == 0) {
                return 1;
            }
            else {
                return 0;
            }
        }
        else {
            return 1;
        }
    }
    else {
        return 0;
    }
}
int count_leap_years(int start_year, int end_year) {
    int leapYearsOcurred = 0;
    for (int year = start_year; year <= end_year; year++) {
        if ((year % 4 == 0 && year % 100 != 0) || year % 400 == 0) {
            leapYearsOcurred++;
        }
    }
    return leapYearsOcurred;
}

void createCalendar(int month) {
    printf("Sun \tMon \tTue \tWed \tThu \tFri \tSat\n");
    int currColumn = 0;
    int numDays = no_of_days(yearArg, month);
    int daysElapsed = ((yearArg - 1900) * 365) + count_leap_years(1900, yearArg) + no_of_days(yearArg, month);
    int firstDayOfMonth = (daysElapsed)%7;
    for (int k=0; k<firstDayOfMonth; k++) {
        printf("%s\t"," ");
        currColumn++;
    }
    int date = 1;
    while (date<=numDays)
    {
        printf("%d \t", date);
        currColumn++;
        if (currColumn==7) {
            printf("\n"); // end row and start row
            currColumn=0; // our next day will be a Sunday
        }
        date++;
    }
}