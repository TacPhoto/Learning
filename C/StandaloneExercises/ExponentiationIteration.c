#include <stdio.h>
#include <stdlib.h>


void pot(int a,int n) {//funkcja podnoszaca okreslona liczbe do zadanej parametrem potegi
	int buf = a; //zmienna buforowa, w niej bede liczyl potege
	for(int i = 1; i<n; i++)buf = buf * a;
	printf("wynik= %d", buf);

}
int main() {
	int n = 0;//wysokosc potegi
	int a = 3;//nasza liczba
	printf("podaj wysokosc potegi do ktorej podniesiemy liczbe 3 \n");
	scanf("%d", &n); //pobiera liczbe od uzytkownika
	printf("\n");
	pot(a,n); //uruchamia funkcje

	system("pause");
	return 0;
}


