#include <stdio.h>
#include <stdlib.h>
#include <conio.h>
#include <ctype.h>
int main() {
	int a = 0;
	int znak;
	printf("podaj jakis znak\n");
	/*dokumentacja + komentarz do uzycia:
	wykorzystamy getchar
	Funkcja getchar() zwraca kod pierwszego znaku ze standardowego wejścia traktowany jako unsigned char przekształcony do typu int. W przypadku końca pliku lub błędu funkcja zwraca wartość EOF - dlatego a jest zdefiniowane jako int
	*/
	znak = getchar(); //wczytanie do zmiennej
	printf("znak w ascii: %d \n", znak); //test

	if (znak>47 && znak<58){ //warunek okreslajacy jaki typ znaku wpisano
	a=1;}else if(znak>95 && znak<122){a=2;}else if(znak>64 && znak<92){a=3;}else{a=4;}

	switch (a) { //wypis wyniku na podstawie okreslonego juz typu wpisanego znaku

	case 1: 
		printf("podales cyfre\n");
		break;

	case 2:
		printf("podales mala litere\n");
		break;

	case 3:
		printf("podales duza litere\n");
		break;
		
	case 4:
		printf("nie podales ani liczby ani litery\n");
		break;


	}



	system("pause");
	return 0;
}

//pobieramy liczbe od usera (lub litere) i opisujemy wg warunkow z case
//przedzialy 48-57    97-121   65-91
