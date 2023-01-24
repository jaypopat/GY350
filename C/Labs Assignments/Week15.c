/*Today you will be given a dataset stored in a text (.txt) file for an office supplies company.
This file contains all orders made to the office supplies company for 2019.
Write a C program that does the following:
1. You must open a file provided to you called ‘SampleData.txt’ (Figure 1). You should
open this file in read mode. Check if the FILE pointer is set correctly. Use either
fgetc() or fgets() to read in the content of the file and print it to the screen.
(30 marks)
2. Open the same file ‘SampleData.txt’ in read mode again. This time, you need to read
in the data from the file and store them in local variables of the appropriate type,
e.g. year as integer, region as string, etc. Print out each line of data to the screen.
You must also calculate and print the following to the screen: (40 marks)
a. The total income from all orders.
b. The average order value.
3. Open the same file ‘SampleData.txt’ in append mode. You must append a new entry
to the file: (30 marks)
a. Date = Today’s date.
b. Region = “Galway”
c. Rep = Your surname.
d. Item = “Pen”
e. Units = The last 2 digits of you student ID (use 50 if yours is ‘00’).
f. Unit Cost = 19.99
g. Total = Units X unit cost
*/ 
//TEXT FILE
/*
OrderDate	Region	Rep	Item	Units	Unit Cost	Total
1/6/18	East	Jones	Pencil	95	 1.99 	 189.05 
1/23/18	Central	Kivell	Binder	50	 19.99 	 999.50 
2/9/18	Central	Jardine	Pencil	36	 4.99 	 179.64 
2/26/18	Central	Gill	Pen	27	 19.99 	 539.73 
3/15/18	West	Sorvino	Pencil	56	 2.99 	 167.44 
4/1/18	East	Jones	Binder	60	 4.99 	 299.40 
4/18/18	Central	Andrews	Pencil	75	 1.99 	 149.25 
5/5/18	Central	Jardine	Pencil	90	 4.99 	 449.10 
5/22/18	West	Thompson	Pencil	32	 1.99 	 63.68 
6/8/18	East	Jones	Binder	60	 8.99 	 539.40 
6/25/18	Central	Morgan	Pencil	90	 4.99 	 449.10 
7/12/18	East	Howard	Binder	29	 1.99 	 57.71 
7/29/18	East	Parent	Binder	81	 19.99 	 41.99
8/15/18	East	Jones	Pencil	35	 4.99 	 174.65 
9/1/18	Central	Smith	Desk	2	 125.00 	 250.00 
9/18/18	East	Jones	PenSet	16	 15.99 	 255.84 
10/5/18	Central	Morgan	Binder	28	 8.99 	 251.72 
10/22/18	East	Jones	Pen	64	 8.99 	 575.36 
11/8/18	East	Parent	Pen	15	 19.99 	 299.85 
11/25/18	Central	Kivell	PenSet	96	 4.99 	 479.04 
12/12/18	Central	Smith	Pencil	67	 1.29 	 86.43 
12/29/18	East	Parent	PenSet	74	 15.99 	 35.43
1/15/19	Central	Gill	Binder	46	 8.99 	 413.54 
2/1/19	Central	Smith	Binder	87	 15.00 	 33.23
2/18/19	East	Jones	Binder	4	 4.99 	 19.96 
3/7/19	West	Sorvino	Binder	7	 19.99 	 139.93 
3/24/19	Central	Jardine	PenSet	50	 4.99 	 249.50 
4/10/19	Central	Andrews	Pencil	66	 1.99 	 131.34 
4/27/19	East	Howard	Pen	96	 4.99 	 479.04 
5/14/19	Central	Gill	Pencil	53	 1.29 	 68.37 
5/31/19	Central	Gill	Binder	80	 8.99 	 719.20 
6/17/19	Central	Kivell	Desk	5	 125.00 	 625.00 
7/4/19	East	Jones	PenSet	62	 4.99 	 309.38 
7/21/19	Central	Morgan	PenSet	55	 12.49 	 686.95 
8/7/19	Central	Kivell	PenSet	42	 23.95 	 55.43
8/24/19	West	Sorvino	Desk	3	 275.00 	 825.00 
9/10/19	Central	Gill	Pencil	7	 1.29 	 9.03 
9/27/19	West	Sorvino	Pen	76	 1.99 	 151.24 
10/14/19	West	Thompson	Binder	57	 19.99 	 45.55
10/31/19	Central	Andrews	Pencil	14	 1.29 	 18.06 
11/17/19	Central	Jardine	Binder	11	 4.99 	 54.89 
12/4/19	Central	Jardine	Binder	94	 19.99 	 21.01
12/21/19	Central	Andrews	Binder	28	 4.99 	 139.72 */
#include <stdio.h>

char line[101];
float lineCounter = 0;
float avgOrderValue=0;
float sumOrderValues =0;
float total_income =0;


void main() {
    FILE *fptr;
    char header[101];
    char OrderDate[101];
    char Region[101];
    char Rep[101];
    char item[101] ;
    float units;
    float unitCost;
    float totalCost;

    fopen_s(&fptr, "C:/Users/email/Desktop/database.txt", "r");
   /* Checking if the file is open or not. If it is not open then it will print an error message and
   exit the program. If it is open then it will print "Everything works fine" and then it will print
   the contents of the file. */
    if (fptr == NULL) {
        puts("Error Opening File \n Exiting ........");
        return;
    } else {
        printf("Everything works fine.\n");
        char c = fgetc(fptr);
        while (c != EOF) {
            printf("%c", c);
            c = fgetc(fptr);
        }
    }
    /* Closing the file. */
    fclose(fptr);

    fopen_s(&fptr, "C:/Users/email/Desktop/database.txt", "r");
   /* Printing the header of the file. */
    fgets(header, 101, fptr);
    printf("%s", header);
   /* Reading the file and printing the contents of the file. */
    while (!feof(fptr)){

        fscanf_s(fptr, "%s\t", OrderDate,101);
        fscanf_s(fptr, "%s\t", Region,101);
        fscanf_s(fptr, "%s\t", Rep,101);
        fscanf_s(fptr, "%s\t", item,101);
        fscanf_s(fptr, "%f\t", &units);
        fscanf_s(fptr, "%f", &unitCost);
        fscanf_s(fptr, "%f", &totalCost);
        sumOrderValues +=totalCost;
        lineCounter++;


        printf("%s\t%s\t%s\t%s\t%.2f\t%.2f\t%.2f\n", OrderDate,Region,Rep,item,units,unitCost,totalCost);
    }
    /* This is calculating the average order value and the total income. */
    avgOrderValue = (sumOrderValues/lineCounter);
    printf("\nAverage Order Value = %f", avgOrderValue);
    printf("\nTotal income is %f", sumOrderValues);
    fclose(fptr);

    fopen_s(&fptr, "C:/Users/email/Desktop/database.txt", "a");
    if (fptr == NULL) {
        puts("Error Opening File \n Exiting ........");
        return;
    }
    /* Calculating the total cost of the custom order and then printing it to the file. */
    else {
        float total_custom_cost=0;
        total_custom_cost = 66*19.99;
        fprintf(fptr,"\n24/1/23\tGalway\tPopat\tPen\t66\t19.99\t%.2f\n"  ,total_custom_cost);
        }
}