#include <stdio.h>
#include <stdlib.h>
#include <time.h>

void generatePassword(int length) {
    char password[100];
    const char characters[] = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
    int charactersLength = sizeof(characters) - 1;

    srand(time(NULL));
    for (int i = 0; i < length; i++) {
        password[i] = characters[rand() % charactersLength];
    }
    password[length] = '\0';

    printf("Generated password: %s\n", password);
}

int main() {
    int length;
    printf("Enter the desired length of the password: ");
    scanf_s("%d", &length);
    generatePassword(length);
    return 0;
}