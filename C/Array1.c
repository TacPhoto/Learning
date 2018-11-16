#include <stdio.h>
#include <stdlib.h>
int main() {
	int tab[3];  //tabela
	int  wynik;  //wynik
	int i = 0;   //licznik

	for (int i = 0; i < 3; i++) { //wpisujemy dane do tablicy
		printf("podaj liczbe do tablicy\n");
		scanf("%s", &tab[i]);
	}
	if (tab[0] == tab[1] || tab[0] == tab[2] || tab[1] == tab[2]) { wynik = 1; } //warunek ktory zachodzi gdy mamy conajmniej dwa takie same rekordy tablicy
	else { wynik = 0; } //warunek alternatywny

	printf("%d", wynik);

	system("pause");
	return 0;
}

