#include <stdio.h>
#include <stdlib.h>
int main()
{
	int *a, *b; //dekl wskaznikow
	//zmienne
	int e = 33; 
	int f = 45;
	//przypisanie adresow
	a = &e;
	b = &f;
	//wypisuje adresy
	printf("dla p adres to %p \n", a);
	printf("dla p1 adres to %p \n", b);
	//porownywanie i wynik
	if (b>a) printf("wiekszy adres to p1");
	else printf("wiekszy adres to p");
	system("pause");
	return 0;
}
