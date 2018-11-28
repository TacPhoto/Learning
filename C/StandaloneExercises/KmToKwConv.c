#include <stdio.h>
#include <stdlib.h>

int main(){
	//zmienne
	int a; //moc silnika (bez jednostek)
	typedef enum { prawda, falsz } bool; //nie znalazlem domyslnego boola w C bez standardu C99 wiec jest stworzony w ten sposob
	bool b; //rodzaj jednostki. 1 dla KM i 0 dla KW
	int wynik; //wynik dzialania
	//start
	printf("Witaj w przeliczniku mocy silnika KM na KW oraz KW na KM\n Podaj moc swojego silnika\n");
	do { //petla sprawdza poprawnosc wpisania danych
		scanf("%d", &a);
		if (a <= 0) {
			printf("Moc silnika nie moze byc ujemna ani rowna 0, sproboj wpisac ja ponownie\n");
		}
	} while (a<=0);

	printf("Wpisz 1 aby podac moc w KM lub 0 aby podac w KW\n");
	do { //petla sprawdza poprawnosc wpisania danych
		scanf("%d", &b);
		if ( b != 0 && b != 1 ) printf ("Sproboj ponownie wpisujac 1 aby podac moc w KM lub 0 aby podac w KW\n");
	
	} while (b != 0 && b != 1);
	//obliczenie konwersji
	if (b == 1) wynik = a * 1000 / 735,49875; //zamiana km na kw
	if (b == 0) wynik = a / 1000 * 735,49875; //zamiana kw na km
	//wypisz wynik
	printf("Moc twojego silnika wynosi po przeliczeniu: %d", wynik);



	system("pause");
	return 0;
}
