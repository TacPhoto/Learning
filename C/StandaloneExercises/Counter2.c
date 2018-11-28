#include <stdio.h>
#include <stdlib.h>

//wersja 'dla zaawans'
void list(int n,int i) { //odliczanie do zera, rekurencyjne
	if(n==0){ //warunek gdy n=0 i nie ma do czego odliczac
		printf("podales 0, koniec\n");
	}
	else if(i<=n){ //odliczanie
		printf("%d\n", i);
		i++;
		list(n,i);
	}
		

}

int main() {
	int n = 0;
	printf("podaj liczbe na potrzeby odliczania \n");
	scanf("%d", &n); //pobiera liczbe od uzytkownika
	printf("\n");
	int i = 1; //licznik
	list(n,i); //uruchamia funkcje
	printf("koniec\n");
	system("pause");
	return 0;
}


