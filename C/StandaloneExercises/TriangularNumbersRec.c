#include <stdio.h>
#include <stdlib.h>

int troj(int n) {
	if (n < 1) return 1; //warunek dla n<1
	return (n + 1) + troj(n - 1); //liczy liczbe trojkatna
}

int main(void) {
	int n = 0; //dla jakiej liczby liczyc
	printf("dla jakiej liczby obliczyc liczby trojkatne?\n");
	scanf("%d", &n);
	for (int i = 0; i<n; i++) { //petla wypisujaca liczby po jednej
		printf("\n%d: %d", i, troj(i)); //ciag bedzie liczony na podstawie licznika i
	}
	printf("\n");
	system("pause");
	return 0;
}
