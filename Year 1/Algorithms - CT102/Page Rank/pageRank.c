#include <stdio.h>
void main() {
    const float d = 0.85;
    const float numPgs = 5;
    float prA;
    float prB;
    float prC;
    float prD;
    float prE;
    float prevA;
    float prevB;
    float prevC;
    float prevD;
    float prevE;
    int itr;
    prevA = prevB = prevC = prevD = prevE = 0;
    prA = prB = prC = prD = prE = 1 / numPgs;
    printf("How many iterations?:");
    scanf_s("%d", &itr);
    for ( int i = 0; i < itr; i++) {
        prevA = prA;
        prevB = prB;
        prevC = prC;
        prevD = prD;
        prevE = prE;
        prA = (1 - d) / numPgs + d * (prevC / 4 + prevE / 2);
        prB = (1 - d) / numPgs + d * (prevC / 4 + prevD / 2);
        prC = (1 - d) / numPgs + d * (prevA / 3);
        prD = (1 - d) / numPgs + d * (prevA / 3 + prevB / 1 + prevC / 4 + prevE / 2);
        prE = (1 - d) / numPgs + d * (prevA / 3 + prevC / 4 + prevD / 2);
    }
    printf("Page rank values after %d iterations:\n A: %.4f \n B: %.4f \n C: %.4f \n D: %.4f \n E: %.4f \n", itr, prA,prB, prC, prD, prE);
}