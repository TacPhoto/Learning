#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main()
{
	int liczba; //liczba ktora bedziemy wpisywac
	int tmp; //zmienna tymczasowa na potrzeby petli odwracajacej znaki
	int i; //licznik
	char str[12];//zapisujemy tablice jako lancuch znakow aby ja potem latwo odwrocic oraz nie pozbyc sie we. zer na poczatku
	printf("Podaj liczbe:\n");
	scanf("%d", &liczba);
	//wpisujemy int do char (str)
	sprintf(str, "%d", liczba);
	int length = strlen(str);//sprawdza dlugosc lancucha znakow 
	for(i = 0;i<length/2;i++)
	{		
		tmp = str[i];	//wpisujemy dany znak do zmiennej tymczasowej
		str[i] = str[length-i-1];		//przesuniecie krok 1
		str[length-i-1] = tmp;      //przesuniecie, krok 2 (ostatni w iteracji)
	}
				
	printf("Twoja liczba po odwroceniu: %s", str);
	systen("pause");
	return 0;
}
