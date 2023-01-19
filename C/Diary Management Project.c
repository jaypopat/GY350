#include <stdio.h>
#include <string.h>

int check_credentials(const char* username, const char* password);
void execute_function(void);

const char* valid_username = "admin";
const char* valid_password = "password";

int check_credentials(const char* username, const char* password)
{
    if (strcmp(username, valid_username) == 0 && strcmp(password, valid_password) == 0) {
        return 0;
    }
    else {
        return 1;
    }
}

void execute_function(void)
{
    char userInput[100];
    char command[64];
    FILE* database;

    printf("Enter command: \nThe commands available are \"access_db\",\"delete_db\" and \"add_note\"'\n");
    fgets(command, sizeof(command), stdin);

    if (strcmp(command, "access_db\n") == 0) {
        if (fopen_s(&database, "database.txt", "r") != 0) {
            puts("Error opening database file");
            return;
        }

        char c = fgetc(database);
        while (c != EOF) {
            printf("%c", c);
            c = fgetc(database);
        }

        if (fclose(database) != 0) {
            puts("Error closing database file");
            return;
        }
    }
    else if (strcmp(command, "delete_db\n") == 0) {
        // Open the database file in write mode
        if (fopen_s(&database, "database.txt", "w") != 0) {
            puts("Error opening database file");
            return;
        }

        if (fclose(database) != 0) {
            puts("Error closing database file");
            return;
        }
    }
    else if (strcmp(command, "add_note\n") == 0) {
        printf("Enter your note: ");
        fgets(userInput, sizeof(userInput), stdin);

        if (fopen_s(&database, "database.txt", "a") != 0) {
            puts("Error opening database file");
            return;
        }

        if (fprintf(database, "%s", userInput) < 0) {
            puts("Error writing to database file");
            return;
        }

        if (fclose(database) != 0) {
            puts("Error closing database file");
            return;
        }

        printf("Your note has been saved.\n");
    }
    else {
        printf("Invalid command entered!\n");
        return 1;
    }
}

void main(void) {
    char username[64];
    char password[64];


    printf("Enter username: ");
    fgets(username, sizeof(username), stdin);

    printf("Enter password: ");
    fgets(password, sizeof(password), stdin);

    if (check_credentials(username, password)) {
        execute_function();
    }
    else {
        printf("Invalid username or password!\n");
    }
}
