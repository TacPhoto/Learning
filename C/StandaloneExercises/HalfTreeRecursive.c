#include <stdio.h>
#include <stdlib.h>


void drzewo(int n, int i) { //wyswietlanie choinki przez rekurencje
	if (n == 0) { //warunek gdy n=0 i choinka nie ma dlugosci
		printf("podales 0, koniec\n");
	}
	else if (i <= n) { //odliczanie
		for (int j = 1; j <= i;j++) { printf("*"); }
		i++;
		printf("\n");
		drzewo(n, i);
	}
}

int main() {
	int n = 0;
	printf("podaj wysokosc choinki \n");
	scanf("%d", &n); //pobiera liczbe od uzytkownika
	printf("\n");
	int i = 1; //licznik
	drzewo(n, i); //uruchamia funkcje

	system("pause");
	return 0;
}


