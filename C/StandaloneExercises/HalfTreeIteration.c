#include <stdio.h>
#include <stdlib.h>


void drzewo(int n) { //wyswietlanie polowy choinki dla parametru wysokosci
	for (int i = 1; i <= n; i++) { //funkcja zawiera funkcje wyswietlajaca pietro choinki, a pozniej enter
		for (int j = 1; j <= i; j++) { //wyswietla pietro choinki
			printf("*");
		}
		printf("\n");
	}
}

int main() {
	int n = 0;
	printf("podaj wysokosc choinki \n");
	scanf("%d", &n); //pobiera liczbe od uzytkownika
	printf("\n");
	drzewo(n); //uruchamia funkcje

	system("pause");
	return 0;
}


