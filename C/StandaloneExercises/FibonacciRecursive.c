////
////read the description, do not try to copy the code if you want to keep computational complexity low ;)
////
#include <stdio.h>
#include <stdlib.h>
int ciag(int n) { //oblicza liczbe w ciagu
	if (n == 0) { //warunek dla 0
		return 0;
	}
	else if (n == 1) { //warunek dla 1
		return 1;
	}
	else {
		return (ciag(n - 1) + ciag(n - 2)); //liczy kolejna liczbe ciagu fib
	}
}
int main() {
	int n = 0; //ilosc rekordow
	printf("\nIle rekordow ciagu fibonacciego wyswietlic?\n");
	scanf("%d", &n);
	printf("\nCiag fibonacciego dla liczby %d: \n\n", n);

	for (int i = 0; i<n ;i++) { //wypis ciagu
		printf("%d ", ciag(i)); //wypisz aktualna liczbe fib na podst. licznika i
	}
}
