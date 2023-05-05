#include <stdio.h>

int inverseCalculation(int num, int mod);

void main() {
	int moduloNum = 0;
	int inverseNum = 0;

	printf("Enter the modulo number: ");
	scanf_s("%d", &moduloNum);

	printf("Enter the number that is inverse mod %d: ", moduloNum);
	scanf_s("%d", &inverseNum);

	int answer = inverseCalculation(inverseNum, moduloNum);

	printf("%d^-1 modulo %d = %d", inverseNum, moduloNum, answer);
}

int inverseCalculation(int num, int mod) {
	int testFlag = 0;
	int numMultiplier = 1;
	int modMultiplier = 1;
	int multipliedNum = num;
	int multipliedMod = mod;

	while (!testFlag) {
		if (multipliedNum % multipliedMod == 1) {
			testFlag = 1;
		}
		else {
			numMultiplier++;
			multipliedNum = num * numMultiplier;

			if (multipliedNum >= multipliedMod && multipliedNum % multipliedMod != 1) {
				modMultiplier++;
				multipliedMod = mod * modMultiplier;
			}


		}
	}
	return numMultiplier;
}