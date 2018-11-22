#include <stdio.h>
#include <stdlib.h>
#include <string.h>
    const 	int n=5;
 void wielka(char * tekst) //zmienia na duze litery przez przesuniecie w ascii
{
	*(tekst + 7) = '\0';

	int i;
	for(i = 0 ; i < 8 ; i++)
	{
		if(*(tekst + i) > 96 && *(tekst + i) < 123)
		{
			*(tekst + i) = *(tekst + i) - 32;
		}
	}
}
int main() {
    char tekst[n]; //deklaracja zmiennej na tekst 
    printf("podaj string\n");
    fgets (tekst ,n+1, stdin); //czytaj tekst
	wielka(tekst); //wykonaj funkcje
    printf("\n %s",tekst); //pokaz wynik funkcji
    system("pause");
    return 0;
}
