#include <stdio.h>

typedef struct
{
    int day, month, year;
}  date;
typedef struct
{
    char name[50];
    int accountNumber;
    double balance;
    date lastTrans;
} customer;

void getCustomerName(customer * cptr);
void getAccountNumber(customer * cptr);
void getLastTransDate(customer * cptr);
void getBalance(customer * cptr);
void printCustomer(customer * cptr);

void main()
{
    int i;
    customer customers[3];
    /* This is a for loop that is iterating through the array of customers and calling the functions to
    get the details of each customer. */
    for (i = 0; i < 3; i++)
    {
        getCustomerName( & customers[i]);
        getAccountNumber( & customers[i]);
        getLastTransDate( & customers[i]);
        getBalance( & customers[i]);
        printf("\n");
    }
    printf("\n\n%25s\t%13s\t%12s\t%s\n\n", "Name", "Account Number","Balance", "Date of Last Transaction");
    for (i = 0; i < 3; i++)
    {
        printCustomer( & customers[i]);
    }
}

void getCustomerName(customer* cptr)
{
    //asking for user input and storing name in the structure member name using cptr - pointer
    printf("Enter customer name:");
    scanf_s("%s",cptr->name);
}
void getAccountNumber(customer* cptr)
{
    //asking for user input and storing account number in the structure member accountNumber using cptr - pointer
    printf("Enter Account Number:");
    scanf_s("%d",&(cptr->accountNumber));
}
void getLastTransDate(customer* cptr)
{
    //asking for user input and storing name in the nested structure date and using dot notation to access inner elements
    printf("Enter Last Transaction Date in the format dd/mm/yyyy:");
    scanf_s("%d/%d/%d",&(cptr->lastTrans.day),&(cptr->lastTrans.month),&(cptr->lastTrans.year));
}

    //asking for user input and storing name in balance structure member using cptr - pointer
void getBalance(customer* cptr){
    printf("Enter Balance: ");
    scanf_s("%lf",&(cptr->balance));
}
void printCustomer(customer* cptr)
{   //printing all the details in a table format with appropriate spacing 
    printf("\n\n%25s\t%13d\t%lf%12d/%d/%d\n\n", cptr->name, cptr->accountNumber,cptr->balance,cptr->lastTrans.day,cptr->lastTrans.month,cptr->lastTrans.year);

}




