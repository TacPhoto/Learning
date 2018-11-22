#include <stdio.h>
#include <string.h>
#include <conio.h>
int main(void)
{
	FILE *plik;
	char napis[81]; //tekst, deklaracja
	plik = fopen("b:\\bin.rec", "wb"); //otworz plik do zapisu binarnego
	
	gets(napis); //pobierz napis od usera
	fwrite(napis, sizeof(napis), 1, plik); //zapisz
	fclose(plik);//zamknij
	return 0; //nie opozniam zamkniecia poniewaz program i tak nie ma nic wyswietlac
}
