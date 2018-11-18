#include <stdio.h>
#include <string.h>
#include <stdlib.h>
int main() {
	char s[] = "jakis napis";
	char t[] = "tez jakis napis";
	int wynik;


	//porownanie
	if (s[strlen(s) - 1] == t[strlen(t) - 1]) { wynik = 1; }
	else { wynik = 0 ;}

	//wypisz wynik
	printf("wynik= %d \n", wynik);

	system("pause");
	return(0);
}

//cw3 funkcja strend(s,t) - zwraca 1 gdy na loncu t jest to co na koncu s. w przeciwnym wypadku zwraca zero
