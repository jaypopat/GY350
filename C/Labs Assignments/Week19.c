#include "stdio.h"
#include "string.h"
#include "stdlib.h"

//Declaring the functions that will be used in the program.
void readCars(char myfilePath[], int numCars);
void displayGarage(int numCars);
int checkYear(int numCars, int year);

typedef struct {
    char make[20];
    char model[20];
    int year;
}car;
car garage[10]; // contains 10 car structures

int checkYear(int numCars, int year);

//The function reads the data from the file and stores it in the struct array using the function readCars()
int main()
{
    char myfilePath[] = "C:\\Users\\email\\Desktop\\carsYear.csv";
    readCars(myfilePath, 10);
    displayGarage(10);
    int ans = checkYear(10, 2016);
    printf("There are %d cars with year %d in the garage.\n",ans,2016);

}
//The function reads the data from the file and stores it in the struct array
void readCars(char myfilePath[], int numCars){
    FILE * csvPtr;
    //Declaring a variable of type car.
    car c;
    int count = 0;
    char data[50];
    //Opening the file and assigning the file pointer to csvPtr.
    fopen_s(&csvPtr,myfilePath,"r");
    if (csvPtr==NULL){
        printf("no file");
        return;
    }
    else{
        //Reading the data from the file and storing it in the struct array using parsing function strtok
        while(!feof(csvPtr) && count<numCars){
            car c;
            fgets(data, 50, csvPtr);
            char *token = strtok(data, " ");
            strcpy(c.make,token);
            token = strtok(NULL, ",");
            strcpy(c.model,token);
            token = strtok(NULL, ",");
            c.year = atoi(token);
            garage[count]=c;
            count++;
        }
    }
}


//This function displays the cars in the garage

void displayGarage(int numCars){
    printf("---Cars in Garage---\n");
    for (int i = 0; i < numCars; i++) {
        printf("Car %d\nCar make: %s\n", i+1,garage[i].make);
        printf("Car Model: %s\n", garage[i].model);
        printf("Car Year: %d\n", garage[i].year);
        printf("-----------");
        printf("\n");
    }
}
/*
It takes the number of cars in the garage and the year of the car as parameters and returns the
number of cars in the garage that were made in that year
 */
int checkYear(int numCars, int year){
    int counter =0;
    for (int i = 0; i < numCars; ++i) {
    if (year == garage[i].year){
        counter++;}
    }
    return counter;
}


