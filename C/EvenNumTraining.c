#include <stdio.h>
#include <stdlib.h>
int nr = 121314; //nr użyty jako początek, deklaruje jako zmienna globalna gdyz nie ulega zmianie
	int main()
	{
		int a = nr;
		int i = 15;  //licznik wypisan
		a = nr;
		if (a % 2 == 1) a -= 1; //upewnia sie ze pracujemy na liczbach parzystych

		do {  //wypisuje kolejno liczby parzyste malejaco
			printf("%d \n", a);
			a-=2;
			i--;
		} while ( i > 0 );


		system("pause");
		return 0;
	}
