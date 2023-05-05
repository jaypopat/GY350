#include <stdio.h>
#include <stdbool.h>

void drawBoard(char board[3][3]);
bool PlayerWinDetector(char board[3][3], char playerOption);
void initialiseBoard(char board[3][3]);

void drawBoard(char board[3][3]) {
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            printf("%c ", board[i][j]);
        }
        printf("\n");
    }
}
void initialiseBoard(char board[3][3]) {
    for(int i = 0; i < 3; i++) {
        for(int j = 0; j < 3; j++) {
            board[i][j] = '-';
        }
    }
}
bool PlayerWinDetector(char board[3][3], char playerOption){
    if(playerOption==board[0][0]&&playerOption==board[0][1]&&playerOption==board[0][2]){
        return true;
    }
    if(playerOption==board[1][0]&&playerOption==board[1][1]&&playerOption==board[1][2]){
        return true;
    }
    if(playerOption==board[2][0]&&playerOption==board[2][1]&&playerOption==board[2][2]){
        return true;
    }
    //-------------------
    if(playerOption==board[0][0]&&playerOption==board[1][0]&&playerOption==board[2][0]){
        return true;
    }
    if(playerOption==board[0][1]&&playerOption==board[1][1]&&playerOption==board[2][1]){
        return true;
    }
    if(playerOption==board[0][2]&&playerOption==board[1][2]&&playerOption==board[2][2]){
        return true;
    }
    //-----------------------------------
    if(playerOption==board[0][0]&&playerOption==board[1][1]&&playerOption==board[2][2]){
        return true;
    }
    if(playerOption==board[0][2]&&playerOption==board[1][1]&&playerOption==board[2][0]){
        return true;
    }
    //------------------------------------

    return false;

}

int main(void)
{
    char board[3][3];
    char playerOption = 'X';
    int resultFound = 1;
    int movesMade=0;

    initialiseBoard(board);
    drawBoard(board);

    do {
        int row, col;
        printf("Enter the row and column of your move (e.g -> '1 2')\n");
        scanf_s("%d %d", &row, &col);

        if (row < 0 || row > 2 || col < 0 || col > 2) {
            printf("Invalid input!\nNote rows and columns -> 0,1,2\n");
        }
        else {
            board[row][col] = playerOption;
            movesMade++;
            if (PlayerWinDetector(board, playerOption)) {
                printf("%c wins!\n", playerOption);
                resultFound = 0;
            }
            else if (movesMade == 9) {
                printf("It's a draw!\n");
                resultFound = 0;
            }
            drawBoard(board);
            if (playerOption == 'X') {
                playerOption = 'O'; // alternates Os and Xs
            } else {
                playerOption = 'X';
            }
        }
    }while (resultFound);
}
